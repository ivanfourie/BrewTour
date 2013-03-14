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

package za.co.brewtour.client.application.beer;

import java.util.List;

import za.co.brewtour.client.place.NameTokens;
import za.co.brewtour.shared.dispatch.GetBeersAction;
import za.co.brewtour.shared.dispatch.GetBeersResult;
import za.co.brewtour.shared.domain.BeerDto;

import com.github.gwtbootstrap.client.ui.Brand;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;

/**
 * @author Ivan Fourie
 */
public class BeerListPresenter extends Presenter<BeerListPresenter.MyView, BeerListPresenter.MyProxy> {
   /**
    * {@link BeerListPresenter}'s proxy.
    */
   @ProxyCodeSplit
   @NameToken(NameTokens.beers)
   public interface MyProxy extends Proxy<BeerListPresenter>, Place {
   }

   /**
    * {@link BeerListPresenter}'s view.
    */
   public interface MyView extends View {
      Button getCloseButton();

      void setServerResponse(String serverResponse);
      
      void setBeerList(List<BeerDto> beerList);
      
      Brand getBrandElement();
   }

   private final DispatchAsync dispatcher;
   private final PlaceManager placeManager;

   private String textToServer;

   @Inject
   public BeerListPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
         DispatchAsync dispatcher) {
      super(eventBus, view, proxy, RevealType.Root);

      this.placeManager = placeManager;
      this.dispatcher = dispatcher;
   }

   @Override
   public void prepareFromRequest(PlaceRequest request) {
      super.prepareFromRequest(request);
   }

   @Override
   protected void onBind() {
      super.onBind();
      
      // TODO: Move this somewhere else
      registerHandler(getView().getBrandElement().addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
             placeManager.revealPlace(new PlaceRequest(NameTokens.getHome()));
          }
       }));
      
      
      registerHandler(getView().getCloseButton().addClickHandler(new ClickHandler() {
         @Override
         public void onClick(ClickEvent event) {
            placeManager.revealPlace(new PlaceRequest(NameTokens.getHome()));
         }
      }));
   }

   @Override
   protected void onReset() {
      super.onReset();
      getView().setServerResponse("Getting beer...");
      dispatcher.execute(new GetBeersAction(), new AsyncCallback<GetBeersResult>() {
         @Override
         public void onFailure(Throwable caught) {
            getView().setServerResponse("An error occured: " + caught.getMessage());
         }

         @Override
         public void onSuccess(GetBeersResult result) {
            getView().setServerResponse("Got result...");
            getView().setBeerList(result.getBeerList());
            getView().setServerResponse("Loaded");
         }
      });
   }
}
