#!/bin/bash
# Obtain the docker container id and stop it
echo "STOPPING THE CURRENTLY RUNNING DOCKER CONTAINER...."
/opt/homebrew/bin/docker stop $(/opt/homebrew/bin/docker ps -a -q)
echo "DONE"