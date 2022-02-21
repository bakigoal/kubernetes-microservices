#!/usr/bin/env bash

kubectl apply -f ./k8s/deployment-a.yml
kubectl apply -f ./k8s/deployment-b.yml
kubectl apply -f ./k8s/service-a.yml
kubectl apply -f ./k8s/service-b.yml
kubectl apply -f ./k8s/ingress.yml