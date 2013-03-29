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
package za.co.brewtour.client.security;

import za.co.brewtour.shared.dto.CurrentUser;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

/**
 * Gatekeeper for administration places
 * 
 * @author Ivan Fourie
 */
public class AdminGatekeeper implements Gatekeeper {
	
	private final CurrentUser currentUser;
	
	@Inject
    public AdminGatekeeper(final CurrentUser currentUser) {
            this.currentUser = currentUser;
    }
    
	/* (non-Javadoc)
	 * @see com.gwtplatform.mvp.client.proxy.Gatekeeper#canReveal()
	 */
	@Override
	public boolean canReveal() {
		//TODO user access management
		//return currentUser.getRoles().contains("ROLE_ADMIN");
		return true;
	}

}
