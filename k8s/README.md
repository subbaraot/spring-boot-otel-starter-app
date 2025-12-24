# Instructions to deploy the TODO API in a Kubernetes cluster

## Pre-requisites

1. Access to a k8s cluster. *The instructions in this file have been verified with a local k8s cluster running via Docker Desktop.*
2. A version of `kubectl` compatible with the target cluster.
3. A version [`helm`](https://helm.sh/) compatible with the target cluster.
4. Configure the following helm repositories
    - Prometheus - `helm repo add prometheus-community https://prometheus-community.github.io/helm-charts`.
    - Grafana - `helm repo add grafana https://grafana.github.io/helm-charts`.

## Setup

To deploy all the applications, run the commands from a Terminal at the directory containing this file.

### Install Prometheus

```bash
helm install prometheus prometheus-community/prometheus -f custom-values/prometheus-values.yaml
```

### Install Loki

```bash
helm install loki grafana/loki -f custom-values/loki-values.yaml
```

### Install Grafana

```bash
helm install grafana grafana/grafana --set persistence.enabled=true
```

### Install Temp

```bash
helm install tempo grafana/tempo
```

### Install OpenTelemetry Collector

```bash
kubectl apply -f otelcol.yaml
```

### Install MongoDB

```bash
kubectl apply -f mongodb.yaml
```

### Build TODO API Image

```bash
cd ../todo-api
docker build -t todo-api:1.0 .
cd ../k8s
```

### Install TODO API

```bash
helm install todo-api todo-api-chart/
```

### Access the applications

Verify if everything is running by running `kubectl get pods`. *Ignore if `prometheus node-exporter` pod fails*.

The Grafana instance requires credentials. The default username is "admin" and the password can be acquired by running the following command

```bash
 kubectl get secret --namespace default grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo
```

#### Setup Port-forwarding

##### Grafana

1. Open a new Terminal.
2. Run `kubectl port-forward svc/grafana 3000:80`.
3. Verify access by opening a browser and visiting [`localhost:3000`](localhost:3000).

##### TODO API

1. Open a new Terminal.
2. Run `kubectl port-forward svc/todo-api 8585:80`.
3. Verify access by opening a browser and visiting [`localhost:8585`](localhost:8585).
