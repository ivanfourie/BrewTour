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

import za.co.brewtour.client.application.ui.BeerItem;
import za.co.brewtour.shared.domain.BeerDto;

import com.github.gwtbootstrap.client.ui.Brand;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author Ivan Fourie
 */
public class BeerListView extends ViewImpl implements BeerListPresenter.MyView {
   public interface Binder extends UiBinder<Widget, BeerListView> {
   }

   @UiField
   HTML serverResponse;
   @UiField
   Button closeButton;
   @UiField
   VerticalPanel verticalPanel;
   @UiField
   Brand brandElement;

   @Inject
   public BeerListView(final Binder binder) {
      initWidget(binder.createAndBindUi(this));
   }

   @Override
   public Button getCloseButton() {
      return closeButton;
   }

   @Override
   public void setBeerList(List<BeerDto> beerList) {
	   verticalPanel.clear();
	   for (BeerDto beer : beerList) {
         BeerItem beerItem = new BeerItem();
         beerItem.setTitle(beer.getName());
         beerItem.setHref(beer.getBreweryUrl());
         beerItem.setDescription(beer.getDescription());
         beerItem.setBrewery(beer.getBrewery());
         beerItem.setBeerStyle(beer.getBeerStyle());
         beerItem.setAbv(beer.getAbv());
         beerItem.setImageUrl(beer.getImageUrl());
         beerItem.setLocation(beer.getLocation());

         verticalPanel.add(beerItem);
      }
   }

   @Override
   public void setServerResponse(String serverResponse) {
      this.serverResponse.setHTML(serverResponse);
   }

   //TODO: Move somewhere else
   @Override
   public Brand getBrandElement() {
	   return brandElement;
   }

}
