//the simple form does not seem to work in a separate file!?
//tasks.wrapper {
tasks.named<Wrapper>("wrapper") {
    gradleVersion = "6.8.1"
    distributionType = Wrapper.DistributionType.ALL
}