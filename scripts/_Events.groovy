eventInstallTemplates = {
    copyTemplate("JQueryPlugin.js")
}

def copyTemplate(template) {
    Ant.copy(
        file: "${jqueryPluginAuthoringPluginDir}/src/templates/jquery/${template}", 
        tofile: "${basedir}/src/templates/jquery/${template}", 
        overwrite: true)
}
