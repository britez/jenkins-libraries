package org
import org.Logger

class GitHelper {

    String credentials
    String repoBase

    def clone(repoName, branch) {
        try {
            Logger.info("Attempt to clean workspace")
            cleanWs()
            Logger.info("Workspace cleaned successfully")
            def repoURL = "${repoBase}/${repoName}.git"
            Logger.info("Attempt to clone repository: ${repoURL}")
            checkout changelog: true, poll: true, scm: [$class: 'GitSCM', branches: [[name: branch]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'WipeWorkspace'], [$class: 'CleanCheckout'], [$class: 'UserIdentity', email: 'rfsc-jenkins@cencosud.cl', name: 'Jenkins CI']], submoduleCfg: [], userRemoteConfigs: [[url: repoURL]]]
            Logger.info("Repository cloned successfully")
        } catch (Exception e) {
            Logger.error("Error trying to clone repository: ${e.getMessage()}")
            throw e
        }
    }
}
//https://github.com/britez/ms-example-rest.git