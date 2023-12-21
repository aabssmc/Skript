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

import ch.njol.skript.registrations.Classes;
import org.skriptlang.skript.lang.converter.Converters;
import org.skriptlang.skript.registration.SyntaxRegistry;

final class SkriptImpl implements Skript {

	private final SyntaxRegistry registry = SyntaxRegistry.createInstance();

	private State state = State.REGISTRATION;

	@Override
	public SyntaxRegistry registry() {
		return registry;
	}

	@Override
	public State state() {
		return state;
	}

	@Override
	public void updateState(State state) {
		if (state == State.ENDED_REGISTRATION) {
			Converters.createChainedConverters();
		} else if (state == State.CLOSED_REGISTRATION) {
			registry.closeRegistration();
			Classes.onRegistrationsStop();
		}

		this.state = state;
	}

}