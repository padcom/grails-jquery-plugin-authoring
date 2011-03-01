import groovy.text.SimpleTemplateEngine
import org.springframework.core.io.FileSystemResource

includeTargets << grailsScript("_GrailsInit")
includeTargets << grailsScript("_GrailsCreateArtifacts")

target ('default': "Creates a JQuery plugin") {
    depends(checkVersion, parseArguments)

    def type = "JQuery plugin"
    promptForName(type: type)
    def name = replaceSpacesWithHyphens(argsMap["params"][0])

    def engine = new SimpleTemplateEngine()
    def templateText = getTemplateText("JQueryPlugin.js")
    def binding = [ 'plugin_name': camelizeName(name) ]
    def outputFileName = "web-app/js/jquery.${name}.js"
    def output = new File("${basedir}/${outputFileName}")
    print "Creating new jQuery plugin in '${outputFileName}'..."
    output.withWriter { writer -> engine.createTemplate(templateText).make(binding).writeTo(writer) }
    println "done"
}

private getTemplateText(String template) {
    def templateFile = new FileSystemResource("${basedir}/src/templates/jquery/${template}")
    if (!templateFile.exists()) {
      templateFile = resolveResources("file:${jqueryPluginAuthoringPluginDir}/src/templates/jquery/${template}")
    }
    return templateFile?.inputStream.text
}

private replaceSpacesWithHyphens(name) {
    name.replaceAll(" ", "-")
}

private camelizeName(name) {
    def capitalize 
    if (!"".respondsTo("capitalize")) {
        // Grails 1.2 and below use Groovy 1.6.x which does not contain the "capitalize" method.
        capitalize = { it[0].toUpperCase() + ((it.size() > 1) ? it[1..-1] : '') }
    } else {
        capitalize = { it.capitalize() }
    }
    def words = name.split(" |-")
    words.eachWithIndex { word, index -> if (index > 0) words[index] = capitalize(words[index]) }
    words.join("")
}
