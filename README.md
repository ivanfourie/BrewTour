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

### Bind Address & Serving URLs

The project uses Google App Engine APIs such as ImageService and Blobstore which generates serving URLS.
To avoid avoid cross site scripting issues with the image upload functions, make sure to set up the bind address to your hostname.  

`-bindAddress <hostname>` 

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

### Persisting
BrewTour makes use of JDO (Java Data Objects), which is a Persistence API and ORM. 

First things first, we need to have a JDO configuration xml, this xml tells the application where it will persist to, how it will do so
and so on:

``` META-INF/jdoconfig.xml ```
```
<?xml version="1.0" encoding="utf-8"?>
<jdoconfig xmlns="http://java.sun.com/xml/ns/jdo/jdoconfig"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/jdo/jdoconfig http://java.sun.com/xml/ns/jdo/jdoconfig_3_0.xsd">

    <persistence-manager-factory name="transactions-optional">
        <property name="javax.jdo.PersistenceManagerFactoryClass"
            value="org.datanucleus.store.appengine.jdo.DatastoreJDOPersistenceManagerFactory"/>
        <property name="javax.jdo.option.ConnectionURL" value="appengine"/>
        <property name="javax.jdo.option.NontransactionalRead" value="true"/>
        <property name="javax.jdo.option.NontransactionalWrite" value="true"/>
        <property name="javax.jdo.option.RetainValues" value="true"/>
        <property name="datanucleus.appengine.autoCreateDatastoreTxns" value="true"/>
    </persistence-manager-factory>
</jdoconfig>

```

To enable persistence for a new entity, you'll need to add
annotation to the new Entity Class like this:

```

@PersistenceCapable(detachable = "true")
public class Beer {
...
}

```

There are also some annotations for the properties of this entity. Here is an example from the Beer Entity:

```
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
    @Persistent
    private String beerName;
    
    @Persistent
    private String description;
   ...
   
```

It is as easy as that.

Now that we have an entity, how do we commit it to the datastore you say?
Firstly, we need an instance of the PersistenceManager, this manager allows us to read, write and update to/from the datastore.
We can get an instance of a PersistenceManger using the PersistenceManagerFactory class. The PersistenceManagerFactory uses the jdoconfig.xml (explained 
right at the beginning of the Persistence section) to give you an instance of a PersistanceManger, this call is quite costly in terms of performance so we have created another class
called: `za.co.brewtour.server.persistence.PMF` that will create an instance of the PersistenceManager only once (when the first call is made to the Datastore, 
this instance will be created).

Now lets persist something!!

To get all the Beer entities from the Datastore, we'll do this:
```
PersistenceManager pm = PMF.get().getPersistenceManager();
Query query = pm.newQuery(Beer.class);
List<Beer> beerEntitiesList = (List<Beer>) query.execute();
```

To persist a new entity, we'll do this:

```
// pm is the same initialised the same as above
Beer beer = new Beer(...);
pm.makePersistent(beer);
```

So as you can see doing persistence here is quite simple.


`end`
