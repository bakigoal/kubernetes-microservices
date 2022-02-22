### Install on minikube

```shell
minikube addons enable ingress
minikube tunnel
```

```shell
./docker-build.sh

k apply -f ./k8s/deployment-mongo.yml
k apply -f ./k8s/deployment-redis.yml
k apply -f ./k8s/deployment-a.yml
k apply -f ./k8s/deployment-b.yml
k apply -f ./k8s/ingress.yml
```

```shell
http :80/a/v1/a
```