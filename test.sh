#!/bin/bash

curl -H "Content-Type: application/json" -X POST -d '{ "foo": 10 }' http://localhost:5050/graphql