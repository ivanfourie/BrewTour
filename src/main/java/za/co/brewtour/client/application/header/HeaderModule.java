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
package za.co.brewtour.client.application.header;

import za.co.brewtour.client.application.beer.BeerListPresenter;
import za.co.brewtour.client.application.beer.BeerListView;
import za.co.brewtour.client.application.response.ResponsePresenter;
import za.co.brewtour.client.application.response.ResponseView;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author Ivan Fourie
 */
public class HeaderModule extends AbstractPresenterModule {

   @Override
   protected void configure() {
	   bind(HeaderView.class).in(Singleton.class);
   }
}
