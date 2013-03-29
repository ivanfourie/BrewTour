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

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import za.co.brewtour.shared.domain.Image;
import za.co.brewtour.server.persistence.PMF;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet that stores uploaded images as blobs and returns an image serving
 * url
 * 
 * @author Ivan Fourie
 */
public class ImageUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ImageUploadServlet.class.getName());
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private static final String UPLOAD_URL = "/upload";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// do security check
		checkSecurity();
		
		String imageName = request.getParameter("name");
		if (imageName != null && !imageName.isEmpty()) {
			Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
			List<BlobKey> blobKeys = blobs.get("image");
	
			if (blobKeys != null) {
				// get first blobKey and serve it
				BlobKey blobKey = blobKeys.get(0);
	
				ImagesService imagesService = ImagesServiceFactory.getImagesService();
	
				// Get the image serving URL
				ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blobKey);
				String imageUrl = tidyUrl(imagesService.getServingUrl(options));
				log.info("Serving URL created: " + imageUrl);
				// save image metadata to datastore
				saveImage(imageName, imageUrl);
	
				response.sendRedirect("/upload?imageUrl=" + imageUrl);
			} else {
				log.warning("BlobKeys are null!");
				response.sendError(500, "BlobKeys are null!");
			}
		}
		else {
			log.warning("Image name cannot be null!");
			response.sendError(500, "Image name cannot be null!");
		}

	}

	/**
	 * @param imageName
	 * @param imageUrl
	 */
	private void saveImage(String imageName, String imageUrl) {
	    PersistenceManager pm = PMF.get().getPersistenceManager();

	    Image img = new Image(imageName, imageUrl);
	    
	    try {
	    	log.info("Saved image metadata: " + img.getName());
	    	pm.makePersistent(img);
	    } finally {
	        pm.close();
	    }
	}

	/**
	 * Strips out {@link http://0.0.0.0:8888} from URL which is problematic in GAE development mode
	 * 
	 * @param imageUrl
	 */
	private String tidyUrl(String imageUrl) {
		return imageUrl.replaceFirst("http://0.0.0.0:8888", "");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String imageUrl = request.getParameter("imageUrl");
		response.setHeader("Content-Type", "text/html");
		if (imageUrl != null) {
			// print the url
			printUrl(response.getWriter(), imageUrl);
		} else {
			printForm(response.getWriter());
		}
	}

	/**
	 * Prints an html form for image upload
	 * 
	 * @param writer
	 */
	private void printForm(PrintWriter writer) {
		writer.println("<form action='" +  getUploadUrl() + "' enctype='multipart/form-data' method='post'>");
		writer.println("File:<input type='file' name='image' size='40' />");
		writer.println("<input type='submit' value='Upload'>");
		writer.println("</form>"); 
	}

	/**
	 * Returns the upload URL for images
	 * 
	 * @return uploadUrl
	 */
	private String getUploadUrl() {
		return blobstoreService.createUploadUrl(UPLOAD_URL);
	}

	/**
	 * Prints image url in the response object
	 * 
	 * @param imageUrl
	 */
	private void printUrl(PrintWriter writer, String imageUrl) {
		// TODO: wrap this in a JSON response
		writer.println(imageUrl);
	}

	/**
	 * (Stub) Checks if user is authenticated and authorised
	 * 
	 */
	private void checkSecurity() {
		// TODO: check with security provider (to be implemented) and throw an
		// exception
	}
	
	
}