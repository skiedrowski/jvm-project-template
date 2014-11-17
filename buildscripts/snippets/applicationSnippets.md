From http://www.gradle.org/docs/current/userguide/application_plugin.html

You can run the application by running gradle run. Gradle will take care of building the application classes,
along with their runtime dependencies, and starting the application with the correct classpath. You can launch the
application in debug mode with `gradle run --debug-jvm` (see JavaExec.setDebug()).

The plugin can also build a distribution for your application. The distribution will package up the runtime
dependencies of the application along with some OS specific start scripts. All files stored in `src/dist` will be added
to the root of the distribution. You can run `gradle installApp` to create an image of the application in
`build/install/projectName`. You can run `gradle distZip` to create a zip containing the distribution.


Usage example
=============

    apply plugin: 'application'

    mainClassName = 'info.kiedrowski.XyzMain'
    applicationName = 'Xyz'

    startScripts.doFirst() {
	    //windows needs the dlls within the jar AND in the working directory
    	copy {
	    	from 'src/main/resources/win32-x86-64'
		    into 'src/dist/lib'
    		include '*.dll'
	    }
    	//config.properties
	    copy {
		    from 'config'
    		into 'src/dist/lib/config'
	    	include 'config.properties'
    	}
	    //bat files
    	copy {
	    	from 'config'
		    into 'src/dist/bin'
    		include '*.bat'
    	}
    }