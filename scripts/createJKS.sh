#!/bin/sh

keytool -genkeypair -dname "cn=$1 $2" -alias release -keypass AWzLF2GuP9khYVLq -keystore app/keystore.jks -storepass AWzLF2GuP9khYVLq -validity 20000 -keyalg RSA -keysize 2048 -storetype JKS