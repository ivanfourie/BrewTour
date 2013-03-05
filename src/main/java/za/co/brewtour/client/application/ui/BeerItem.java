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
package za.co.brewtour.client.application.ui;

import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ivan Fourie
 */
public class BeerItem extends Composite implements HasText {

   private static BeerItemUiBinder uiBinder = GWT.create(BeerItemUiBinder.class);

   interface BeerItemUiBinder extends UiBinder<Widget, BeerItem> {
   }

   /**
    * Because this class has a default constructor, it can be used as a binder
    * template. In other words, it can be used in other *.ui.xml files as
    * follows: <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
    * xmlns:g="urn:import:**user's package**">
    * <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
    * depending on the widget that is used, it may be necessary to implement
    * HasHTML instead of HasText.
    */
   public BeerItem() {
      initWidget(uiBinder.createAndBindUi(this));
   }

   @UiField
   Anchor titleAnchor;
   @UiField
   Paragraph description;
   @UiField
   Label brewery;
   @UiField
   Label location;
   @UiField
   Label beerStyle;
   @UiField
   Label abv;
   @UiField
   Image image;

   public BeerItem(String title, String href, String description, String imageUrl, String brewery, String location,
         String beerStyle, String abv) {
      initWidget(uiBinder.createAndBindUi(this));

      // Can access @UiField after calling createAndBindUi
      setTitle(title);
      setHref(href);
      setDescription(description);
      setImageUrl(imageUrl);
      setBrewery(brewery);
      setLocation(location);
      setBeerStyle(beerStyle);
      setAbv(abv);
   }

   @UiHandler("image")
   void onClick(ClickEvent e) {
      Window.alert("Hello!");
   }

   @Override
   public void setText(String text) {
      // button.setText(text);
   }

   /**
    * Gets invoked when the default constructor is called and a string is
    * provided in the ui.xml file.
    */
   @Override
   public String getText() {
      // return button.getText();
      return null;
   }

   /**
    * @return the title
    */
   @Override
   public String getTitle() {
      return titleAnchor.getText();
   }

   /**
    * @param title
    *           the title to set
    */
   @Override
   public void setTitle(String title) {
      this.titleAnchor.setText(title);
   }

   /**
    * @return the title href
    */
   public String getHref() {
      return titleAnchor.getHref();
   }

   /**
    * @param title
    *           the title href to set
    */
   public void setHref(String href) {
      this.titleAnchor.setHref(href);
   }

   /**
    * @return the description
    */
   public String getDescription() {
      return description.getText();
   }

   /**
    * @param description
    *           the description to set
    */
   public void setDescription(String description) {
      this.description.setText(description);
   }

   /**
    * @return the brewery
    */
   public String getBrewery() {
      return brewery.getText();
   }

   /**
    * @param brewery
    *           the brewery to set
    */
   public void setBrewery(String brewery) {
      this.brewery.setText(brewery);
   }

   /**
    * @return the location
    */
   public String getLocation() {
      return location.getText();
   }

   /**
    * @param location
    *           the location to set
    */
   public void setLocation(String location) {
      this.location.setText(location);
   }

   /**
    * @return the beerStyle
    */
   public String getBeerStyle() {
      return beerStyle.getText();
   }

   /**
    * @param beerStyle
    *           the beerStyle to set
    */
   public void setBeerStyle(String beerStyle) {
      this.beerStyle.setText(beerStyle);
   }

   /**
    * @return the abv
    */
   public String getAbv() {
      return abv.getText();
   }

   /**
    * @param abv
    *           the abv to set
    */
   public void setAbv(String abv) {
      this.abv.setText(abv);
   }

   /**
    * @return the image url
    */
   public String getImageUrl() {
      return image.getUrl();
   }

   /**
    * @param image
    *           url the image url to set
    */
   public void setImageUrl(String url) {
      this.image.setUrl(url);
   }

}
