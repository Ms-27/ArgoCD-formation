#Installation et configuration de l'ArgoCD sur un cluster local

##Demarrer le cluster minikube
• -> `kubectl create namespace argocd`
• -> `kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml`

##Pointer le context sur le namespace d’argocd
    `kubectl config set-context --current --namespace=argocd`

• Verifier les ressources installés : pods, services, configmaps, secrets ..
• Accéder à l’UI d’ArgoCD

o Exposer le service
    `kubectl port-forward svc/argocd-server -n argocd 8080:443`
o Aller sur https://localhost:8080

o Recuperer le mot de passe du user admin depuis le secret 
    `kubectl get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d`

o Explorer et modifier le mot de passe de l’admin

##Installer le CLI

o `curl -sSL -o argocd-linux-amd64 https://github.com/argoproj/argo-cd/releases/latest/download/argocd-linux-amd64`
o `sudo install -m 555 argocd-linux-amd64 /usr/local/bin/argocd`
o `rm argocd-linux-amd64`

##Login avec la commande `argocd login localhost:8080`
