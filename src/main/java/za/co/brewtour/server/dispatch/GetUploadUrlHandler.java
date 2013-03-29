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

import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import za.co.brewtour.shared.dispatch.GetUploadUrlAction;
import za.co.brewtour.shared.dispatch.GetUploadUrlResult;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Handler to fetch a list of beers. Binds to {@code GetUploadUrlAction.class}
 * in ctor.
 * 
 * @author Ivan Fourie
 */
public class GetUploadUrlHandler extends AbstractActionHandler<GetUploadUrlAction, GetUploadUrlResult> {
	@Autowired
	private ServletContext servletContext;

	private static final Logger log = Logger.getLogger(GetUploadUrlHandler.class.getName());
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	/**
	 * The upload url of the servlet handling blob uploads
	 */
	private static final String UPLOAD_URL = "/upload";

	public GetUploadUrlHandler() {
		super(GetUploadUrlAction.class);
	}

	/**
	 * Gets the upload url for images
	 * 
	 * @return {@literal GetUploadUrlResult}
	 */
	@Override
	public GetUploadUrlResult execute(GetUploadUrlAction action, ExecutionContext context) throws ActionException {
		return new GetUploadUrlResult(getUploadUrl());
	}

	/**
	 * Returns the upload URL for images
	 * 
	 * @return {@literal uploadUrl}
	 */
	private String getUploadUrl() {
	   String uploadUrl = blobstoreService.createUploadUrl(UPLOAD_URL);
	   log.info("Created Blobstore upload URL - " + uploadUrl);
	   return uploadUrl;
   }

	@Override
	public Class<GetUploadUrlAction> getActionType() {
		return GetUploadUrlAction.class;
	}

	@Override
	public void undo(GetUploadUrlAction action, GetUploadUrlResult result, ExecutionContext context)
			throws ActionException {
		// Not undoable
	}
}
