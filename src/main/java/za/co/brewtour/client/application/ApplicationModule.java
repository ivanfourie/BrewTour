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
package za.co.brewtour.client.application;

import za.co.brewtour.client.application.admin.beer.BeerAdminModule;
import za.co.brewtour.client.application.admin.image.ImageAdminModule;
import za.co.brewtour.client.application.beer.BeerListModule;
import za.co.brewtour.client.application.header.HeaderModule;
import za.co.brewtour.client.application.home.HomeModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * Main application module.
 * 
 * @author Ivan Fourie
 * @author Michael Bester
 */
public class ApplicationModule extends AbstractPresenterModule {

   @Override
   protected void configure() {
	  // Install modules
	  install(new HeaderModule());
	  install(new HomeModule());
	  install(new BeerListModule());
	  // Admin modules
	  install(new BeerAdminModule());
	  install(new ImageAdminModule());
	  
      // Application Presenter
      bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
            ApplicationPresenter.MyProxy.class);
   }
}
