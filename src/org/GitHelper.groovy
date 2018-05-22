package org.jenkins

class GitHelper {

    String credentials
    String repoBase

    def clone(repoName, branch) {
        try {
            echo "Attempt to clean workspace"
            cleanWs()
            echo "Workspace cleaned successfully"
            def repoURL = "${repoBase}/${repoName}.git"
            echo "Attempt to clone repository: ${repoURL}"
            checkout changelog: true, poll: true, scm: [$class: 'GitSCM', branches: [[name: branch]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'WipeWorkspace'], [$class: 'CleanCheckout'], [$class: 'UserIdentity', email: 'rfsc-jenkins@cencosud.cl', name: 'Jenkins CI']], submoduleCfg: [], userRemoteConfigs: [[url: repoURL]]]
            echo "Repository cloned successfully"
        } catch (Exception e) {
            echo "Error trying to clone repository: ${e.getMessage()}"
            throw e
        }
    }
}
//https://github.com/britez/ms-example-rest.git