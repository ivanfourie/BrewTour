BrewTour
========

BrewTour shows local beers and breweries around South Africa. It is currently hosted on the Google App Engine at http://brewtourproject.appspot.com/

Started by @bestermichael and @ivan-fourie.

Building & Running
------------------

This project requires Java 6 and uses the maven build tool.

### Building

To build the project run `mvn clean install`

This will download all the dependencies, compile the code and build the project


### Running

To run the project in GWT Hosted mode, use the GWT run goal

`mvn gwt:run`

To similuate running in Google App Engine, use the gae:run goal

`mvn gae:run`


Stack
-----

The current technology stack 
* Host and App Platform - Google App Engine
* UI - Google Web Toolkit / GWT-Platform / GWT-Bootstrap 
* IoC container and DI - Spring / Gin
* Persistence - JDO / Appengine Datastore

Project Structure
-----------------

BrewTour has a client component based on Google Web Toolkit (GWT) and a server component that are serlvets running in the Spring container. These components are glued together via the GWT Platform (GWTP) by making use of the command pattern (handlers, dispatcher, actions, result) and the model-view-presenter pattern. 

### Entry points
Since BrewTour makes use of Dependency Injection frameworks - Gin for the client and Spring for server - the following two modules act as entry points:
```
za
└───co
    └───brewtour
        ├───client
        │   └───gin
        │       └───ClientModule.java // Module installs other modules and configures the client
        └───server
            └───spring
                └───ServerModule.java // Module that binds handlers and configures the server     
```

### Communication
BrewTour makes use of GWT-RPC and the command pattern for client-server communication. To achieve this, presenters on the client dispatches actions (via GWT-RPC) to an associated action handler on the server, the server handles the action and responds with the relevant result object to the client. Action and Result objects may contain Data Transfer Objects for complicated data structures. GWTP offers code generation for Actions, Results and DTOs. Communication objects are contained in the following packages:

```
za
└───co
    └───brewtour
        ├───client
        │   └───application
        │       └───beer
        │           └───BeerListPresenter.java // Presenter that dispatches GetBeerAction to GetBeersHandler via RPC
        ├───server
        │   └───dispatch
        │       └───GetBeersHandler.java // Handler that handles GetBeersAction and returns GetBeersResult
        └───shared
            ├───dispatch /* Actions and Results shared by server and client */
            │   └───GetBeers.java // auto-generates GetBeersAction.java and GetBeersResult.java by using @GenDispatch annotation
            └───domain /* Domain objects shared by server and client */
                └───Beer.java // Simple class that generates BeerDto.java by using @GenDto annotation

```
