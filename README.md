# Quarkus Spring Boot Demo - GitOps

This repository act as GitOps repo for the <https://github.com/kameshsampath/quarkus-springboot-demo> app.

Please check the <https://github.com/kameshsampath/dag-stack> on how to use this repo.

## Environment

Let us setup the following variables for convenience,

```shell
# directory where you have cloned the dag-stack repo
export DAG_HOME="<directory where you have cloned the dag-stack repo>"
# the argocd project to use
export ARGO_CD_PROJECT="default"
export GIT_URL="http://gitea-127.0.0.1.sslip.io:30950"
export GIT_USER="<your gitea user>
export GIT_REPO="http://gitea-127.0.0.1.sslip.io:30950/${GIT_USER}/quarkus-springboot-demo-gitops.git"
```

## GitOps

Update the App helm `$APP_HOME/helm_vars/values.yaml`

```shell
envsubst < "$APP_HOME/helm_vars/values.tpl.yaml" > "$APP_HOME/helm_vars/values.yaml"
```

Commit and push the code to git repo,

```shell
git commit -a -m "Init GitOps"
git push origin main
```

Deploy Argo CD application Kubernetes cluster that will use our GitOps repo,

```shell
envsubst < $APP_HOME/app.yaml | kubectl apply -f -
```
