This build script is pretty similar to [jaxwsSnippets](jaxwsSnippets.md).

	configurations { jaxb }

	dependencies { jaxb 'com.sun.xml.bind:jaxb-xjc:2.2.4-1' }

	//it is currently impossible to put that under ${build} since intelliJ would not see it
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

	// skip analysis for source folder with generated sources
	checkstyleJaxb.enabled = false
	findbugsJaxb.enabled = false
	pmdJaxb.enabled = false

	task jaxb () {
		// perform actions
		doLast {
			ant.taskdef(name: 'xjc', classname: 'com.sun.tools.xjc.XJCTask', classpath: configurations.jaxb.asPath)
			ant.jaxbTargetDir = file( genSourcesDir )
			ant.jaxbTargetDir.mkdirs()
			ant.xjc(
					destdir: "${ant.jaxbTargetDir}",
					package: 'TODO set package name',
					schema: 'TODO full path to .xsd file'
			)
		}
	}
	compileJava.dependsOn(jaxb)