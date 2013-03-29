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

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.FileUpload;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.Form.SubmitCompleteEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ImageUploadView extends ViewWithUiHandlers<ImageUploadUiHandlers> implements ImageUploadPresenter.MyView {

	public interface Binder extends UiBinder<Widget, ImageUploadView> {
	}

	int imageSize = 0;

	@UiField
	Hidden filenameField;

	@UiField
	Button uploadButton;

	@UiField
	Form uploadForm;

	@UiField
	FileUpload uploadFile;

	@UiField
	HTML serverResponse;

	@UiField
	Image imageField;

	@Inject
	public ImageUploadView(final Binder binder) {
		initWidget(binder.createAndBindUi(this));

		// Disable the button until we get the URL to POST to
		setUploadButton("Loading...", false);
		uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadForm.setMethod(FormPanel.METHOD_POST);
		uploadFile.setName("image");
	}

	@UiHandler("uploadForm")
	void onSubmit(SubmitCompleteEvent e) {
		if (getUiHandlers() != null)
			getUiHandlers().onSubmitCompeteResult(e);
	}

	@Override
	public void setServerResponse(String serverResponse) {
		this.serverResponse.setHTML(serverResponse);
	}

	@Override
	public void setUploadUrl(String url) {
		uploadForm.setAction(url);
		setUploadButton("Upload", true);
	}

	@UiHandler("uploadButton")
	void onSubmit(ClickEvent e) {
		checkSetFilename();
		submitForm();
		setUploadButton("Uploading...", false);
	}

	@Override
	public void setUploadButton(String text, boolean enabled) {
		uploadButton.setText(text);
		uploadButton.setEnabled(enabled);
	}

	@UiHandler("uploadFile")
	void onUpoadFileChange(ChangeEvent event) {
		checkSetFilename();
	}

	public void checkSetFilename() {
		if (filenameField.getValue().isEmpty()) {
			filenameField.setValue(basename(uploadFile.getFilename()));
		}
	}

	@Override
	public void submitForm() {
		checkSetFilename();
		uploadForm.submit();
	}

	/**
	 * Gets the image URL for this widget. If a new image has been uploaded this
	 * URL will be genereated.
	 * 
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageField.getUrl();
	}

	/**
	 * Sets the image URL for this widget
	 * 
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		imageField.setUrl(imageUrl);
	}

	/**
	 * return the name of a file without path.
	 */
	private static String basename(String name) {
		return name.replaceAll("^.*[/\\\\]", "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * za.co.brewtour.client.application.imageupload.ImageUploadPresenter.MyView
	 * #resetForm()
	 */
	@Override
	public void resetForm() {
		uploadForm.reset();
	}
}
