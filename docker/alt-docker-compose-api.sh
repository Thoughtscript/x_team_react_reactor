#!/usr/bin/env bash

# Mongo DB
docker stop mongodb
docker rm mongodb
docker run -d -p 27017:27017 --name mongodb mongo:3.4

# Redis DB
docker stop redis
docker rm redis
docker run -d --name redis -p 6379:6379 redis:4.0.5-alpine

# X-Team Spring API
cd ../xteamApi/ 
java -jar xteamApi-1.0.0.jar