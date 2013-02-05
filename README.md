robocode-pmj-dacruzer
=====================

Example project showing how to develop robocode robots using the Maven build system.  To install this package:

1. Install robocode
-------------------

Install both the robocode basic distribution and the robocode testing plugin.  Both of these setup.jar files are available for version 1.7.4.4 [here](https://sourceforge.net/projects/robocode/files/robocode/1.7.4.4/). 

Be sure to run the system manually from the command line to ensure that you have the appropriate Java installed and so forth. 

2. Install Maven
----------------

Start by following the [directions on installing Maven](http://maven.apache.org/download.cgi).

Be sure to run mvn --version to verify that it is correctly installed.  This package has been tested using Maven 3.0.4.

3. Download this robocode-pmj-dacruzer package
----------------------------------------------

For those who do not know about git, the easiest way is to click the "ZIP" button at the top of this page, which will download the latest version of this repository as a .zip file. 

For those who know about git, you will want to clone it. 

4. Install robocode jar files into your local Maven repository
--------------------------------------------------------------

Robocode binaries are not provided as part of public Maven repositories, so the next step is to install the seven jar files needed for compilation and testing into your local repository.   You accomplish this by changing directory to robocode/libs and executing the following seven commands:

```
mvn install:install-file -Dfile=robocode.jar -DartifactId=robocode  -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.testing.jar -DartifactId=robocode.testing -DgroupId=net.sourceforge.robocode  -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.battle-1.7.4.4.jar -DartifactId=robocode.battle -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.core-1.7.4.4.jar   -DartifactId=robocode.core   -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.host-1.7.4.4.jar   -DartifactId=robocode.host   -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.repository-1.7.4.4.jar -DartifactId=robocode.repository   -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=picocontainer-2.14.2.jar -DgroupId=net.sourceforge.robocode -DartifactId=picocontainer -Dversion=2.14.2 -Dpackaging=jar -DgeneratePom=true
```

A typical output from one of these commands might be:

```
[~/robocode/libs]-> mvn install:install-file -Dfile=robocode.jar -DartifactId=robocode -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-install-plugin:2.3.1:install-file (default-cli) @ standalone-pom ---
[INFO] Installing /Users/johnson/robocode/libs/robocode.jar to /Users/johnson/.m2/repository/net/sourceforge/robocode/robocode/1.7.4.4/robocode-1.7.4.4.jar
[INFO] Installing /var/folders/__/qq1ydtj56n3fxtccjh9k6dk80000gn/T/mvninstall1027288217865601684.pom to /Users/johnson/.m2/repository/net/sourceforge/robocode/robocode/1.7.4.4/robocode-1.7.4.4.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.454s
[INFO] Finished at: Mon Jan 28 14:28:31 HST 2013
[INFO] Final Memory: 7M/309M
[INFO] ------------------------------------------------------------------------
[~/robocode/libs]-> 
```
5.  Build and test the system
-----------------------------

Now that everything is installed, build and test the system. You use the standard Maven 'test' target.  There are two special aspects of this process of which you should be aware.  

First, the RobotTestBed class and the Maven POM file requires the definition of a System Property called "robocode.home", which should point to the directory where Robocode is installed. To define this property and its value, use the -Drobocode.home=robocodeHomeDirectory command line option, as illustrated below.

Second, the Robocode runtime system needs your newly developed robot to be known to the system during testing.  To accomplish this, the pom.xml file defines a "copy-resource" goal that copies your robot binary from the target/classes directory to the robocode.home/robots directory after completing compilation. Take a look at the pom.xml file to see how this is done.  

Note that this approach does not remove these files from the robocode installation after the testing process is done. 

Here is an example of the command line used to build and test the system, along with the output.

```shell
[~/projecthosting/github/robocode-pmj-dacruzer]-> mvn -Drobocode.home=/Users/johnson/robocode test
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building dacruzer 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ dacruzer ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:2.3.2:compile (default-compile) @ dacruzer ---
[INFO] Compiling 1 source file to /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:copy-resources (copy-resources) @ dacruzer ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ dacruzer ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:2.3.2:testCompile (default-testCompile) @ dacruzer ---
[INFO] Compiling 3 source files to /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.10:test (default-test) @ dacruzer ---
[INFO] Surefire report directory: /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running pmj.TestDaCruzerFiring
Loaded net.sf.robocode.api
Loaded net.sf.robocode.core
Loaded net.sf.robocode.battle
Loaded net.sf.robocode.repository
Loaded net.sf.robocode.host
Preparing battle...
----------------------
Round 1 initializing..
Let the games begin!
..
Round 1 cleaning up.

(test output deleted)

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.924 sec
Running pmj.TestDaCruzerMovement
Preparing battle...
----------------------
Round 1 initializing..
Let the games begin!
..
Round 1 cleaning up.

(test output deleted)

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.693 sec
Running pmj.TestDaCruzerVersusSittingDuck
Preparing battle...
----------------------
Round 1 initializing..
Let the games begin!
..
Round 1 cleaning up.

(test output deleted) 

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.62 sec

Results :

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.346s
[INFO] Finished at: Mon Jan 28 14:44:59 HST 2013
[INFO] Final Memory: 16M/309M
[INFO] ------------------------------------------------------------------------
```

To remove the robot from the robocode installation, invoke the clean target with the -D property:

```shell
[~/projecthosting/github/robocode-pmj-dacruzer]-> mvn -Drobocode.home=/Users/johnson/robocode clean
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building dacruzer 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ dacruzer ---
[INFO] Deleting /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target
[INFO] Deleting /Users/johnson/robocode/robots/pmj (includes = [], excludes = [])
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.305s
[INFO] Finished at: Tue Jan 29 07:00:17 HST 2013
[INFO] Final Memory: 6M/309M
[INFO] ------------------------------------------------------------------------
```
6.  Install robocode-pmj-dacruzer into Eclipse
----------------------------------------------

Now that the system is running from the command line, you'll want to also run it from Eclipse.  To do so, bring up Eclipse, and select File | Import | Maven | Existing Maven Projects, and then complete the dialog boxes to import your project.  Eclipse will read the POM file in order to determine the libraries to include on the build path.  

To run the pmj.dacruzer robot within Eclipse, you must configure Eclipse and Robocode in the normal way:
  * In the Run configuration, change the working directory to your Robocode installation directory. 
  * In the Robocode window, select Options | Preferences | Development Options to add the target/classes directory so that Robocode will see your robot.

To run the test cases, edit the Run configuration for each test to include -Drobocode.home=robocodeHomeDirectory as a VM argument. 

7. Use this project as a template for your own development
----------------------------------------------------------

Once you have completed the above steps, you are ready to use this project for your own development. To do this:
  * Duplicate the robocode-pmj-dacruzer directory, and rename it with your own robot's name.
  * Edit the POM file, and change the top lines to correspond to your own robot name. 
  * Import the project (as a Maven project) into Eclipse.
  * In Eclipse, select the project (robocode-pmj-dacruzer), then right-click and Refactor | Rename to rename the project to your own robot name.

8. Quality assurance
--------------------

The 'quality-assurance' branch extends the Maven POM file with additional plugins to support invocation of Checkstyle, PMD, FindBugs, and Jacoco.

Checkstyle
**********


To run checkstyle by itself, invoke the checkstyle:check goal:

```
[~/projecthosting/github/robocode-pmj-dacruzer]-> mvn checkstyle:check
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building dacruzer 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-checkstyle-plugin:2.9.1:check (default-cli) @ dacruzer ---
[INFO] Starting audit...
Audit done.

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.614s
[INFO] Finished at: Tue Feb 05 11:30:49 HST 2013
[INFO] Final Memory: 12M/309M
[INFO] ------------------------------------------------------------------------
[~/projecthosting/github/robocode-pmj-dacruzer]-> 
```

Checkstyle is configured to run the checks specified at: http://ics-software-engineering.googlecode.com/svn/trunk/configfiles/checkstyle.xml

If there had been an error, the build would have failed.

PMD
***

To run PMD by itself, invoke the pmd:check goal:

```
[~/projecthosting/github/robocode-pmj-dacruzer]-> mvn pmd:check
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building dacruzer 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] >>> maven-pmd-plugin:2.7.1:check (default-cli) @ dacruzer >>>
[INFO] 
[INFO] --- maven-pmd-plugin:2.7.1:pmd (pmd) @ dacruzer ---
[INFO] 
[INFO] <<< maven-pmd-plugin:2.7.1:check (default-cli) @ dacruzer <<<
[INFO] 
[INFO] --- maven-pmd-plugin:2.7.1:check (default-cli) @ dacruzer ---
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.356s
[INFO] Finished at: Tue Feb 05 11:31:36 HST 2013
[INFO] Final Memory: 15M/309M
[INFO] ------------------------------------------------------------------------
[~/projecthosting/github/robocode-pmj-dacruzer]-> 
```

PMD is configured to use the rules specified at: http://ics-software-engineering.googlecode.com/svn/trunk/configfiles/pmd.rulesets.xml.

If there had been an error, the build would have failed.

FindBugs
********

To run FindBugs by itself, invoke the findbugs:check goal:

```
[~/projecthosting/github/robocode-pmj-dacruzer]-> mvn findbugs:check
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building dacruzer 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] >>> findbugs-maven-plugin:2.5.2:check (default-cli) @ dacruzer >>>
[INFO] 
[INFO] --- findbugs-maven-plugin:2.5.2:findbugs (findbugs) @ dacruzer ---
[INFO] 
[INFO] <<< findbugs-maven-plugin:2.5.2:check (default-cli) @ dacruzer <<<
[INFO] 
[INFO] --- findbugs-maven-plugin:2.5.2:check (default-cli) @ dacruzer ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.493s
[INFO] Finished at: Tue Feb 05 11:32:18 HST 2013
[INFO] Final Memory: 15M/309M
[INFO] ------------------------------------------------------------------------
[~/projecthosting/github/robocode-pmj-dacruzer]-> 
```

FindBugs is configured to use the default rules. 

If there had been an error, the build would have failed.

Jacoco
******

Jacoco (the code coverage tool) cannot be run independently, but only as part of the verify goal, which also runs all of the preceeding quality assurance tools. Note that since code coverage requires running of the junit tests, the verify goal requires the robocode.home system property.  Here is example output from invoking the verify goal (with test case output removed):

```
[~/projecthosting/github/robocode-pmj-dacruzer]-> mvn -Drobocode.home=/Users/johnson/robocode verify
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building dacruzer 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- jacoco-maven-plugin:0.6.2.201302030002:prepare-agent (prepare-agent) @ dacruzer ---
[INFO] argLine set to -javaagent:/Users/johnson/.m2/repository/org/jacoco/org.jacoco.agent/0.6.2.201302030002/org.jacoco.agent-0.6.2.201302030002-runtime.jar=destfile=/Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target/jacoco.exec
[INFO] 
[INFO] --- maven-checkstyle-plugin:2.9.1:check (default) @ dacruzer ---
[INFO] Starting audit...
Audit done.

[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ dacruzer ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.0:compile (default-compile) @ dacruzer ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:copy-resources (copy-resources) @ dacruzer ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ dacruzer ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.0:testCompile (default-testCompile) @ dacruzer ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 3 source files to /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.10:test (default-test) @ dacruzer ---
[INFO] Surefire report directory: /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running pmj.TestDaCruzerFiring
Loaded net.sf.robocode.api
Loaded net.sf.robocode.core
Loaded net.sf.robocode.battle
Loaded net.sf.robocode.repository
Loaded net.sf.robocode.host
Preparing battle...
----------------------

(test output deleted)

Round 10 cleaning up.
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.804 sec

Results :

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- jacoco-maven-plugin:0.6.2.201302030002:report (report) @ dacruzer ---
[INFO] 
[INFO] --- maven-jar-plugin:2.3.2:jar (default-jar) @ dacruzer ---
[INFO] Building jar: /Users/johnson/projecthosting/github/robocode-pmj-dacruzer/target/dacruzer-1.0-SNAPSHOT.jar
[INFO] 
[INFO] >>> findbugs-maven-plugin:2.5.2:check (default) @ dacruzer >>>
[INFO] 
[INFO] --- findbugs-maven-plugin:2.5.2:findbugs (findbugs) @ dacruzer ---
[INFO] Fork Value is true
[INFO] Done FindBugs Analysis....
[INFO] 
[INFO] <<< findbugs-maven-plugin:2.5.2:check (default) @ dacruzer <<<
[INFO] 
[INFO] --- findbugs-maven-plugin:2.5.2:check (default) @ dacruzer ---
[INFO] BugInstance size is 0
[INFO] Error size is 0
[INFO] No errors/warnings found
[INFO] 
[INFO] >>> maven-pmd-plugin:2.7.1:check (default) @ dacruzer >>>
[INFO] 
[INFO] --- maven-pmd-plugin:2.7.1:pmd (pmd) @ dacruzer ---
[INFO] 
[INFO] <<< maven-pmd-plugin:2.7.1:check (default) @ dacruzer <<<
[INFO] 
[INFO] --- maven-pmd-plugin:2.7.1:check (default) @ dacruzer ---
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.6.2.201302030002:check (check) @ dacruzer ---
[INFO] All coverage checks have been met.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 15.800s
[INFO] Finished at: Tue Feb 05 11:33:48 HST 2013
[INFO] Final Memory: 38M/330M
[INFO] ------------------------------------------------------------------------
[~/projecthosting/github/robocode-pmj-dacruzer]-> 
```

Jacoco is configured to fail the build if class and method-level coverage is not 100%.  To see the coverage report, look in the target/site/jacoco directory. 







