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
package za.co.brewtour.client.application.admin.image;

import za.co.brewtour.client.application.header.HeaderView;
import za.co.brewtour.shared.domain.Image;
import za.co.brewtour.shared.dto.BeerDto;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.DataGrid;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.github.gwtbootstrap.client.ui.Pagination;
import com.github.gwtbootstrap.client.ui.SimplePager;
import com.github.gwtbootstrap.client.ui.SubmitButton;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.SimpleEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author Ivan Fourie
 */
public class ImageAdminView extends ViewImpl implements
		ImageAdminPresenter.MyView, Editor<Image> {
	public interface Binder extends UiBinder<Widget, ImageAdminView> {
	}

	SimpleEditor<Integer> id = SimpleEditor.of();

	@UiField
	TextBox name;

	@UiField
	ControlGroup nameControlGroup;

	@UiField
	@Editor.Ignore
	HelpInline nameHelpInline;

	/*
	public enum BeerStyle implements HasDisplayLabel {
		NONE("none"),
		JAVE("Java"),
		PYTHON("Python"),
		C("C"),
		CSHARTP("C#");

		private final String displayLabel;

		private BeerStyle(String displayLabel) {
			this.displayLabel = displayLabel;
		}

		@Override
		public String getDisplayLabel() {
			return displayLabel;
		}
	}
	
	
	@UiField(provided = true)
	ValueListBox<BeerDto.Favorite> favorite;
	*/
	
	@UiField(provided = true)
	CellTable<Image> exampleTable = new CellTable<Image>(5,	GWT.<CellTable.SelectableResources> create(CellTable.SelectableResources.class));

	@UiField(provided = true)
	DataGrid<Image> exampleDataGrid = new DataGrid<Image>(20, GWT.<DataGrid.SelectableResources> create(DataGrid.SelectableResources.class));

	@UiField
	SubmitButton saveButton;

	/*
	@UiField
	DateBox birthDay;
	*/
	
	// @UiField
	Pagination pagination = new Pagination();

	@UiField
	Pagination dataGridPagination;

	@UiField
	Form submitExampleForm;

	SimplePager pager = new SimplePager();
	SimplePager dataGridPager = new SimplePager();

	ListDataProvider<BeerDto> dataProvider = new ListDataProvider<BeerDto>();
	
	interface Driver extends SimpleBeanEditorDriver<Image, ImageAdminView> {
	}

	@Inject
	public ImageAdminView(final Binder binder, HeaderView header) {
		initWidget(binder.createAndBindUi(this));
	}
	
	@Override
	   public void resetAndFocus() {
	      // Focus the cursor on the name field when the app loads
			name.setFocus(true);
			name.selectAll();
	   }
}
