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

import com.google.errorprone.annotations.DoNotCall;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Set;

/**
 * The syntax registry manages syntax registration of a single element.
 * Registering may not be possible after the registration stage is over.
 */
@ApiStatus.Experimental
public interface SyntaxRegistry<I extends SyntaxInfo<?>> {
	
	@Unmodifiable
	Set<I> syntaxes();
	
	@Contract("_ -> this")
	SyntaxRegistry<I> register(I info);
	
	@DoNotCall
	@ApiStatus.Internal
	@Contract("-> new")
	SyntaxRegistry<I> closeRegistration();
	
}