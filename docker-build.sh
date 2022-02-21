#!/usr/bin/env bash

./gradlew clean bootJar

docker build -t bakigoal/service-a ./service-a
docker push bakigoal/service-a

docker build -t bakigoal/service-b ./service-b
docker push bakigoal/service-b