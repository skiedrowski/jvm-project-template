see https://docs.gradle.org/current/dsl/org.gradle.api.artifacts.ResolutionStrategy.html


	configurations.all {
    	resolutionStrategy {
    		//need to use exactly this version for glassfish
    		force 'org.slf4j:slf4j-api:1.5.11'
    	}
    }
