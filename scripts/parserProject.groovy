// input: YAML string
//// output: runtime model Map
//
//import groovy.yaml.YamlSlurper
//
//class ParserProject {
//
//    Map parse(String yamlText) {
//        if (yamlText == null || yamlText.trim().isEmpty()) {
//            throw new IllegalArgumentException('YAML input must not be empty')
//        }
//
//        def slurper = new YamlSlurper()
//        def rawConfig = slurper.parseText(yamlText)
//
//        if (!(rawConfig instanceof Map)) {
//            throw new IllegalArgumentException('Parsed YAML must be a Map')
//        }
//
//        return rawConfig as Map
//
//    }
//
//}

