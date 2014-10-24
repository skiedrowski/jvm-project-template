/*
 * Environment specific configuration file for the gradle build
 */

// environment specific properties
environments {
    dev {
        ignoreTestFailures = false
    }

    // continuous integration server
    ci {
        ignoreTestFailures = true
    }
}
