#!/bin/sh

cd /home/ec2-user/star

docker-compose build
docker-compose up -d