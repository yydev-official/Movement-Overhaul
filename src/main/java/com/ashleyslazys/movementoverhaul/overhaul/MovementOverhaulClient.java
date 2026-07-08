package com.ashleyslazys.movementoverhaul.overhaul;

import net.fabricmc.api.ClientModInitializer;

import com.ashleyslazys.movementoverhaul.Config;

public class MovementOverhaulClient implements ClientModInitializer {

    /*
        TODO:
         - double jumping
         - ledge holding
         - wall climbing
         - wall jumping
         - wall running
    */
    @Override
    public void onInitializeClient() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        Config.LOGGER.info("Hello Fabric client world!");
    }
}
