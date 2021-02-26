#!/bin/bash

./gradlew clean build

#./gradlew bootBuildImage --imageName=akorchevskyi/springapp

docker run --expose=8080 -p 8080:8080 -d docker.io/akorchevskyi/springapp:latest
