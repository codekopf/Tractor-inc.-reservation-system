# Tractor inc. reservation system #
Tractor inc. reservation system is a simple app for tracking vehicle fleet of fictive transport company Tractor inc.

The app was developed during Java Bootcamp at Unicorn Systems. Since then I am continuing in development of the project. It is a good way to practice new design patterns and technologies. 

Project contains several good design choices:
+ Technologies: Java Spring, Angualr 2, Hibernate
+ Design patterns: DAO, service, facade, ...

# Installation ##

## Preconditions ##
To run this app, you need to install following technologies.
+ [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
+ [node.js server](https://nodejs.org/en/)
+ Eclipse Jetty
+ H2 (in memory DB)

## Installation
If you are reading this, clone this project to your machine.

## Installation DB (H2) ##

You can use this tutorial for WIndows 
[http://www.h2database.com/html/tutorial.html](http://www.h2database.com/html/tutorial.html)

On OSX:
* Install H2 over terminal typing <code>brew install h2</code>
* Go to usr/local/Cellar/h2/VERSION/bin/h2 and run it by double click on h2 exec . H2 will start.
* At [http://localhost:8082/](http://localhost:8082/)

+ Driver Class: org.h2.Driver
+ JDBC url: jdbc:h2:~/test
+ User Name: sa
+ Password: 

TODO: HOW TO KILL DB
TODO: HOW TO CHANGE H2 DB PORT NUMBER 

## Installation Backend

### Jetty ###
TODO - HOW TO INSTALL JETTY IN ECLIPSE
* Install Jetty into Eclipse over Eclipse marketplace
* Run Jetty server by your Eclipse. REST endpoints are located at [http://localhost:8085/](http://localhost:8085/)

Spring @annotation problem
* Right click on project > Run config > Jetty WEbapp > JRE > Execution enviroment 1.6 -> Apply
* Preferencies > Java > Compiler 
>> Compiler compliance level - 1.6 -> Apply
>> Configure Project Specific Settings > Select project > Uncheck "Use compl ... " and select Complience level 1.6 > Apply
>> >> Java Build path > Execution enviroment - JavaSE 1.6 -> Aply
>> > > Vsetky ostaten 3 moznosti na spodku
>> >> Java 6 
>> >> Execution enviroment - 1.6 
* Project > clean


## Installation Frontend
Direct the console to the frontend directory (tractor-rework) by and run __npm start__ command. Node.js server is required for running Angular 2 frontend. 

If this is the first install, npm will install all Angular dependencies. It can take a while depending on the speed of your internet connection. If all dependencies are all right you can go to the [http://localhost:8081/](http://localhost:8081/) . App should be running.

### Installation bugs ###

* Eclipse after import can populate project with error (over annotations etc.). It is necessary to  If Java does not work or Eclipse automatically place plenty of errors to classes you shall probably 
__Restart__ Run configuration -> JRE -> Project JRE
* If the Angular does not start or errors are showing after the start use a clean installation of dependencies with mvn commands: 
    * mvn clean 
    * mvn install

## What I learned ##
"Mark of developer seniority is his time of asking somebody else."
                                                                    Unknown senior developer.


TODO
+ Clean filters and typos
+ Write more comments
+ Write more tests (unit tests and tests using mocks of objects)
+ Implement better Angular 2 theme
+ Get in order README.md
+ Go through the whole installation process and figure out if something is not missing.
+ Shift from H2 in memory DB to MySQL
+ Shift from Eclipse Jetty server to Apache Tomcat
+ Support of i18n on backend side, not only on frontend side
+ Make small image of usage

DONE
* Include into project DB SQL sources (DB scheme and random data)

## History ##
This app was developed initially during Java Bootcamp for Windows environment. I am currently trying to create app OS independent mediate as much as possible while I am using OS X for development. 
