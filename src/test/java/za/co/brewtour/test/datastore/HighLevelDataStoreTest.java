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
package za.co.brewtour.test.datastore;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test, to test the DataStore service. We run the same test two times to
 * ensure that there is no state leaking between tests.
 *
 * @author bestermichael
 */
public class HighLevelDataStoreTest {

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper((new LocalDatastoreServiceTestConfig()));

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testInsert1() {
        doTest();
    }

    @Test
    public void testInsert2() {
        doTest();
    }

    private void doTest() {
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        assertEquals(0, ds.prepare(new Query("test")).countEntities(withLimit(10)));

        ds.put(new Entity("test"));
        ds.put(new Entity("test"));

        assertEquals(2, ds.prepare(new Query("test")).countEntities(withLimit(10)));
    }
}
