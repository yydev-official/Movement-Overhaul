package com.ashleyslazys.movementoverhaul.overhaul;

import com.ashleyslazys.movementoverhaul.Config;
import com.ashleyslazys.movementoverhaul.overhaul.utils.MovementOverhaulBindings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

import net.minecraft.client.KeyMapping;

import com.mojang.blaze3d.platform.InputConstants.Type;

import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Unique;


public class MovementOverhaulClient implements ClientModInitializer {

    private static KeyMapping SlideKeybind;

    /*
        TODO:
         - double jumping
         - ledge holding
         - wall climbing
         - wall jumping
         - wall running
    */

    Identifier slideKeyIdentifier = Identifier.parse("movement.bindings.slide");

    @Unique
    public void InitializeKeybinds() {
        SlideKeybind = MovementOverhaulBindings.createNewBinding(
                "bindings.movement_overhaul.slide",              // Translation key
                GLFW.GLFW_KEY_C,                       // The C keycode
                slideKeyIdentifier
        );
    }

    @Override
    public void onInitializeClient() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        InitializeKeybinds();
        Config.LOGGER.info("Hello Fabric client world!");
    }
}
