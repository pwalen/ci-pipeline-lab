import groovy.json.JsonOutput
import groovy.yaml.YamlSlurper

class ParserProject {

   Object parse(String yamlText) {
       if (yamlText == null || yamlText.trim().isEmpty()) {
           throw new IllegalArgumentException('YAML input must not be empty')
       }

       def slurper = new YamlSlurper()
       def rawConfig = slurper.parseText(yamlText)

       if (!(rawConfig instanceof Map || rawConfig instanceof List)) {
           throw new IllegalArgumentException('Parsed YAML must be a Map')
       }

       return rawConfig

   }
}


def yamlText = new File('config/android-projects.yaml').text
def parser = new ParserProject()
def model = parser.parse(yamlText)
new File ('out/runtime-model.json').text = JsonOutput.prettyPrint(JsonOutput.toJson(model))