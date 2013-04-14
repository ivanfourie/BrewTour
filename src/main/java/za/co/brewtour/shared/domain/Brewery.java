/*
 * Copyright 2013 BrewTour.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package za.co.brewtour.shared.domain;

import com.google.appengine.api.datastore.Key;
import java.util.Date;
import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * JDO Entity for the Brewery Object
 *
 * @author bestermichael
 */
@PersistenceCapable(detachable = "true")
public class Brewery {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String name;
	@Persistent
	private Date dateStarted;
	@Persistent
	private String country;
	@Persistent
	private String state_province;
	@Persistent
	private String city;
	@Persistent
	private String logoUrl;
	@Persistent(mappedBy = "brewery")
	private List<Beer> beers;

	public Brewery(String name, Date dateStarted, String country, String state_province, String city, String logoUrl) {
		this.name = name;
		this.dateStarted = dateStarted;
		this.country = country;
		this.state_province = state_province;
		this.city = city;
		this.logoUrl = logoUrl;
	}

	public Key getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public String getCountry() {
		return country;
	}

	public String getState_province() {
		return state_province;
	}

	public String getCity() {
		return city;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public List<Beer> getBeers() {
		return beers;
	}

	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}
}
