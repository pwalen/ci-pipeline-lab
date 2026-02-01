import groovy.json.JsonSlurper

def model = new JsonSlurper().parse(new File('out/runtime-model.json'))
assert model != null : "Runtime model is empty"
println "Runtime model validation OK."