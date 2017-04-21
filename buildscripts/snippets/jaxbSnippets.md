This build script is pretty similar to [jaxwsSnippets](jaxwsSnippets.md).

	configurations { jaxb }

	dependencies { jaxb 'com.sun.xml.bind:jaxb-xjc:2.2.4-1' }

	//approx. 2014: it is currently impossible to put that under ${build} since intelliJ would not see it
	ext.genSourcesDir = "src/jaxb/java"

	clean { delete genSourcesDir }

	sourceSets {
		jaxb

		main {
			compileClasspath += sourceSets.jaxb.output
			runtimeClasspath += sourceSets.jaxb.output
		}

		test {
			compileClasspath += sourceSets.jaxb.output
			runtimeClasspath += sourceSets.jaxb.output
		}
	}

	jar { 
		from sourceSets.jaxb.output
		from sourceSets.....output
	}

	task jaxb () {
		doLast {
			//System.setProperty('javax.xml.accessExternalSchema', 'all')
			ant.taskdef(name: 'xjc', classname: 'com.sun.tools.xjc.XJCTask', classpath: configurations.jaxb.asPath)
			ant.jaxbTargetDir = file( genSourcesDir )
			ant.jaxbTargetDir.mkdirs()
			ant.xjc(
				destdir: "${ant.jaxbTargetDir}", 
				/*encoding: 'UTF-8',*/ 
				extension: 'true', 
				target: '2.1',
				package: 'TODO set package name',
				schema: 'TODO full path to .xsd file'
			) {
				schema(dir: 'src/main/resources', includes: "**/*.xsd")
				binding(dir: 'src/main/resources', includes: "**/*.xjb")
				arg(value: '-npa')
			}
		}
	}
	compileJava.dependsOn(jaxb)
	