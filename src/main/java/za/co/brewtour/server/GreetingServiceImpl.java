/**
 * 
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
 */

package za.co.brewtour.server;

import za.co.brewtour.client.GreetingService;
import za.co.brewtour.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

   public String greetServer(String input) throws IllegalArgumentException {
      // Verify that the input is valid.
      if (!FieldVerifier.isValidName(input)) {
         // If the input is not valid, throw an IllegalArgumentException back to
         // the client.
         throw new IllegalArgumentException("Name must be at least 4 characters long");
      }

      String serverInfo = getServletContext().getServerInfo();
      String userAgent = getThreadLocalRequest().getHeader("User-Agent");

      // Escape data from the client to avoid cross-site script vulnerabilities.
      input = escapeHtml(input);
      userAgent = escapeHtml(userAgent);

      return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
            + userAgent;
   }

   /**
    * Escape an html string. Escaping data received from the client helps to
    * prevent cross-site script vulnerabilities.
    * 
    * @param html
    *           the html string to escape
    * @return the escaped string
    */
   private String escapeHtml(String html) {
      if (html == null) {
         return null;
      }
      return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
   }
}
