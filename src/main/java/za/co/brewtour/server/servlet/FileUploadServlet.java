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

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import za.co.brewtour.server.entity.Image;
import za.co.brewtour.server.persistence.PMF;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivan Fourie
 */
public class FileUploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FileUploadServlet.class.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			ServletFileUpload upload = new ServletFileUpload();
			res.setContentType("text/plain");

			FileItemIterator iterator = upload.getItemIterator(req);
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				InputStream stream = item.openStream();

				if (item.isFormField()) {
					log.warning("Got a form field: " + item.getFieldName());
				} else {
					log.warning("Got an uploaded file: " + item.getFieldName()
							+ ", name = " + item.getName());
					
					// TODO check security
					
					String fileName = item.getName();
					//TODO: Extensions can't be trusted; consider using JMimeMagic or Apache Tika
					String mimeType = getServletContext().getMimeType(fileName);
					if (isImage(mimeType)) {
					    // It's an image.
						
						byte[] data = readFromStream(stream);
						
						saveImage(fileName, mimeType, data);

						res.sendRedirect("/image?title=" + fileName);
					}
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private boolean isImage(String mimeType) {
		if (mimeType.startsWith("image"))
		    return true;
		
		return false;    	
	}
	
	private Image saveImage(String fileName, String imageType, byte[] data) {
	    PersistenceManager pm = PMF.get().getPersistenceManager();

	    Image img = new Image(fileName, imageType, data);
	    
	    try {
	    	pm.makePersistent(img);
	    } finally {
	        pm.close();
	    }

	    return null;
	}
	
	// TODO: move to file util
	private byte[] readFromStream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		byte[] data = new byte[4096];
		int count = inputStream.read(data);
		while (count != -1) {
			dos.write(data, 0, count);
			count = inputStream.read(data);
		}

		return baos.toByteArray();
	}
}