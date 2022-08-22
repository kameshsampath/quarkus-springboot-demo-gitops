# Java CI and GitOps with Drone, Argo CD and Gitea

This repository act as GitOps repo for the <https://github.com/kameshsampath/quarkus-springboot-demo> app.

## Prerequisites

- [Docker for Mac/Windows/Linux](https://www.docker.com/products/docker-desktop)
- [httpie](https://httpie.org/)
- [Drone CLI](https://docs.drone.io/cli/install/)

## Setup DAG Stack

You need a environment that can help you do CI and GitOps. You can setup one locally as described here <https://github.com/kameshsampath/dag-stack.git>.

## GitOps

Update the App helm `$APP_GITOPS_HOME/helm_vars/values.yaml`

```shell
envsubst < "$APP_GITOPS_HOME/helm_vars/values.tpl.yaml" > "$APP_GITOPS_HOME/helm_vars/values.yaml"
```

Commit and push the code to git repo,

```shell
git commit -a -m "Init GitOps"
git push origin main
```

Deploy Argo CD application Kubernetes cluster that will use our GitOps repo,

```shell
kustomize build "$APP_GITOPS_HOME"| envsubst | kubectl apply -f -
```
