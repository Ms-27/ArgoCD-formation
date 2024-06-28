# Integration CI

Créer un script (shell, python ou groovy) qui permet de lancer un déploiement en utilisant argocd CLI
Paramètres d’entrée :
• App_name
• app_version
Variables d’environnement :
• ARGOCD_SERVER
• ARGOCD_TOKEN
Etapes :
• Check si l’appli existe sinon message
• Update de la version
• Déploiement

Essayer de modifier ce script pour utiliser l’Api Rest

```groovy
withEnv(["ARGOCD_SERVER=$argocdServer", ARGOCD_AUTH_TOKEN='+ ARGOCD_TOKEN]) {
        def chartChecksum = sh(
                script: "echo helm-checksum-${BUILD_NUMBER} | sha256sum | head -c 64",
                returnStdout: true
                )
        // shell command line return an interger status code
        def argoCDGetResult = sh(
                script: "argocd app get ${applicationName} --grpc-web",
                returnStatus: true
                )
        // Target application is found in the Argocd CRD apps
        if (argoCDGetResult == 0) {
            sh "argocd app set ${applicationName} --grpc-web  -p image.tag=${config.app_version} "
            sh "argocd app sync ${applicationName} --grpc-web"
            sh "argocd app wait ${applicationName} --grpc-web"
            sh "echo \"https://${argocdServer}/applications/${applicationName} \" "
            return false
        }
        else {
            println "Application ${applicationName} was not found . Please check the parameters"
            sh "exit 1"
            return true
        }
    }
```
