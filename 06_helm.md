# ArgoCD & Helm

### Exercice 1
Créé une application ‘helmargo’ via un fichier yaml de type Application :
- https://github.com/mkonzali/DemoHelmChart.git
- Namespace spring-apps-dev
- Projet spring-apps
- Helm.releaseName : helmargo
- valueFiles : values.yaml, dev-values.yaml
Check que l’application est en état synced et helthy
Ajouter le nom de domaine dans le fichier hosts
Explorer le Cm et le Secret, modifier leurs valeurs et tester avec l’endpoint http://helmargo.local/details
- Passer un paramètre dans le yaml d’application image.tag= 2.0.0 et surveiller l’appli
- Ajouter un champ à ignorer avec ignoreDifferences et tester

### Exercice 2
Pour plus de security créer un repos séparé pour les secrets
Pour que ce soit accessibles par les devs, créer un repos séparé pour les configs
Créer 2 nouvelles appli argocd à partir de ces 2 repos
