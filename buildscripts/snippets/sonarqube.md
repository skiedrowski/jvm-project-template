SonarQube
========
just add the plugin to the root project

	plugins {
		id "org.sonarqube" version "2.2.1"
	}

and make sure your sonar instance is configured in (systemwide or project specific) `gradle.properties` (or in `sonarqube` closure)

Example

	systemProp.sonar.host.url=https://<HOST>:<PORT>/sonar
	systemProp.sonar.login=<LOGIN>
	systemProp.sonar.password=<PASSWORD>
