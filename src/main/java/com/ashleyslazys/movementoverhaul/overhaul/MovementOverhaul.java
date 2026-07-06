package com.ashleyslazys.movementoverhaul.overhaul;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;
import com.ashleyslazys.movementoverhaul.Config;

public class MovementOverhaul implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Config.LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(Config.MOD_ID, path);
	}
}
