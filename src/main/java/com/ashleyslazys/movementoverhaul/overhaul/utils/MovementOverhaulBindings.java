package com.ashleyslazys.movementoverhaul.overhaul.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

import java.util.Objects;

public class MovementOverhaulBindings {
    public static KeyMapping createNewBinding(String name, int Key, Identifier KeyIdentifier) {
        return KeyMappingHelper.registerKeyMapping(new KeyMapping(
                name,                                       // Translation key
                InputConstants.Type.KEYSYM,                 // Type of input (Keyboard)
                Key,                                        // The C keycode
                new KeyMapping.Category(KeyIdentifier)      // Keybinding category
        ));
    }

    // Returns true when the Binding with the given name is pressed
    public static boolean OnBindingPress(String name) {
        return Objects.requireNonNull(KeyMapping.get(name)).consumeClick();
    }

    // Returns true when the Binding with the given name is held
    public static boolean OnBindingHeld(String name) {
        return  Objects.requireNonNull(KeyMapping.get(name)).isDown();
    }
}
