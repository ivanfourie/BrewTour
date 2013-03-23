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
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import za.co.brewtour.server.entity.Image;
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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		pm = PMF.get().getPersistenceManager();
		// load image from file
		is = getClass().getResourceAsStream(imageFileName);
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
		assertNotNull(is);
		byte[] data = readFromStream(is);
		// Create entity
		System.out.println("Data length: " + data.length);
		Image img = new Image(imageFileName, "image/jpeg", data);
		
		// Persist entity
		pm.makePersistent(img);
		
		// find entity in data store
		Query query = pm.newQuery(Image.class);
        List<Image> images = (List<Image>) query.execute();
        assertNotNull(images);
        assertTrue("Entities list's size must be 1", images.size() == 1);
        
        Image dsimg = images.get(0);
        assertTrue("Entity title does not match input title ", img.getTitle().equals(imageFileName));
        assertTrue("Entity ImageType does not match input ImageType ", img.getImageType().equals("image/jpeg"));
        assertTrue("Entity data does not match input data ", Arrays.equals(dsimg.getData(), data));

	}

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
