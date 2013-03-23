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
package za.co.brewtour.server.entity;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

/**
 * JDO-annotated model class for storing image properties; 
 * image data is stored as a Blob (large byte array) in the image field.
 * 
 * @author Ivan.Fourie
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Image {
	 
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String title;

    @Persistent
    @Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
    private String imageType;

    @Persistent
    private Blob data;

    public Image() {
    	super();
    }
	/**
	 * Constructs a Image Entity
	 * 
	 * @param title
	 * @param imageType
	 * @param data
	 */
	public Image(String title, String imageType, byte[] data) {
		super();
		this.title = title;
		this.imageType = imageType;
		this.data = new Blob(data);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return key.getId();
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the imageType
	 */
	public String getImageType() {
		return imageType;
	}

	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		if (data == null) {
			return null;
		}
		
		return data.getBytes();
	}

	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = new Blob(data);
	}
}
