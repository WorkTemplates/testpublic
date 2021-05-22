import configparser
import os


def replace(fpath, function):
    with open(fpath) as f:
        s = f.read()
    s = function(s)
    with open(fpath, "w") as f:
        f.write(s)
    pass

config = configparser.ConfigParser()
config.read('config.txt')

package = config['PROJECT']['package']

os.rename("../app/src/main/java/com/template", f"../app/src/main/java/com/{package}")

#build.gradle
replace("../app/build.gradle", lambda x: x.replace("applicationId \"com.template.app\"", f"applicationId \"com.{package}.app\""))

#kotlin sources
for dname, dirs, files in os.walk("../app/src/main/java/com"):
    for file in files:
        if file.split('.')[1] == 'kt':
            fpath = os.path.join(dname, file)
            def replace_function(s):
                s = s.replace("package com.template", f"package com.{package}")
                s = s.replace("import com.template.app", f"import com.{package}.app")
                return s
            replace(fpath, replace_function)

#navigation
replace("../app/src/main/res/navigation/main.xml", lambda x: x.replace("android:name=\"com.template.", f"android:name=\"com.{package}."))

#manifest
replace("../app/src/main/AndroidManifest.xml", lambda x: x.replace("package=\"com.template", f"package=\"com.{package}"))

#proguard
replace("../app/proguard-rules.pro", lambda x: x.replace("com.template.app", f"com.{package}.app"))


