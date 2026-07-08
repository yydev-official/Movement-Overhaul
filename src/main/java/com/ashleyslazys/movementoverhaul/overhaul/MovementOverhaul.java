package com.ashleyslazys.movementoverhaul.overhaul;

import com.ashleyslazys.movementoverhaul.network.MovementPackets;
import net.fabricmc.api.ModInitializer;

import com.ashleyslazys.movementoverhaul.Config;

public class MovementOverhaul implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		MovementPackets.registerPayloads();
		Config.LOGGER.info("Registered payloads!");
	}
}
