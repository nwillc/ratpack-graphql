#!/bin/bash

curl -H "Content-Type: application/json" -X POST -d @samples/companies.json http://localhost:5050/graphql