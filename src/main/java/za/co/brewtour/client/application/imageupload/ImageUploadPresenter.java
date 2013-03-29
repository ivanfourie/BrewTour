/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package za.co.brewtour.client.application.imageupload;

import za.co.brewtour.shared.dispatch.GetUploadUrlAction;
import za.co.brewtour.shared.dispatch.GetUploadUrlResult;
import com.github.gwtbootstrap.client.ui.Form.SubmitCompleteEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ImageUploadPresenter extends PresenterWidget<ImageUploadPresenter.MyView> implements ImageUploadUiHandlers {

	/**
	 * {@link ImageUploadPresenter}'s view.
	 */
	public interface MyView extends View, HasUiHandlers<ImageUploadUiHandlers> {
		void setUploadUrl(String url);
		
		void setServerResponse(String serverResponse);

		void setImageUrl(String url);

		String getImageUrl();

		void setUploadButton(String text, boolean enabled);

		void submitForm();

		void resetForm();

	}

	private final DispatchAsync dispatcher;
	
	int imageSize;

	@Inject
	public ImageUploadPresenter(EventBus eventBus, MyView view, DispatchAsync dispatcher) {
		super(eventBus, view);

		this.dispatcher = dispatcher;
		
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();
		startUploadSession();
	}
	
	private void startUploadSession() {
		getView().setServerResponse("Getting upload URL");
		dispatcher.execute(new GetUploadUrlAction(), new AsyncCallback<GetUploadUrlResult>() {
			@Override
			public void onFailure(Throwable caught) {
				getView().setServerResponse("An error occured: " + caught.getMessage());
				getView().setUploadButton("Not available", false);
			}

			@Override
			public void onSuccess(GetUploadUrlResult result) {
				getView().setUploadUrl(result.getUploadUrl());
				getView().setServerResponse("");
			}
		});
	}
	
	private void handleResult(String result) {
		if (result != null) {
			getView().setImageUrl(result+calculateImageSize());
			getView().setServerResponse("Image uploaded");
			getView().setUploadButton("Upload", true);
		} 
		else {
			getView().setServerResponse("Got no result from server");
		}
	}
	
	/**
	 * @return the imageSize
	 */
	public int getImageSize() {
		return imageSize;
	}

	/**
	 * @param imageSize the imageSize to set
	 */
	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
	}
	
	/**
	 * @return
	 */
	private String calculateImageSize() {
		if (imageSize > 0)
			return "=s" + imageSize;
		
		return "";
	}

	/* (non-Javadoc)
	 * @see za.co.brewtour.client.application.imageupload.ImageUploadUiHandlers#onSubmitCompeteResult(com.github.gwtbootstrap.client.ui.Form.SubmitCompleteEvent)
	 */
	@Override
	public void onSubmitCompeteResult(SubmitCompleteEvent event) {
		String result = event.getResults();
		handleResult(result);
		getView().resetForm();
	}
}
