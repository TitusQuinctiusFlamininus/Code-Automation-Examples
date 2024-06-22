#!/bin/bash
# Remove any process hanging on to port 3000
/usr/sbin/lsof -ti:3000 | xargs kill