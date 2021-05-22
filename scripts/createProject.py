import configparser
import os



for dname, dirs, files in os.walk("../app/src/main/java"):
    print(dname, dirs, files)

