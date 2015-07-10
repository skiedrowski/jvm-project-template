// example file for 'loadVersions.gradle'

// version numbers

// API/Spec Dependencies
javax {
	validation = '1.0.0.GA' // JEE 6 contains 1.0.0
	persistence = '2.1.0'
}

//implementation dependencies
hibernate.validator = '4.3.1.Final' //optional; used during tests by mpt

junit = '4.+'
hamcrest = '1.3'
mockito = '1.9.5'

/*
// access in *.gradle files via
apply from: "$buildscriptDir/loadVersions.gradle"
dependencies {
    testCompile "junit:junit-dep:$ver.junit"
    compile "javax.validation:validation-api:$ver.javax.validation"
}
*/
