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

Robocode binaries are not provided as part of public Maven repositories, so the next step is to install the two jar files needed for basic compilation into your local repository.   You accomplish this via the following commands:

```
mvn install:install-file -Dfile=robocode.jar -DartifactId=robocode  -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.testing.jar -DartifactId=robocode.testing -DgroupId=net.sourceforge.robocode  -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
```



