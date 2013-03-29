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
package za.co.brewtour.server.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.brewtour.shared.domain.Image;
import za.co.brewtour.server.persistence.PMF;

/**
 * GET requests return the promotional image associated with the movie with the
 * title specified by the title query string parameter.
 * 
 * @author Ivan Fourie
 */
public class GetImageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetImageServlet.class.getName());

	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        Image image = getImage(name);

        if (image != null && image.getImageUrl() != null)  {
        	log.info("Redirecting to image: " + image.getName() + " [" + image.getImageUrl() + "]");
            resp.sendRedirect(image.getImageUrl());
        } else {
            // If no image is found with the given title, redirect the user to a static image
            resp.sendRedirect("/static/noimage.png");
        }
    }

	/**
	 * Queries the datastore for the Image object with the passed-in title. If
	 * found, returns the Image object; otherwise, returns null.
	 *
	 * @param title image title to look up
	 */
	private Image getImage(String name) {
	    PersistenceManager pm = PMF.get().getPersistenceManager();

	    // Search for any Movie object with the passed-in title; limit the number
	    // of results returned to 1 since there should be at most one movie with
	    // a given title
	    Query query = pm.newQuery(Image.class, "title == titleParam");
	    query.declareParameters("String titleParam");
	    query.setRange(0, 1);

	    try {
	        List<Image> results = (List<Image>) query.execute(name);
	        if (results.iterator().hasNext()) {
	            // If the results list is non-empty, return the first (and only)
	            // result       	
	            return results.get(0);
	        }
	    } finally {
	        query.closeAll();
	        pm.close();
	    }

	    return null;
	}
}
