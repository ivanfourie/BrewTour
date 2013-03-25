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

import za.co.brewtour.server.entity.Image;
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
 * A servlet that stores uploaded images as blobs and returns an image serving url
 * 
 * @author Ivan Fourie
 */
public class ImageUploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FileUploadServlet.class.getName());
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
        List<BlobKey> blobKeys = blobs.get("image");
         
        if (blobKeys == null) {
            log.warning("BlobKeys are null!");
        } else {
        	// get first blobKey and serve it
        	BlobKey blobKey = blobKeys.get(0);
 
            ImagesService imagesService = ImagesServiceFactory.getImagesService();
 
            // Get the image serving URL
            ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blobKey);
            
            String imageUrl = imagesService.getServingUrl(options);
 
            // TODO: store image entity in datastore
            // new Image entity
            // blobKey
            // UploadedImage.CREATED_USER - currentUser
            // UploadedImage.CREATED_AT - new Date());
            // UploadedImage.SERVING_URL - imageUrl);
            // saveImage()
 
            response.sendRedirect(imageUrl);
        }
        
	}
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<form action='" + blobstoreService.createUploadUrl("/upload") + "' enctype='multipart/form-data' method='post'>");
		out.println("File:<input type='file' name='image' size='40' />");
		out.println("<input type='submit' value='Upload'>");
		out.println("</form>");
		out.flush();
		
	}
	
}