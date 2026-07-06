package com.ashleyslazys.movementoverhaul.overhaul;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.resources.Identifier;

import com.ashleyslazys.movementoverhaul.Config;

public class MovementOverhaulServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        Config.LOGGER.info("Hello Fabric server world!");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Config.MOD_ID, path);
    }
}
