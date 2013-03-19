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
package za.co.brewtour.server.entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author bestermichael
 */
@PersistenceCapable(detachable = "true")
public class Beer {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String beerName;
    @Persistent
    private String description;
    @Persistent
    private String breweryName;
    @Persistent
    private Date introduced;
    @Persistent
    private String abv;
    @Persistent
    private String style;
    @Persistent
    private String imageLink;

    /**
     * Constructs a Beer Entity
     *
     * @param beerName
     * @param breweryName
     * @param introduced
     * @param abv
     * @param style
     * @param imageLink
     */
    public Beer(String beerName, String description, String breweryName, Date introduced, String abv, String style, String imageLink) {
        this.beerName = beerName;
        this.description = description;
        this.breweryName = breweryName;
        this.introduced = introduced;
        this.style = style;
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public String getBeerName() {
        return beerName;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public Date getIntroduced() {
        return introduced;
    }

    public String getAbv() {
        return abv;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public void setIntroduced(Date introduced) {
        this.introduced = introduced;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getStyle() {
        return style;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Key getKey() {
        return key;
    }
}
