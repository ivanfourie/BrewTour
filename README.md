BrewTour
========

BrewTour shows local beers and breweries around South Africa. It is currently hosted on the Google App Engine at http://brewtourproject.appspot.com/

Building & Running
------------------

This project requires Java 6 and uses the maven build tool.

### Building

To build the project run `mvn clean install`

This will download all the dependencies, compile the code and build the project

### Running

To run the project in GWT Hosted mode, use the GWT run goal

`mvn gwt:run`


Stack
-----

The current technology stack 
* Host and App Platform - Google App Engine
* UI - Google Web Toolkit / GWT-Platform / GWT-Bootstrap 
* IoC container and DI - Spring / Gin
* Persistence - JDO / Appengine Datastore
