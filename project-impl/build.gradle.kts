description = """implementation classes

A typical user of this project should not rely on these classes as they are implementation details."""

dependencies {
	implementation(project(":project-api"))
	testImplementation(project(path = ":project-api", configuration = "testJar"))
}