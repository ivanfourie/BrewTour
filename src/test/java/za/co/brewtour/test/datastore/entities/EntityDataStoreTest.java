/*
 * Copyright 2013 BrewTour.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package za.co.brewtour.test.datastore.entities;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jdo.JDOException;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import za.co.brewtour.server.persistence.PMF;
import za.co.brewtour.shared.domain.Beer;
import za.co.brewtour.shared.domain.Brewery;

/**
 *
 * @author bestermichael
 */
public class EntityDataStoreTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper((new LocalDatastoreServiceTestConfig()));
	PersistenceManager pm;

	@Before
	public void setUp() {
		helper.setUp();
		pm = PMF.get().getPersistenceManager();

	}

	@After
	public void tearDown() {
		helper.tearDown();
		pm.close();
	}

	@Test
	public void addNewBeerEntity() {
		//pm = PMF.get().getPersistenceManager();
		Brewery brewery = new Brewery("Jack Black", new Date(), "South Africa", "Western Cape", "Cape Town", "url:for.logo");

		Beer beer = new Beer("Lager", "Something nice", brewery, new Date(), "5", "Pale Lager", "url:url");
		Beer beer2 = new Beer("Pale Ale", "Something nice", brewery, new Date(), "5", "Pale Ale", "url:url");


		//Persist the Beer to the DataStore, and check that it has been persisted:
		Transaction tx = pm.currentTransaction();
		tx.begin();
		pm.makePersistent(brewery);
		pm.makePersistent(beer);
		pm.makePersistent(beer2);


		//Set the Beers for the brewery:
		List<Beer> beerList = new ArrayList<Beer>();
		beerList.add(beer);
		beerList.add(beer2);
		brewery.setBeers(beerList);
		tx.commit();


		//Check that the entities exists.
		try {
			Query breweryQuery = pm.newQuery(Brewery.class);
			List<Brewery> breweryEntities = (List<Brewery>) breweryQuery.execute();
			assertNotNull(breweryEntities);

			Query query = pm.newQuery(Beer.class);
			List<Beer> beerEntities = (List<Beer>) query.execute();
			assertNotNull(beerEntities);
			assertTrue("Entities List's size must be 2", beerEntities.size() == 2);
		} catch (JDOException e) {
			fail();
		}
	}

	@Test
	public void deleteBeerEntity() {
		Brewery brewery = new Brewery("Jack Black", new Date(), "South Africa", "Western Cape", "Cape Town", "url:for.logo");
		Beer beer = new Beer("Jack Black Lager", "Something nice", brewery, new Date(), "5", "Pale Lager", "url:url");

		//First persist an object:
		Transaction tx = pm.currentTransaction();
		tx.begin();
		pm.makePersistent(brewery);
		pm.makePersistent(beer);
		tx.commit();

		//Now delete it:
		tx.begin();
		pm.deletePersistent(beer);
		tx.commit();

		try {
			Query query = pm.newQuery(Beer.class);
			List<Beer> beerEntities = (List<Beer>) query.execute();
			assertTrue("Entity has not been deleted", beerEntities.isEmpty());

		} catch (JDOException e) {
			fail();
		}
	}

	@Test
	public void updateBeerEntity() {

		// We need two transactions, one to create the beer and the second to update it
		Transaction tx = pm.currentTransaction();
		Brewery brewery = new Brewery("Jack Black", new Date(), "South Africa", "Western Cape", "Cape Town", "url:for.logo");
		Beer beer = new Beer("Jack Black Lager", "Something nice", brewery, new Date(), "5", "Pale Lager", "url:url");

		// begin insert transaction
		tx.begin();

		//First persist an object:
		pm.makePersistent(brewery);
		pm.makePersistent(beer);
		// Commit insert transaction, flushing the object to the datastore
		tx.commit();

		// begin update transaction
		tx.begin();
		//Beer detachedBeer = pm.detachCopy(beer);
		Beer jackBlackBeer = pm.getObjectById(Beer.class, beer.getKey());
		Beer detachedBeer = pm.detachCopy(jackBlackBeer);
		//Update the object and persist it:
		detachedBeer.setBeerName("Pale Ale");

		pm.makePersistent(detachedBeer);
		// Commit update transaction
		tx.commit();

		try {
			Query query = pm.newQuery(Beer.class);
			List<Beer> beerEntities = (List<Beer>) query.execute();

			assertTrue("Should only have one entity persisted", beerEntities.size() == 1);
			assertTrue("Beer name should have been updated", beerEntities.get(0).getBeerName().equals("Pale Ale"));
		} catch (JDOException e) {
			fail();
		}
	}
}
