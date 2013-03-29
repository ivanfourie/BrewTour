/**
 * Copyright (C) 2013 BrewTour
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * 
 */
package za.co.brewtour.test.datastore.entities;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import za.co.brewtour.shared.domain.Image;
import za.co.brewtour.server.persistence.PMF;

/**
 * @author Ivan.Fourie
 */
public class ImageDataStoreTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			(new LocalDatastoreServiceTestConfig()));
	PersistenceManager pm;

	private InputStream is = null;
	private String imageFileName = "ivan_homebrew_lager.jpg";
	private String imageUrl = "http://ivanfourie:8888/_ah/img/pt7oKl1xrYV_BXrWmgj2lQ";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		pm = PMF.get().getPersistenceManager();
		// load image from file
		//is = getClass().getResourceAsStream(imageFileName);
		//ClassPathResource resource = new ClassPathResource(imageFileName);
		//is = resource.getInputStream();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		helper.tearDown();
		pm.close();
		if (is != null) {
			is.close();
		}

		is = null;
	}

	@Test
	public void addNewImageTest() throws Exception {
		// Test image is loaded
		//assertNotNull(is);
		//byte[] data = readFromStream(is);
		// Create entity
		
		Image img = new Image(imageFileName, imageUrl);
		
		// Persist entity
		pm.makePersistent(img);
		
		// find entity in data store
		Query query = pm.newQuery(Image.class);
        List<Image> images = (List<Image>) query.execute();
        assertNotNull(images);
        assertTrue("Entities list's size must be 1", images.size() == 1);
        
        Image dsimg = images.get(0);
        assertTrue("Entity title does not match input title ", dsimg.getName().equals(imageFileName));
        assertTrue("Entity ImageType does not match input ImageType ", dsimg.getImageUrl().equals(imageUrl));

	}

	@Deprecated
	public byte[] readFromStream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		byte[] data = new byte[4096];
		int count = inputStream.read(data);
		while (count != -1) {
			dos.write(data, 0, count);
			count = inputStream.read(data);
		}

		return baos.toByteArray();
	}
}
