if (System.getProperty("java.vm.specification.version") != "11") {
    throw IllegalArgumentException("Build config error. This build needs Java 11, but we've got ${System.getProperty("java.vm.specification.version")}.")
}