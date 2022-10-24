package rinf.autoclickerrinf.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBinders {
    public static KeyBinding kb_openACFM;
    public static KeyBinding kb_toggleACF;
    public static void keyBinders() {
        kb_openACFM = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.autoclicker-rinf.openacfm",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                "category.rinf-mods.general"
        ));
        kb_toggleACF = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.autoclicker-rinf.toggleacf",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                "category.rinf-mods.general"
        ));
    }
}
