#!/bin/bash

set -e  # Exit on error

services=(CompanyMS Config-Server gateway JobMS ReviewMS service-reg)

echo "Building JARs..."
for service in "${services[@]}"; do
  echo "Building $service"
  if (cd "$service" && mvn -s "/c/Users/abhishek.kamti/.m2/settings-default.xml" clean package -DskipTests); then
    echo "$service built successfully"
  else
    echo "Failed to build $service"
    exit 1
  fi
done

echo -e "\n Rebuilding Docker images..."
docker compose build

echo -e "\n Restarting containers..."
docker compose up -d

echo -e "\n All services are up and running!"  
