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
package za.co.brewtour.shared.domain;

import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * JDO-annotated model class for storing image properties; image data is stored
 * as a Blob (large byte array) in the image field.
 * 
 * @author Ivan.Fourie
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Image implements IsSerializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	String key;

	@Persistent
	private String name;

	@Persistent
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private String imageUrl;

	@Persistent
	Date createdDate = new Date();

	/**
	 * @param name
	 * @param imageUrl
	 * @param createdDate
	 */
	public Image(String name, String imageUrl) {
		this(name, imageUrl, new Date());
	}

	/**
	 * @param name
	 * @param imageUrl
	 * @param createdDate
	 */
	public Image(String name, String imageUrl, Date createdDate) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.createdDate = createdDate;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param String
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Image() {
		super();
	}

}
