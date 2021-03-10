//required for jenkins; otherwise the rootProject.name will be "workspace"
rootProject.name = "jvm-project-template"

include(
    "project-api",
    "project-impl",
    "project-runtime"
)

