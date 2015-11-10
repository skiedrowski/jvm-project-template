//transform version.properties contents

	processResources {
		from(sourceSets.main.resources.srcDirs) {
			include '**/version.properties'
			filter { String line ->
				line.replace "@appVersion@", version
			}
		}
	}