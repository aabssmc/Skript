/**
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright Peter Güttinger, SkriptLang team and contributors
 */
package org.skriptlang.skript;

import org.jetbrains.annotations.ApiStatus;
import org.skriptlang.skript.registration.SkriptRegistry;

/**
 * The main class for everything related to Skript.
 * This is separated from platform-specific implementations.
 */
@ApiStatus.Experimental
public interface Skript {
	
	/**
	 * Returns the current Skript instance, can be updated with {@link Skript#setInstance(Skript)}
	 *
	 * @return {@link Skript}
	 */
	static Skript instance() {
		return SkriptImpl.instance();
	}

	/**
	 * Updates the Skript instance returned by {@link Skript#instance()}. Because state doesn't get preserved,
	 * it is recommended to update the Skript instance before any syntax loading.
	 *
	 * @param instance The new Skript instance
	 */
	static void setInstance(Skript instance) {
		SkriptImpl.setInstance(instance);
	}
	
	/**
	 * @return {@link SkriptRegistry}
	 */
	SkriptRegistry registry();
	
	/**
	 * @return The current state Skript is in
	 */
	State state();
	
	@ApiStatus.Internal
	void updateState(State state);
	
	enum State {
		REGISTRATION(true),
		ENDED_REGISTRATION(false),
		CLOSED_REGISTRATION(false);

		private final boolean acceptsRegistration;
		
		State(boolean acceptsRegistration) {
			this.acceptsRegistration = acceptsRegistration;
		}
		
		public boolean acceptsRegistration() {
			return acceptsRegistration;
		}
		
	}
	
}