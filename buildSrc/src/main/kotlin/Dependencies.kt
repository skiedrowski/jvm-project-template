// see https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/
// for an explanation of the idea

object Ver {
    const val kotlin = "1.4.31"
    const val apache_http_core = "4.4.9"
    const val apache_http_client = "4.5.5"

    const val junit = "5.7.+"
    const val mockito_kotlin = "1.6.+"
    const val hamkrest = "1.8.+"
    const val hamcrest = "1.3"
    const val mockito = "2.28.2"
}

object Deps {
    const val kt_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Ver.kotlin}"
    val apache_http_core = "org.apache.httpcomponents:httpcore:${Ver.apache_http_core}"
    val apache_http_client = "org.apache.httpcomponents:httpclient:${Ver.apache_http_client}"

    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Ver.kotlin}"
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Ver.junit}"
    const val junit_engine = "org.junit.jupiter:junit-jupiter-engine:${Ver.junit}"
    const val mockito_kotlin = "com.nhaarman:mockito-kotlin:${Ver.mockito_kotlin}"
    const val hamkrest = "com.natpryce:hamkrest:${Ver.hamkrest}"

    const val hamcrest_integration = "org.hamcrest:hamcrest-integration:${Ver.hamcrest}"
    const val mockito_core = "org.mockito:mockito-core:${Ver.mockito}"
    const val mockito_junit_jupiter = "org.mockito:mockito-junit-jupiter:${Ver.mockito}"
}
