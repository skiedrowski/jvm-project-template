Enable Logging
==============
Only useful for small projects, otherwise the log gets polluted easily.

For all test tasks

    tasks.withType(Test) {
        testLogging {
            events 'passed'
        }
    }

only within a special test task

    task sequentialTest(type: Test) {
        testLogging {
            events 'passed'
        }
    }

Ignore failures
===============

see ignoreTestsFailuresIfConfiguredInEnv.gradle

Execute tests in parallel
=========================

    test.maxParallelForks = 4
    
or

    test.maxParallelForks = Runtime.runtime.availableProcessors() / 2

Using JUnit Categories
======================

see execSequentialTestsSeparately.gradle
