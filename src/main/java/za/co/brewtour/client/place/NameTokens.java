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

package za.co.brewtour.client.place;

/**
 * Main presenters name tokens.
 * 
 * Tokens starts with bang operator (!) for AJAX SEO
 * 
 * @author Michael Bester
 * @author Ivan Fourie
 */
public class NameTokens {
   public static final String home = "!home";
   public static final String response = "!response";
   public static final String beers = "!beers";

   public static String getHome() {
      return home;
   }

   public static String getResponse() {
      return response;
   }

   public static String getBeerList() {
      return beers;
   }
}
