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

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author Michael Bester
 * @author Ivan Fourie
 */
public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
   public interface Binder extends UiBinder<Widget, ApplicationView> {
   }

   @UiField
   TextBox nameField;
   @UiField
   Button sendButton;
   @UiField
   HTML error;

   @UiField
   NavLink beerNavLink;

   @Inject
   public ApplicationView(final Binder binder) {
      initWidget(binder.createAndBindUi(this));
   }

   @Override
   public String getName() {
      return nameField.getText();
   }

   @Override
   public Button getSendButton() {
      return sendButton;
   }

   @Override
   public void resetAndFocus() {
      // Focus the cursor on the name field when the app loads
      nameField.setFocus(true);
      nameField.selectAll();
   }

   @Override
   public void setError(String errorText) {
      error.setText(errorText);
   }

   @Override
   public NavLink getBeerNavLink() {
      return beerNavLink;
   }
}
