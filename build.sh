#!/usr/bin/env bash

lein do clean, ring uberjar

docker build -t urho .