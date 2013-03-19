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
package za.co.brewtour.server.dispatch;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gwtplatform.dispatch.shared.ActionException;

import za.co.brewtour.shared.domain.BeerDto;

/**
 * Simple test class to tes {@link za.co.brewtour.server.dispatch.GetBeersHandler GetBeersHandler}
 * 
 * @author Michael Bester
 * @author Ivan Fourie
 */
public class GetBeersHandlerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		GetBeersHandler handler = new GetBeersHandler();
		
		try {
			List<BeerDto> list = handler.execute(null, null).getBeerList();
			assertNotNull(list);
			assertTrue("Beer size is not larger than 1",list.size()>1);
		} catch (ActionException e) {
			fail(e.getMessage());
		}
		
		
	}

}
