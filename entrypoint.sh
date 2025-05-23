#!/bin/bash

# Optional: clear the cache on each run
rm -rf /tmp/wdm-cache/*

# Start the Java app
exec java \
  -Dwebdriver.chrome.driver=/tmp/wdm-cache \
  -DopenAiKey="${OPENAI_API_KEY}" \
  -jar /ai/mcpdemo.jar
