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

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * Main application presenter. Provides a content slot in which other views can be 
 * revealed in via the corresponding presenter using 
 * {@code ApplicationPresenter.TYPE_SetMainContent} instead of RevealType.Root
 * 
 * @author Ivan Fourie
 * @author Michael Bester
 */
public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
	public interface MyView extends View {
    }
	
	@ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();
	
	/**
    * {@link ApplicationPresenter}'s proxy.
    */
	@ProxyStandard
	public interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	@Inject
	public ApplicationPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);
	}
}
