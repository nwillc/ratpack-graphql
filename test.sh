#!/bin/bash

for SAMPLE in samples/*.json
do
    curl -H "Content-Type: application/json" -X POST -d @${SAMPLE} http://localhost:5050/graphql
    echo
done