package rinf.autoclickerrinf.client.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class ClickDelayTextFieldWidget extends TextFieldWidget {
    public ClickDelayTextFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text text) {
        super(textRenderer, x, y, width, height, text);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (!(chr >= '0' && chr <= '9')) {
            return false;
        }
        return super.charTyped(chr, modifiers);
    }
}
