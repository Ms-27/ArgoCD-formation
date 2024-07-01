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