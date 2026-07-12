package com.ashleyslazys.movementoverhaul.mixin;

import com.ashleyslazys.movementoverhaul.network.MovementPackets.MovementStatePayload;
import com.ashleyslazys.movementoverhaul.overhaul.utils.MovementOverhaulBindings;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.stats.RecipeBook;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerEntityMixin {

    @Unique private static final int MAX_SLIDE_TICKS = 25;

    @Unique private boolean isSliding = false;
    @Unique private int slideTicks = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    private void handleCustomMovement(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;

        boolean lastOnGround = player.onGround();

        // roll logic
        if (lastOnGround && player.input.keyPresses.shift()) {
            player.setDeltaMovement(
                player.getDeltaMovement().x,
                0,
                player.getDeltaMovement().z
            );
        }

        boolean SlideKeyClicked = MovementOverhaulBindings.OnBindingPress("bindings.movement_overhaul.slide");

        // Slide Initialization Logic
        if (player.isSprinting() && player.onGround() && SlideKeyClicked && !isSliding) {
            isSliding = true;
            slideTicks = 0;

            double burst = 2;
            float yaw = player.getYRot() * Mth.DEG_TO_RAD;

            player.setDeltaMovement(
                -Mth.sin(yaw) * burst,
                player.getDeltaMovement().y,
                Mth.cos(yaw) * burst
            );

            ClientPlayNetworking.send(new MovementStatePayload(true, false));
        }

        // Active Slide Ticking Logic
        if (isSliding) {
            slideTicks++;
            Vec3 currentVel = player.getDeltaMovement();

            player.setDeltaMovement(currentVel.x * 0.92, currentVel.y, currentVel.z * 0.92);
            player.setPose(Pose.SLIDING);

            if (slideTicks >= MAX_SLIDE_TICKS || !SlideKeyClicked) {
                isSliding = false;
                ClientPlayNetworking.send(new MovementStatePayload(false, false));
            }
        }
    }
}