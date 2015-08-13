# A guide to set up the project #

### Software Requirements ###

1) SVN [SVN Tortoise](http://tortoisesvn.net/downloads.html)

2) Eclipse [Spring STS](http://www.springsource.com/landing/best-development-tool-enterprise-java)

3) Maven 2.2.1 [Maven 2.2.1](http://maven.apache.org/download.html)

4) Jboss AS 6.1.0.Final [Jboss AS 6.1.0.Final](http://www.jboss.org/jbossas/downloads/)


### Steps to deploy the application ###

We will first run the application using [Jetty](http://en.wikipedia.org/wiki/Jetty_(Web_server))

1) [Checkout](http://code.google.com/p/ddd-cqrs-base-project/source/checkout) the project

2) open command prompt and traverse to  %your checkout path%/ddd-cqrs-base-project\trunk\

3) A post on how to set up your database can be found [here](http://noobjuggler.blogspot.in/2012/03/configuring-database-for-dddcqrs-base.html)

4) type in mvn clean compile install
Then you will get a BUILD SUCCESSFUL message

5) type in mvn jetty:run
Then you will get a BUILD SUCCESSFUL message, and the application has been deployed

6) Open your favorite browser and goto http://localhost:8080/BaseProject


A more detailed explanation of the above along with screen shots is available [here](http://noobjuggler.blogspot.com/2011/11/how-to-deploy-dddcqrs-base-project.html)



Using Jboss --- This section will be updated soon


### Issues I encountered during Setup ###

**JBoss ships with its own version of hibernate which causes class loading problems**

Solution : This is **one** of the solutions :) not the most elegant one

[Similar issue reported](http://stackoverflow.com/questions/4410017/how-to-separate-ear-classloader-and-system-classloader-in-jboss-6)

[Jboss Class loading explanation](http://community.jboss.org/wiki/ClassLoadingConfiguration)

[Jboss Class loading explanation](http://java.dzone.com/articles/jboss-microcontainer-classloading)


Unfortunately none of the above actually resolved my problem

[Problem I encountered](http://czetsuya-tech.blogspot.com/2011/02/javalangnosuchmethodexception.html)

However strangely the solution recommended did not work with JBoss 6 so I came up with my version of the solution.

I removed the below 3 jars from %JBOSS\_HOME%/common/lib

_hibernate-commons-annotations_

_hibernate-core_

_hibernate-validator-legacy_


**JBoss also ships with its own version of BSH and HSQLDB which again causes class loading problems**

So remove the below 2 Jars from %JBOSS\_HOME%/common/lib

_bsh.jar_

_hsqldb.jar_


Put the below jars into %JBOSS\_HOME%/common/lib

_hsqldb-2.2.4.jar_

_hibernate-entitymanager-3.6.7.Final_

_hibernate-jpa-2.0-api-1.0.1.Final_

_hibernate-validator-4.0.0.GA_


These Jars are the same jar versions included in the project.