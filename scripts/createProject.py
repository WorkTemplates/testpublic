import configparser
import os
import xml.etree.ElementTree as ET
import shutil

path = "app"

def replace(fpath, function):
    with open(fpath) as f:
        s = f.read()
    s = function(s)
    with open(fpath, "w") as f:
        f.write(s)
    pass

config = configparser.ConfigParser()
config.read('./config/config.txt')

package = config['PROJECT']['package']
data_url = config['PROJECT']['data_url']

app_name = config['TEXT']['app_name']
about_text = config['TEXT']['about_text']
tab_title = config['TEXT']['tab_title']


colors_day_colorPrimary = config['COLORS_DAY']['colorPrimary']
colors_day_colorPrimaryVariant = config['COLORS_DAY']['colorPrimaryVariant']
colors_day_colorOnPrimary = config['COLORS_DAY']['colorOnPrimary']
colors_day_colorSecondary = config['COLORS_DAY']['colorSecondary']
colors_day_colorSecondaryVariant = config['COLORS_DAY']['colorSecondaryVariant']
colors_day_colorOnSecondary = config['COLORS_DAY']['colorOnSecondary']

colors_night_colorPrimary = config['COLORS_NIGHT']['colorPrimary']
colors_night_colorPrimaryVariant = config['COLORS_NIGHT']['colorPrimaryVariant']
colors_night_colorOnPrimary = config['COLORS_NIGHT']['colorOnPrimary']
colors_night_colorSecondary = config['COLORS_NIGHT']['colorSecondary']
colors_night_colorSecondaryVariant = config['COLORS_NIGHT']['colorSecondaryVariant']
colors_night_colorOnSecondary = config['COLORS_NIGHT']['colorOnSecondary']

os.rename(f"{path}/src/main/java/com/template", f"{path}/src/main/java/com/{package}")

#build.gradle
replace(f"{path}/build.gradle", lambda x: x.replace("applicationId \"com.template.app\"", f"applicationId \"com.{package}.app\""))

#kotlin sources
for dname, dirs, files in os.walk(f"{path}/src/main/java/com"):
    for file in files:
        if file.split('.')[1] == 'kt':
            fpath = os.path.join(dname, file)
            def replace_function(s):
                s = s.replace("package com.template", f"package com.{package}")
                s = s.replace("import com.template.app", f"import com.{package}.app")
                return s
            replace(fpath, replace_function)

replace(f"{path}/src/main/java/com/{package}/app/network/Dataretrofit.kt",
        lambda x: x.replace("@GET(\"url\")", f"@GET(\"{data_url}\")"))

#navigation
replace(f"{path}/src/main/res/navigation/main.xml", lambda x: x.replace("android:name=\"com.template.", f"android:name=\"com.{package}."))

#manifest
replace(f"{path}/src/main/AndroidManifest.xml", lambda x: x.replace("package=\"com.template", f"package=\"com.{package}"))

#proguard
replace(f"{path}/proguard-rules.pro", lambda x: x.replace("com.template.app", f"com.{package}.app"))

#string
def replace_strings(s):
    root = ET.fromstring(s)
    strings = root.findall('./string')
    for string in strings:
        name = string.attrib['name']
        if name == 'app_name':
            string.text = app_name
        if name == 'main_about_text':
            string.text = about_text
        if name == 'main_tab_data_title':
            string.text = tab_title
    return str(ET.tostring(root, 'UTF-8'), 'UTF-8')
replace(f"{path}/src/main/res/values/strings.xml", replace_strings)

#colors
def replace_colors_day(s):
    root = ET.fromstring(s)
    strings = root.findall('./style/item')
    for string in strings:
        name = string.attrib['name']
        if name == 'colorPrimary':
            string.text = colors_day_colorPrimary
        if name == 'colorPrimaryVariant':
            string.text = colors_day_colorPrimaryVariant
        if name == 'colorOnPrimary':
            string.text = colors_day_colorOnPrimary
        if name == 'colorSecondary':
            string.text = colors_day_colorSecondary
        if name == 'colorSecondaryVariant':
            string.text = colors_day_colorSecondaryVariant
        if name == 'colorOnSecondary':
            string.text = colors_day_colorOnSecondary
    return str(ET.tostring(root, 'UTF-8'), 'UTF-8')
replace(f"{path}/src/main/res/values/themes.xml", replace_colors_day)

def replace_colors_night(s):
    root = ET.fromstring(s)
    strings = root.findall('./style/item')
    for string in strings:
        name = string.attrib['name']
        if name == 'colorPrimary':
            string.text = colors_night_colorPrimary
        if name == 'colorPrimaryVariant':
            string.text = colors_night_colorPrimaryVariant
        if name == 'colorOnPrimary':
            string.text = colors_night_colorOnPrimary
        if name == 'colorSecondary':
            string.text = colors_night_colorSecondary
        if name == 'colorSecondaryVariant':
            string.text = colors_night_colorSecondaryVariant
        if name == 'colorOnSecondary':
            string.text = colors_night_colorOnSecondary
    return str(ET.tostring(root, 'UTF-8'), 'UTF-8')
replace(f"{path}/src/main/res/values-night/themes.xml", replace_colors_night)

shutil.copytree(f"./config/mipmap-hdpi", f"{path}/src/main/res/mipmap-hdpi", dirs_exist_ok=True)
shutil.copytree(f"./config/mipmap-mdpi", f"{path}/src/main/res/mipmap-mdpi", dirs_exist_ok=True)
shutil.copytree(f"./config/mipmap-xhdpi", f"{path}/src/main/res/mipmap-xhdpi", dirs_exist_ok=True)
shutil.copytree(f"./config/mipmap-xxhdpi", f"{path}/src/main/res/mipmap-xxhdpi", dirs_exist_ok=True)
shutil.copytree(f"./config/mipmap-xxxhdpi", f"{path}/src/main/res/mipmap-xxxhdpi", dirs_exist_ok=True)

shutil.copy(f"./config/image.jpg", f"{path}/src/main/res/drawable/image.jpg")

shutil.copy(f"./config/google-services.json", f"{path}/google-services.json")

shutil.copy(f"./config/keystore.jks", f"{path}/keystore.jks")