package rinf.autoclickerrinf.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import rinf.autoclickerrinf.Config;
import rinf.autoclickerrinf.client.gui.ACFHud;
import rinf.autoclickerrinf.client.gui.ACFMenuScreen;

@Environment(EnvType.CLIENT)
public class AutoclickerRinfClient implements ClientModInitializer {
    public static Config config = new Config();
    public static ACFHud acfHud;
    @Override
    public void onInitializeClient() {
        KeyBinders.keyBinders();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClickController.Update();
            while (KeyBinders.kb_openACFM.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ACFMenuScreen());
            }
            while (KeyBinders.kb_toggleACF.wasPressed()) {
                ClickController.lTimer = 0;
                ClickController.rTimer = 0;
                ClickController.setClickerActive(!ClickController.getClickerActive());
            }
        });
        HudRenderCallback.EVENT.register(((drawContext, tickDelta) -> acfHud = new ACFHud(drawContext, tickDelta) ));
        ClientLifecycleEvents.CLIENT_STARTED.register(this::clientStart);
    }
    private void clientStart(MinecraftClient client) {
        config.load();
    }
}
