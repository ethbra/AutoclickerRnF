package rinf.autoclickerrinf.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.glfw.GLFW;

public class ClickController {
    public static int lClickDelayInt = 0;
    public static int rClickDelayInt = 0;
    private static boolean _clickerActive = false;
    public static boolean lClickerActive = false;
    public static boolean rClickerActive = false;
    public static boolean lHoldMode = false;
    public static boolean rHoldMode = false;
    public static boolean lTargetEntityMode = false;
    public static boolean rTargetEntityMode = false;
    public static boolean lCooldownAttackMode = false;
    public static int lTimer = 0;
    public static int rTimer = 0;

    public static void Update()
    {
        if (getClickerActive()) {
            assert MinecraftClient.getInstance().player != null;
            if (MinecraftClient.getInstance().currentScreen != null) setClickerActive(false);

            if (lClickerActive) {
                if (!lHoldMode) {
                    if (!lTargetEntityMode) {
                        if (lCooldownAttackMode) {
                            if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                            }
                        } else {
                            if (lTimer < lClickDelayInt) lTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                lTimer = 0;
                            }
                        }
                    } else {
                        if (MinecraftClient.getInstance().targetedEntity != null) {
                            if (lCooldownAttackMode) {
                                if (MinecraftClient.getInstance().player.getAttackCooldownProgress(0) == 1) {
                                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                }
                            } else {
                                if (lTimer < lClickDelayInt) lTimer++;
                                else {
                                    KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1));
                                    lTimer = 0;
                                }
                            }
                        }
                    }
                } else {
                    KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), true);
                }
            }
            if (rClickerActive) {
                if (!rHoldMode) {
                    if (!rTargetEntityMode) {
                        if (rTimer < rClickDelayInt) rTimer++;
                        else {
                            KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                            rTimer = 0;
                        }
                    } else {
                        if (MinecraftClient.getInstance().targetedEntity != null) {
                            if (rTimer < rClickDelayInt) rTimer++;
                            else {
                                KeyBinding.onKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2));
                                rTimer = 0;
                            }
                        }
                    }
                } else {
                    KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2), true);
                }
            }
            if (!MinecraftClient.getInstance().player.input.getMovementInput().equals(new Vec2f(0, 0))) setClickerActive(false);
        }
    }

    public static void setClickerActive(boolean active) {
        if (!active) {
            if (lHoldMode) KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_1), false);
            if (rHoldMode) KeyBinding.setKeyPressed(InputUtil.Type.MOUSE.createFromCode(GLFW.GLFW_MOUSE_BUTTON_2), false);
        }
        _clickerActive = active;
    }
    public static boolean getClickerActive() { return _clickerActive; }
    public static void saveValues() {
        AutoclickerRinfClient.config.set(
                lClickerActive,
                rClickerActive,
                lClickDelayInt,
                rClickDelayInt,
                lHoldMode,
                rHoldMode,
                lTargetEntityMode,
                rTargetEntityMode,
                lCooldownAttackMode
        );
    }
}
