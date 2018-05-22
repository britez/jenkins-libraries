//Utils for github

def clone(Map params) {
    def url = params.url
    def branch = params.branch?params.branch:'develop'

    try {
        log.info("Attempt to clean workspace")
        cleanWs()
        log.info("Workspace cleaned successfully")
        log.info("Attempt to clone repository: ${url}")
        checkout changelog: true, poll: true, scm: [$class: 'GitSCM', branches: [[name: branch]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'WipeWorkspace'], [$class: 'CleanCheckout'], [$class: 'UserIdentity', email: 'rfsc-jenkins@cencosud.cl', name: 'Jenkins CI']], submoduleCfg: [], userRemoteConfigs: [[url: url]]]
        log.info("Repository cloned successfully")
    } catch (Exception e) {
        log.error("Error trying to clone repository: ${e.getMessage()}")
        throw e
    }
}