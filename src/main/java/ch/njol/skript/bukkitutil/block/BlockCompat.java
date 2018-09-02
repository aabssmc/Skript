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
 *
 * Copyright 2011-2017 Peter Güttinger and contributors
 */
package ch.njol.skript.bukkitutil.block;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.FallingBlock;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;

/**
 * Methods which operate with blocks but are not compatible across some
 * Minecraft versions.
 */
public interface BlockCompat {
	
	/**
	 * Instance of BlockCompat for current Minecraft version.
	 */
	static final BlockCompat INSTANCE = Skript.isRunningMinecraft(1, 13)
			? new NewBlockCompat() : new MagicBlockCompat();
	
	/**
	 * Gets block values from a block state. They can be compared to other
	 * values if needed, but cannot be used to retrieve any other data.
	 * @param block Block state to retrieve value from.
	 * @return Block values.
	 */
	@Nullable
	BlockValues getBlockValues(BlockState block);
	
	/**
	 * Gets block values from a block. They can be compared to other values
	 * if needed, but cannot be used to retrieve any other data.
	 * @param block Block to retrieve value from.
	 * @return Block values.
	 */
	@Nullable
	@SuppressWarnings("null")
	default BlockValues getBlockValues(Block block) {
		return getBlockValues(block.getState());
	}
	
	/**
	 * Creates a block state from a falling block.
	 * @param entity Falling block entity
	 * @return Block state.
	 */
	BlockState fallingBlockToState(FallingBlock entity);
	
	@Nullable
	default BlockValues getBlockValues(FallingBlock entity) {
		return getBlockValues(fallingBlockToState(entity));
	}

	/**
	 * Creates new block values for given material and state.
	 * @param type Block material.
	 * @param states Block states, as used in /setblock command in Minecraft.
	 * @return Block values, or null if given state was invalid.
	 */
	@Nullable
	BlockValues createBlockValues(Material type, Map<String, String> states);
	
	/**
	 * Checks whether the given material implies emptiness. On Minecraft 1.13+,
	 * there are several blocks that do so.
	 * @param type Material of block.
	 * @return Whether the material implies empty block.
	 */
	boolean isEmpty(Material type);
	
	/**
	 * Checks whether the given material is a liquid.
	 * @param type Material of block.
	 * @return Whether the material is liquid.
	 */
	boolean isLiquid(Material type);
}