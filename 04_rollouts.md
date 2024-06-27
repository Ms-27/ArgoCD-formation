# Rollouts

### Exercice 1 : blue-green

• Installer argocd-rollouts et kubectl plugin `https://argo-rollouts.readthedocs.io/en/stable/installation/#kubectl-plugin-installation`

```shell
brew install argoproj/tap/kubectl-argo-rollouts
kubectl create namespace argo-rollouts
kubectl apply -n argo-rollouts -f https://github.com/argoproj/argo-rollouts/releases/latest/download/install.yaml
```

• Créer un namespace `rollouts`
• Créer blue-green.yaml pour définir une application `bluegreen` dans le namespace `rollouts` à

partir de cet emplacement `https://github.com/argoproj/rollouts-demo/tree/master/examples/blue-green`

• sync l’application
• check existence du rollout:
`kubectl argo rollouts get rollout bluegreen-demo -n rollouts`
• Deployer une nouvelle version:
`kubectl argo rollouts set image bluegreen-demo -n rollouts bluegreen-demo=argoproj/rollouts-demo:yellow`
• Suivre l’avancement du deployment:
`kubectl argo rollouts get rollout bluegreen-demo -n rollouts --watch`

### Exercice 2 : Canary

• Créer canary.yaml pour définir une application ‘canary dans le namespace ‘rollouts’ à partir de
cet emplacement https://github.com/argoproj/rollouts-demo/tree/master/examples/canary
• sync l’application
• check existence du rollout
• Deployer une nouvelle version ‘yellow’
• Watch le rollout
• Promote pour valider et poursuivre les prochaines étapes :
kubectl argo rollouts promote <nom-rollout>
• Watch le rollout
• Deployer une nouvelle version ‘red’
• Watch le rollout
• Abort : kubectl argo rollouts abort <nom-rollout>
• Revenir sur la version yellow