#Création et déploiement d'une application

##Exercice 1

###Avec Argocd CLI

- `argocd app create guestbook --repo https://github.com/argoproj/argocd-example-apps.git --path guestbook --dest-server https://kubernetes.default.svc --dest-namespace default`

- Vérifier les infos de l’application `argocd app get guestbook`
- Check si des ressources kube sont deployées `kubectl get ..`
- Lancer une synchronisation `argocd app sync guestbook`
- Verifier l’état de l’app guestbook
- Afficher les logs de l’app guestbook `argocd app logs guestbook`
- Supprimer l’app guestbook `argocd app delete guestbook`

###Avec ArgoCD UI

- Créer une application guestbook2 via l’interface avec les mêmes paramètres et suivre les mêmes
étapes.

###Avec YAML (declarative)

Créer une application guestbook3 avec les mêmes paramètres en se basant sur ce modèle de yaml:
```yaml
apiVersion: argoproj.io/v1alpha1
kind: Application
metadata:
name: guestbook
namespace: argocd
spec:
project: default
source:
repoURL: https://github.com/argoproj/argocd-example-apps.git
targetRevision: HEAD
path: guestbook
destination:
server: https://kubernetes.default.svc
namespace: guestbook
```

`kubectl apply -n argocd -f application.yaml`


##Exercice 2

- Créer une application demoargo avec ce repos `https://github.com/mkonzali/DemoManifests.git`
- Modifier le port dans service.yaml
- Ajouter un ingress
- Modifier le nombre de replicas dans deployment.yaml
- Ajouter une politique de synchronisation auto
- Ajouter des probes pour déterminer la santé de l’appli