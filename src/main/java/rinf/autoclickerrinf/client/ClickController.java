package rinf.autoclickerrinf.client;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.glfw.GLFW;
import rinf.autoclickerrinf.client.gui.ACFHud;
import rinf.autoclickerrinf.client.gui.ACFMenuGui;

public class ClickController extends ACFMenuGui {
    public static int clickDelayInt = 0;
    public static boolean clickerActive = false;
    public static boolean targetEntityMode = false;
    public static boolean attackCooldownMode = false;
    private static int i = 0;
    public static void clickController() {
        if (!clickerActive) CottonHud.remove(ACFHud.item);
        if (clickerActive) {
            CottonHud.add(ACFHud.item, ACFHud.itemPositioner());
            assert MinecraftClient.getInstance().player != null;
            if (MinecraftClient.getInstance().currentScreen != null) clickerActive = false;
            if (!targetEntityMode) {
                if (attackCooldownMode) {
                    if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1.0F) {
                        KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                    }
                } else {
                    if (i < clickDelayInt) i++;
                    else {
                        KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                        i = 0;
                    }
                }
            } else {
                if (MinecraftClient.getInstance().targetedEntity != null) {
                    if (attackCooldownMode) {
                        if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1.0F) {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                        }
                    } else {
                        if (i < clickDelayInt) i++;
                        else {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                            i = 0;
                        }
                    }
                }
            }
            if (!MinecraftClient.getInstance().player.input.getMovementInput().equals(new Vec2f(0, 0))) clickerActive = false;
            if (!clickerActive) CottonHud.remove(ACFHud.item);
        }
    }
}
