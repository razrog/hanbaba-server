#!/usr/bin/env bash 
SERVER_PATH=SERVER_PATH=/home/ec2-user/dev/services/hanbaba/hanbaba-server/
BUILD_JAR=hanbaba-server-1.0-SNAPSHOT.jar

cd ${SERVER_PATH}
./gradlew clean build
java -jar ${SERVER_PATH}${BUILD_JAR} >/dev/null 2>&1 &