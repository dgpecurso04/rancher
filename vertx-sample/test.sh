#!/bin/sh
curl http://localhost:9090/api/primero?mode=abc
curl "http://localhost:9090/api/suma?numA=34&numB=23"
curl "http://localhost:9090/api/suma?numA=23&numB=2"
curl "http://localhost:9090/api/resta?numA=8&numB=2"
curl "http://localhost:9090/api/resta?numA=100&numB=90"
curl "http://localhost:9090/api/resta?numA=5&numB=2"
curl "http://localhost:9090/api/resta?numA=15&numB=4"
curl "http://localhost:9090/api/mult?numA=7&numB=7"
curl "http://localhost:9090/api/mult?numA=10&numB=20"
curl "http://localhost:9090/api/mult?numA=8&numB=9"
curl "http://localhost:9090/api/div?numA=1&numB=2"
curl "http://localhost:9090/api/div?numA=2&numB=1"
curl "http://localhost:9090/api/div?numA=9&numB=3"
curl "http://localhost:9090/api/div?numA=100&numB=10"

