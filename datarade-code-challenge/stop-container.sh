#!/bin/bash
# Obtain the docker container id and stop it
/opt/homebrew/bin/docker stop $(/opt/homebrew/bin/docker ps -a -q)