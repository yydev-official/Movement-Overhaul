package com.ashleyslazys.movementoverhaul.overhaul;

import com.ashleyslazys.movementoverhaul.network.MovementPackets;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.entity.Pose;

public class MovementOverhaulServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        // Listen for the client's custom movement packet
        ServerPlayNetworking.registerGlobalReceiver(MovementPackets.MOVEMENT_STATE_TYPE, (payload, context) -> {
            context.server().execute(() -> {
                var player = context.player();
                if (payload.isSliding()) {
                    // Force matching pose on server to sync collision bounding boxes
                    player.setPose(Pose.CROUCHING);
                }
            });
        });
    }
}