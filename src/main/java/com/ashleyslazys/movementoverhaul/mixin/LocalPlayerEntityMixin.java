package com.ashleyslazys.movementoverhaul.mixin;

import com.ashleyslazys.movementoverhaul.network.MovementPackets.MovementStatePayload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerEntityMixin {

    @Unique private static final int MAX_SLIDE_TICKS = 25;

    @Unique private boolean IsSliding = false;
    @Unique private int SlideTicks = 0;

    @Unique private boolean LastOnGround = false;

    /*
    @Unique
    private void HandleBHOP(LocalPlayer Player) {
        if (Player.onGround() && !LastOnGround && Player.input.keyPresses.jump()) {
            Vec3 Velocity = Player.getDeltaMovement();
            double Speed = Math.sqrt(Velocity.x * Velocity.x + Velocity.z * Velocity.z);

            if (Speed > 0.1) {
                float yaw = Player.getYRot() * Mth.DEG_TO_RAD;

                double SpeedMultiplier = 2;
                double BoostedSpeed = Speed * SpeedMultiplier;

                Player.setDeltaMovement(
                        Velocity.x * -Mth.sin(yaw) * BoostedSpeed,
                        Velocity.y * 0.42,
                        Velocity.z * -Mth.cos(yaw) * BoostedSpeed
                );

                ClientPlayNetworking.send(new MovementStatePayload(IsSliding, true));
            }
        }
    }
    */
    /*
    @Unique
    private void HandleSlide(LocalPlayer Player) {
        if (Player.isSprinting() && Player.input.keyPresses.shift() && Player.onGround() && !IsSliding) {
            IsSliding = true;
            SlideTicks = 0;

            double Burst = 2;

            Vec3 Velocity = Player.getDeltaMovement();
            float yaw = Player.getYRot() * Mth.DEG_TO_RAD;

            Player.setDeltaMovement(
                    Velocity.x * -Mth.sin(yaw) * Burst,
                    Velocity.y,
                    Velocity.z * -Mth.cos(yaw) * Burst
            );
            ClientPlayNetworking.send(new MovementStatePayload(true, false));
        }

        if (IsSliding) {
            SlideTicks++;
            Vec3 currentVel = Player.getDeltaMovement();

            Player.setDeltaMovement(currentVel.x * 0.92, currentVel.y, currentVel.z * 0.92);
            Player.setPose(net.minecraft.world.entity.Pose.CROUCHING);

            if (SlideTicks >= MAX_SLIDE_TICKS || !Player.input.keyPresses.shift()) {
                IsSliding = false;
                ClientPlayNetworking.send(new MovementStatePayload(false, false));
            }
        }
    }
    */

    @Inject(method = "tick", at = @At("HEAD"))
    private void handleCustomMovement(CallbackInfo CallbackInfo_) {
        LocalPlayer Player = (LocalPlayer) (Object) this;

        if (Player.onGround() && !LastOnGround && Player.input.keyPresses.jump()) {
            Vec3 Velocity = Player.getDeltaMovement();
            double Speed = Math.sqrt(Velocity.x * Velocity.x + Velocity.z * Velocity.z);

            if (Speed > 0.1) {
                float yaw = Player.getYRot() * Mth.DEG_TO_RAD;

                double SpeedMultiplier = 2;
                double BoostedSpeed = Speed * SpeedMultiplier;

                Player.setDeltaMovement(
                        -Mth.sin(yaw) * BoostedSpeed,
                        Player.BASE_JUMP_POWER,
                        Mth.cos(yaw) * BoostedSpeed
                );

                ClientPlayNetworking.send(new MovementStatePayload(IsSliding, true));
            }
        }
        LastOnGround = Player.onGround();
        if (Player.isSprinting() && Player.input.keyPresses.shift() && Player.onGround() && !IsSliding) {
            IsSliding = true;
            SlideTicks = 0;

            double Burst = 2;
            float yaw = Player.getYRot() * Mth.DEG_TO_RAD;

            Player.setDeltaMovement(
                    -Mth.sin(yaw) * Burst,
                    Player.getDeltaMovement().y,
                    Mth.cos(yaw) * Burst
            );
            ClientPlayNetworking.send(new MovementStatePayload(true, false));
        }

        if (IsSliding) {
            SlideTicks++;
            Vec3 currentVel = Player.getDeltaMovement();

            Player.setDeltaMovement(currentVel.x * 0.92, currentVel.y, currentVel.z * 0.92);
            Player.setPose(net.minecraft.world.entity.Pose.CROUCHING);

            if (SlideTicks >= MAX_SLIDE_TICKS || !Player.input.keyPresses.shift()) {
                IsSliding = false;
                ClientPlayNetworking.send(new MovementStatePayload(false, false));
            }
        }
    }
}