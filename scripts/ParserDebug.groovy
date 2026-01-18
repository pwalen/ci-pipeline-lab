import groovy.yaml.YamlSlurper
import groovy.json.JsonOutput

class ParserProject {

    Map parse(String yamlText) {
        if (yamlText == null || yamlText.trim().isEmpty()) {
            throw new IllegalArgumentException("YAML input must not be empty")
        }

        def slurper = new YamlSlurper()
        def rawConfig = slurper.parseText(yamlText)

        if (!(rawConfig instanceof Map)) {
            throw new IllegalArgumentException("Parsed YAML must be a Map")
        }

        return rawConfig as Map
    }

}

// --- Debug runner ---

//// Load Parser class explicitly
//evaluate(new File("scripts/ParserProject.groovy"))

// Read YAML file as raw text (outside of Parser)
def yamlText = new File("config/android-projects.yaml").text

// Create Parser instance
def parser = new ParserProject()


// Execute extract phase (YAML -> raw AST)
def rawConfig = parser.parse(yamlText)

// Print rawConfig in readable JSON form
println JsonOutput.prettyPrint(
        JsonOutput.toJson(rawConfig)
)

