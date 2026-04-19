#!/bin/bash
echo "Build du projet Maven multi-module..."
mvn clean install -DskipTests
echo "Build terminé."
