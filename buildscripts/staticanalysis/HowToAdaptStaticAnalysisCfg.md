Assumption
==========

The results of the static code analysis should be monitored and all findings should be fixed.

* Monitoring and not fixing the findings is a waste of CPU cycles.
* Monitoring and fixing _some_ of the findings will inevitably lead to more and more findings as the project grows. How do you decide which findings need a fix?
* Monitoring and fixing _all_ of the findings is the only way to make static code analysis significant.

How to configure the tools
==========================
Priority 1: It should be possible to stay at 0 findings over the whole lifetime of the project.

* Only activate relevant rules.
* Use `@SuppressWarnings` or something similar if a concrete finding is accepted but you want to keep the rule active.
* Decide whether it is okay to use a different ruleset for tests.

Priority 2: When updating a static analysis tool, it should be easy to check which new rules you want to activate.

Priority 3: Configure the tools so their rules to not overlap too much

* it seems impossible to avoid overlaps due to inaccuracies in the rules


PMD
===
http://pmd.sourceforge.net/pmd-5.2.0/customizing/howtomakearuleset.html

PMD suggests that a project configures its own ruleset which delegates to the rules provided by the PMD project. This
allows replacing the PMD with a newer version without the need to reapply the whole configuration to the newer version.

If a ruleset is referenced as a whole, changes to the ruleset (by a newer PMD version) will activate automatically.
If a rule is referenced directly, changes to the ruleset will not activate automatically.
If a rule needs to be parameterized, it needs to be referenced directly (?).

_Last Version Update: 2014-10-24: V5.2.0 (not available on Maven Central yet, so we stick to 5.1.3)_

Best Practices
--------------
* Reference the rulesets as a whole.
* Exclude unwanted rules.
* If a rule needs to be modified
  * it needs to be excluded from the ruleset and
  * referenced explicitly (with modifications).
  * Example: TooManyMethods in pmdruleset.xml

Suppress Warnings
-----------------
using Java Annotation: http://pmd.sourceforge.net/pmd-5.2.0/usage/suppressing.html

Checkstyle
==========
Best Practices
--------------
* reference file is checkstyle_checks.xml of the currently used checkstyle version
* create and adapt copies named checkstyle_main_rules.xml, checkstyle_test_rules.xml

Deactivating a check: add `<property name="severity" value="ignore"/>` to the `module`

Parameterizing a check: modify properties as desired.

Do NOT modify the order of the checks.

That way it should be possible to upgrade to newer checkstyle versions without too much hassle.

Suppress Warnings
-----------------
http://checkstyle.sourceforge.net/config.html describes how to deal with suppressions in checkstyle. The provided config
supports the `@SuppressWarnings` style out of the box.

Since the docs are quite sparse, here are the 2 relevant parts:

Config snippet:

    <module name="SuppressWarningsFilter" />
    <module name="TreeWalker">
           ...
       <module name="SuppressWarningsHolder" />
    </module>

Usage examples

    @SuppressWarnings("checkstyle:methodlength")
    public void someLongMethod() throws Exception { ... }


    @SuppressWarnings({"checkstyle:executablestatementcount", "checkstyle:methodlength"})
    public void method() { ... }

The `checkstyle:` prefix is optional but recommended. Then the name of the check is added in lower case.

Findbugs
========
Best Practices
--------------
Add rules to exclude to an exclusions file.

Suppress Warnings
-----------------
Either add a findbugs jar to the classpath and use `@SuppressFBWarning` or use file-specific exclusions in the
aforementioned exclusions file.


Code Formatting
===============
I prefer to limit code formatting rules to a minimum since it is hard to match the configuration of code formatters (IDE) and code
formatter rules. Just describe the most important aspects of code formatting via rules.