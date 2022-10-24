package rinf.autoclickerrinf.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import rinf.autoclickerrinf.Config;
import rinf.autoclickerrinf.client.gui.ACFMenuGui;
import rinf.autoclickerrinf.client.gui.ACFMenuScreen;
import rinf.autoclickerrinf.client.gui.EmptyGui;

@Environment(EnvType.CLIENT)
public class AutoclickerRinfClient implements ClientModInitializer {
    Config config = new Config();
    @Override
    public void onInitializeClient() {
        KeyBinders.keyBinders();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClickController.clickController();
            while (KeyBinders.kb_openACFM.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ACFMenuScreen(new EmptyGui()));
                assert MinecraftClient.getInstance().currentScreen != null;
                MinecraftClient.getInstance().currentScreen.close();
                MinecraftClient.getInstance().setScreen(new ACFMenuScreen(new ACFMenuGui()));
            }
            while (KeyBinders.kb_toggleACF.wasPressed()) ClickController.clickerActive = !ClickController.clickerActive;
        });
        ClientLifecycleEvents.CLIENT_STARTED.register(this::clientStart);
    }
    private void clientStart(MinecraftClient client) {
        config.load();
    }
}
