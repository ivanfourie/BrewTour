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

import java.util.Collection;

import za.co.brewtour.client.application.beer.BeerListView.Binder;
import za.co.brewtour.client.application.response.ResponsePresenter;
import za.co.brewtour.client.place.NameTokens;

import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

/**
 * Header widget for site
 * 
 * @author Ivan.Fourie
 */
public class HeaderView extends Composite {

	interface Binder extends UiBinder<Widget, HeaderView> {
	}
	
	private final PlaceManager placeManager;
	
	@UiField
	NavLink home;
	@UiField
	NavLink beer;
	
	@Inject
	public HeaderView(final Binder binder, PlaceManager placeManager) {
		this.placeManager = placeManager;
		initWidget(binder.createAndBindUi(this));
	}
	
	@UiHandler("home")
	void onHomeClick(ClickEvent event) {
		placeManager.revealPlace(new PlaceRequest(NameTokens.home));
	}
	
	@UiHandler("beer")
	void onBeerClick(ClickEvent event) {
		placeManager.revealPlace(new PlaceRequest(NameTokens.beers));
	}
}
