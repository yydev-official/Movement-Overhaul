package com.ashleyslazys.movementoverhaul.overhaul;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.Identifier;

import org.spongepowered.asm.mixin.Mixin;

import com.ashleyslazys.movementoverhaul.Config;

@Mixin(MinecraftClient.class)
public class MovementOverhaulClient implements ClientModInitializer {

    /*
    TODO:
     - add sliding
     - double jumping
     - lendge holding
     - wall climbing
     - wall jumping
     -  wall running
    */
    @Override
    public void onInitializeClient() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        Config.LOGGER.info("Hello Fabric client world!");
    }


    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Config.MOD_ID, path);
    }
}
