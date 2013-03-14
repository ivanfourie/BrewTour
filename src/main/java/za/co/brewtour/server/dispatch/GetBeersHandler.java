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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import za.co.brewtour.shared.dispatch.GetBeersAction;
import za.co.brewtour.shared.dispatch.GetBeersResult;
import za.co.brewtour.shared.domain.BeerDto;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler to fetch a list of beers
 * 
 * @author Ivan Fourie
 */
public class GetBeersHandler extends AbstractActionHandler<GetBeersAction, GetBeersResult> {
   @Autowired
   private ServletContext servletContext;

   public GetBeersHandler() {
      super(GetBeersAction.class);
   }

   @Override
   public GetBeersResult execute(GetBeersAction action, ExecutionContext context) throws ActionException {
      // TODO: Specify range for pagination
      return new GetBeersResult(getBeers());
   }

   /**
    * @return {@literal List<BeerDto>}
    */
   private List<BeerDto> getBeers() {
      // TODO Change to get it from data source
      List<BeerDto> beerList = new ArrayList<BeerDto>();
      
      // generate list
      beerList.add(new BeerDto("Beast Of The Deep", 
				              "A tropical-fruit aroma greets you with a creamy, smooth mouthfeel and an almost honey-like character before the biscuity, chewy malt backbone coats your palate with a smooth richness. The finish is elegant and dry, leaving you yearning for more...", 
				              "Brewers &amp; Union", 
				              "http://www.gabrielcollective.com/beast/beast.html", 
				              "Cape Town", 
				              "Strong Lager", 
				              "6.5%", 
				              "img/beer_beast_of_the_deep.png"));
      
      beerList.add(new BeerDto("Whale Tale Ale", 
				              "Whale Tale Ale  has a toffee character with a hint of caramel - an unmistakably unique flavour. Hallertauer hops ensures a clean refined finish. If, like me, you're wanting more from your beer, this will become your regular. Available in draught &amp; 340ml bottles.", 
				              "Boston Breweries", 
				              "http://www.bostonbreweries.co.za", 
				              "Paarden Eiland, Cape Town", 
				              "Amber Ale", 
				              "3.5%", 
				              "img/beer_whale_tale_ale.jpg"));
      
      beerList.add(new BeerDto("Van Hunks Pumpkin Ale", 
				              "This unique blend of pumpkin, coriander, butternut, cinnamon and nutmeg is a complexed mix full with flavour. Its a rich blend of unusual ingredients that leave you wanting more.", 
				              "Boston Breweries", 
				              "http://www.bostonbreweries.co.za", 
				              "Paarden Eiland, Cape Town", 
				              "Pumkpin Ale", 
				              "5%", 
				              "img/beer_whale_tale_ale.jpg"));
      
      beerList.add(new BeerDto("Naked Mexican", 
				              "A sparkling, golden lager with a pure white head. Imported malt combined with subtle hop flavours creates a perfect refreshing experience.  A beer that is pure and naked with nothing to hide.", 
				              "Boston Breweries", 
				              "http://www.bostonbreweries.co.za", 
				              "Paarden Eiland, Cape Town", 
				              "Pale Lager", 
				              "4.5%", 
				              "img/beer_naked_mexican.jpg"));
      
      beerList.add(new BeerDto("Johnny Gold Weiss", 
				              "Johnny Gold Weiss is slightly sweet and fruity, with a full body. It has a typical hefeweizen taste produced by Bavarian yeast. Strong clove and banana flavours with hints of vanilla. Johnny Gold Weiss is very lightly hopped leaving almost no bitterness. Bottle conditioning gives it a typical cloudy appearance.", 
				              "Boston Breweries", 
				              "http://www.bostonbreweries.co.za", 
				              "Paarden Eiland, Cape Town", 
				              "Hefeweizen", 
				              "5%", 
				              "img/beer_johnny_gold_weiss.jpg"));
      
      beerList.add(new BeerDto("Hazzard Ten Ale", 
				              "If you're a fizzy yellow beer drinker this is definitely not for you. With an alcohol content of 10% it is the strongest beer brewed in South Africa, the most defining character however is it's flavour. It is dark red in colour, has a thick creamy head, and a strong malty character. The sweetness has been balanced by adding large amounts of hops to the beer after fermentation, a process called dry hopping. It's definitely the beer that is the most fun to make.", 
				              "Boston Breweries", 
				              "http://www.bostonbreweries.co.za", 
				              "Paarden Eiland, Cape Town", 
				              "American Strong Ale", 
				              "10%", 
				              "img/beer_hazzard_ten_ale.jpg"));
      
      beerList.add(new BeerDto("Boston Premium Lager", 
                               "The first of the Boston Range, a deliciously crafted Premium Lager boasting a golden malty character that is masterfully balanced using four hop varieties. Crystal Malt creates the unmistakable malty body. A unique blend of Saaz and Hallertauer hops guarantees a fresh, crisp finish. Special lager yeast produces a long lasting, smooth aftertaste. With a crisp refreshing finish this beer goes down well served chilled on a hot day!", 
                               "Boston Breweries", 
                               "http://www.bostonbreweries.co.za", 
                               "Paarden Eiland, Cape Town", 
                               "Premium Lager", 
                               "4%", 
                               "img/beer_boston_premium_lager.jpg"));
      
      beerList.add(new BeerDto("Jack Black Lager", 
                              "Our flagship Pre-Prohibition style lager is inspired by the all malt beers of the early 1900s. A traditional lager brewed with a Pale Malt, Southern Promise and Saaz hops, achieves a great balance between hop bitterness and malt sweetness.", 
                              "Jack Black", 
                              "http://www.jackblackbeer.com", 
                              "Woodstock, Cape Town", 
                              "Premium Lager", 
                              "5%", 
                              "img/beer_jack_black_lager.jpg"));
      
      beerList.add(new BeerDto("Jack Black Pils", 
                               "Super clean, white head with a crisp straw yellow colour. Look for fine hop bitterness with fresh citrus notes. The perfect summer thirst quencher.", 
                               "Jack Black", 
                               "http://www.jackblackbeer.com", 
                               "Woodstock, Cape Town", 
                               "Pilsner", 
                               "5.2%", 
                               "img/beer_jack_black_lager.jpg")); 
      
      beerList.add(new BeerDto("Jack Black Pale Ale", 
                               "An American Style Pale Ale, lighter in body but with a distinctive flavour profile, and a decidedly fresh taste. Refreshing characteristics balanced with rich malt complexity and a bitterness that lingers to the finish.", 
                               "Jack Black", 
                               "http://www.jackblackbeer.com", 
                               "Woodstock, Cape Town", 
                               "Pale Ale", 
                               "4.5", 
                               "img/beer_jack_black_lager.jpg")); 
     
      
      return beerList;
   }

   @Override
   public Class<GetBeersAction> getActionType() {
      return GetBeersAction.class;
   }

   @Override
   public void undo(GetBeersAction action, GetBeersResult result, ExecutionContext context) throws ActionException {
      // Not undoable
   }
}
