
class GitHelper {

    String credentials
    String repoBase

    def clone(repoName, branch) {
        try {
            log.info("Attempt to clean workspace")
            cleanWs()
            log.info("Workspace cleaned successfully")
            def repoURL = "${repoBase}/${repoName}.git"
            log.info("Attempt to clone repository: ${repoURL}")
            checkout changelog: true, poll: true, scm: [$class: 'GitSCM', branches: [[name: branch]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'WipeWorkspace'], [$class: 'CleanCheckout'], [$class: 'UserIdentity', email: 'rfsc-jenkins@cencosud.cl', name: 'Jenkins CI']], submoduleCfg: [], userRemoteConfigs: [[url: repoURL]]]
            log.info("Repository cloned successfully")
        } catch (Exception e) {
            log.error("Error trying to clone repository: ${e.getMessage()}")
            throw e
        }
    }
}