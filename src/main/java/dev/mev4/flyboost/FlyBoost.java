package dev.mev4.flyboost;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.lwjgl.glfw.GLFW;

public class FlyBoost implements ClientModInitializer {
    private static final KeyBinding FLY_BOOST_KEY_BINDING = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("fly_boost", GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories.gameplay")
    );

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTick);
    }

    private void onClientTick(MinecraftClient client) {
        if (FLY_BOOST_KEY_BINDING.isPressed()) {
            ClientPlayerEntity player = client.player;
            ClientWorld world = client.world;
            if (player != null && world != null && player.isCreative() && player.getAbilities().allowFlying) {
                player.getAbilities().setFlySpeed(0.3f); // Change this value to adjust the fly speed
            }
        } else {
            ClientPlayerEntity player = client.player;
            if (player != null && player.isCreative()) {
                player.getAbilities().setFlySpeed(0.05f); // Change this value to adjust the default fly speed
            }
        }
    }
}
