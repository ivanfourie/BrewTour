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

package za.co.brewtour.client.application.admin.beer;

import za.co.brewtour.client.application.ApplicationPresenter;
import za.co.brewtour.client.application.response.ResponsePresenter;
import za.co.brewtour.client.place.NameTokens;
import za.co.brewtour.shared.FieldVerifier;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;

/**
 * @author Ivan Fourie
 */
public class BeerAdminPresenter extends Presenter<BeerAdminPresenter.MyView, BeerAdminPresenter.MyProxy> {
   /**
    * {@link BeerAdminPresenter}'s proxy.
    */
   @ProxyStandard
   @NameToken(NameTokens.beerAdmin)
   public interface MyProxy extends Proxy<BeerAdminPresenter>, Place {
   }

   /**
    * {@link BeerAdminPresenter}'s view.
    */
   public interface MyView extends View {

	   public void resetAndFocus();
	      // Focus the cursor on the name field when the app loads

   }

   private final PlaceManager placeManager;

   @Inject
   public BeerAdminPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager) {
      super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

      this.placeManager = placeManager;
   }

   @Override
   protected void onBind() {
      super.onBind();

//      registerHandler(getView().getSendButton().addClickHandler(new ClickHandler() {
//         @Override
//         public void onClick(ClickEvent event) {
//            sendNameToServer();
//         }
//      }));

   }

   @Override
   protected void onReset() {
      super.onReset();

      getView().resetAndFocus();
   }

   /**
    * Send the name from the nameField to the server and wait for a response.
    */
   private void sendNameToServer() {
      // First, we validate the input.
      

      // Then, we transmit it to the ResponsePresenter, which will do the server
      // call
      //placeManager.revealPlace(new PlaceRequest(NameTokens.response).with(ResponsePresenter.textToServerParam,
      //      textToServer));
   }

}
