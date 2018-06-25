#!/usr/bin/env bash

docker stop mongodb
docker rm mongodb
docker run -d -p 27017:27017 --name mongodb mongo:3.4