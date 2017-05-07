#!/bin/bash

for SAMPLE in $(ls samples/*.json)
do
    echo "Query: " $(basename ${SAMPLE} .json)
    curl -H "Content-Type: application/json" -X POST -d @${SAMPLE} http://localhost:5050/graphql
    echo
done