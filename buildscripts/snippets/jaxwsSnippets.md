The following build.gradle is take from a module containing only jax-ws (wsdl) resources.

	description = 'jax-ws (wsdl) resources'

	configurations { jaxws }

	dependencies { jaxws 'com.sun.xml.ws:jaxws-tools:2.2.8' }

	//it is currently impossible to put that under ${build} since intelliJ would not see it
	ext.genSourcesDir = "src/wsdlGen/java"

	clean { delete genSourcesDir }

	sourceSets {
		wsdlGen

 		main {
     		compileClasspath += sourceSets.jaxws.output
         	runtimeClasspath += sourceSets.jaxws.output
        }

        test {
        	compileClasspath += sourceSets.jaxws.output
        	runtimeClasspath += sourceSets.jaxws.output
        }
	}

	// skip analysis for source folder with generated sources
	checkstyleWsdlGen.enabled = false
	pmdWsdlGen.enabled = false
	findbugsWsdlGen.enabled = false

	task wsimport {
		//If this task fails with "accessExternalSchema" disallows "file" you're probably using jdk 8 or newer.
		//One workaround is the following setting.
		//
		//    System.setProperty('javax.xml.accessExternalSchema', 'file')
		//
		//see http://stackoverflow.com/questions/22044397/how-to-surpass-gradle-wsimport-task-jdk-8-access-restrictions
		//
		//the recommended way to stick to JDK 7 is putting the following line into gradle.properties of the root project
		//org.gradle.java.home=/Library/Java/JavaVirtualMachines/jdk1.7.0_55.jdk/Contents/Home

		def destDir = file(genSourcesDir)
		outputs.dir(destDir)

		def wsdlSrcDir = 'src/main/resources/wsdl'
		inputs.file(file(wsdlSrcDir))

		doLast {
			//TODO set correct names of wsdl files to import
			def wsdls = ['one.wsdl', 'two.wsdl']
			wsdls.each { wsdl ->
				def wsdlSrc = file("$wsdlSrcDir/$wsdl")

				sourceSets.main.output.classesDir.mkdirs()
				destDir.mkdirs()

				ant {
					taskdef(name: 'wsimport',
							classname: 'com.sun.tools.ws.ant.WsImport',
							classpath: configurations.jaxws.asPath)
					wsimport(keep: true,
							destdir: sourceSets.main.output.classesDir,
							sourcedestdir: destDir,
							wsdl: wsdlSrc,
							extension: true,
							package: "TODO fill in target package name",
							wsdllocation: "/wsdl/$wsdl",
							verbose: false)
				}
			}
		}
	}
	compileJava.dependsOn(wsimport)
