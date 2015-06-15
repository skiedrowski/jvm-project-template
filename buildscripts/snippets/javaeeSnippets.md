Exclude Java EE - API (Spec) Artefact
=====================================

The spec artifacts are fine for coding but not for running against them, since the class files are crippled.

To exclude them from runtime, use the following block

	configurations {
		//	replace spec with implementation at runtime
		runtime.exclude group: 'javax', module: 'javaee-api'
	}
