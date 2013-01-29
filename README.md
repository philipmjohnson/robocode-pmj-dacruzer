robocode-pmj-dacruzer
=====================

Example project showing how to develop robocode robots using the Maven build system.  To install this package:

1. Install robocode
-------------------

Install both the robocode basic distribution and the robocode testing plugin.  Both setup.jar files are available at: https://sourceforge.net/projects/robocode/files/robocode/1.7.4.4/. 

Be sure to run the system manually from the command line to ensure that you have the appropriate Java installed and so forth. 

2. Install Maven 3
------------------

Directions on installing Maven are at: http://maven.apache.org/download.cgi

Be sure to run mvn --version to verify that it is correctly installed. 

3. Download the robocode-pmj-dacruzer package
---------------------------------------------

For those who do not know about git, the easiest way is to click the "ZIP" button at the top of this page, which will download the latest version of this repository as a .zip file. 

For those who know about git, you will want to clone it. 

4. Install selected robocode jar files into your local Maven repository
-----------------------------------------------------------------------

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

The next step is to build and test the system. You use the standard Maven 'test' target.  There are two special aspects of this process of which you should be aware.  

First, the RobotTestBed class that is used for the test cases requires the definition of a System Property called "robocode.home", which should point to the directory where Robocode is installed. To tell Maven the value of this property, you use the -D command line value, as illustrated below.

Second, the Robocode runtime system needs your newly developed robot to be known to the system during testing.  To accomplish this, the pom.xml file defines a "copy-resource" goal that copies your robot binary from the target/classes directory to the robocode.home/robots directory after completing compilation. Take a look at the pom.xml file to see how this is done.   Note that this approach does not remove these files from the robocode installation after the testing process is done, which is a bug in the process.

Here is an example of the command line used to build and test the system, along with the output.

```
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
----------------------
Round 2 initializing..
Let the games begin!
..
Round 2 cleaning up.
----------------------
Round 3 initializing..
Let the games begin!
..
Round 3 cleaning up.
----------------------
Round 4 initializing..
Let the games begin!
..
Round 4 cleaning up.
----------------------
Round 5 initializing..
Let the games begin!
..
Round 5 cleaning up.
----------------------
Round 6 initializing..
Let the games begin!
..
Round 6 cleaning up.
----------------------
Round 7 initializing..
Let the games begin!
..
Round 7 cleaning up.
----------------------
Round 8 initializing..
Let the games begin!
..
Round 8 cleaning up.
----------------------
Round 9 initializing..
Let the games begin!
..
Round 9 cleaning up.
----------------------
Round 10 initializing..
Let the games begin!
..
Round 10 cleaning up.
----------------------
Round 11 initializing..
Let the games begin!
..
Round 11 cleaning up.
----------------------
Round 12 initializing..
Let the games begin!
..
Round 12 cleaning up.
----------------------
Round 13 initializing..
Let the games begin!
..
Round 13 cleaning up.
----------------------
Round 14 initializing..
Let the games begin!
..
Round 14 cleaning up.
----------------------
Round 15 initializing..
Let the games begin!
..
Round 15 cleaning up.
----------------------
Round 16 initializing..
Let the games begin!
..
Round 16 cleaning up.
----------------------
Round 17 initializing..
Let the games begin!
..
Round 17 cleaning up.
----------------------
Round 18 initializing..
Let the games begin!
..
Round 18 cleaning up.
----------------------
Round 19 initializing..
Let the games begin!
..
Round 19 cleaning up.
----------------------
Round 20 initializing..
Let the games begin!
..
Round 20 cleaning up.
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.924 sec
Running pmj.TestDaCruzerMovement
Preparing battle...
----------------------
Round 1 initializing..
Let the games begin!
..
Round 1 cleaning up.
----------------------
Round 2 initializing..
Let the games begin!
..
Round 2 cleaning up.
----------------------
Round 3 initializing..
Let the games begin!
..
Round 3 cleaning up.
----------------------
Round 4 initializing..
Let the games begin!
..
Round 4 cleaning up.
----------------------
Round 5 initializing..
Let the games begin!
..
Round 5 cleaning up.
----------------------
Round 6 initializing..
Let the games begin!
..
Round 6 cleaning up.
----------------------
Round 7 initializing..
Let the games begin!
..
Round 7 cleaning up.
----------------------
Round 8 initializing..
Let the games begin!
..
Round 8 cleaning up.
----------------------
Round 9 initializing..
Let the games begin!
..
Round 9 cleaning up.
----------------------
Round 10 initializing..
Let the games begin!
..
Round 10 cleaning up.
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.693 sec
Running pmj.TestDaCruzerVersusSittingDuck
Preparing battle...
----------------------
Round 1 initializing..
Let the games begin!
..
Round 1 cleaning up.
----------------------
Round 2 initializing..
Let the games begin!
..
Round 2 cleaning up.
----------------------
Round 3 initializing..
Let the games begin!
..
Round 3 cleaning up.
----------------------
Round 4 initializing..
Let the games begin!
..
Round 4 cleaning up.
----------------------
Round 5 initializing..
Let the games begin!
..
Round 5 cleaning up.
----------------------
Round 6 initializing..
Let the games begin!
..
Round 6 cleaning up.
----------------------
Round 7 initializing..
Let the games begin!
..
Round 7 cleaning up.
----------------------
Round 8 initializing..
Let the games begin!
..
Round 8 cleaning up.
----------------------
Round 9 initializing..
Let the games begin!
..
Round 9 cleaning up.
----------------------
Round 10 initializing..
Let the games begin!
..
Round 10 cleaning up.
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
[~/projecthosting/github/robocode-pmj-dacruzer]-> 
```




