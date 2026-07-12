package com.ashleyslazys.movementoverhaul.network;

import com.ashleyslazys.movementoverhaul.Config;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class MovementPackets {
    public static final CustomPacketPayload.Type<MovementStatePayload> MOVEMENT_STATE_TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Config.MOD_ID, "movement_state"));

    public record MovementStatePayload(boolean isSliding, boolean isDoubleJump) implements CustomPacketPayload {
        public static final StreamCodec<FriendlyByteBuf, MovementStatePayload> CODEC = CustomPacketPayload.codec(
                MovementStatePayload::write, MovementStatePayload::new
        );

        private MovementStatePayload(FriendlyByteBuf buffer) {
            this(buffer.readBoolean(), buffer.readBoolean());
        }

        private void write(FriendlyByteBuf buffer) {
            buffer.writeBoolean(isSliding);
            buffer.writeBoolean(isDoubleJump);
        }

        @Override
        public @NonNull Type<? extends CustomPacketPayload> type() {
            return MOVEMENT_STATE_TYPE;
        }
    }

    public static void registerPayloads() {
        // In Fabric API 26.1, playC2S() was renamed to serverboundPlay()
        PayloadTypeRegistry.serverboundPlay().register(
                MOVEMENT_STATE_TYPE,
                MovementStatePayload.CODEC
        );
    }
}