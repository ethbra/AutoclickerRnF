package rinf.autoclickerrinf.client.gui;

import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class RToggleButton extends SliderWidget {
    public boolean checked;
    public RToggleButton(int x, int y, int width, int height, boolean checked) {
        super(x, y, width, height, Text.empty(), booleanToDouble(checked));
        this.checked = checked;
        this.updateMessage();
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (!checked) {
            checked = true;
            this.value = 1;
        } else {
            checked = false;
            this.value = 0;
        }
        this.updateMessage();
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
    }

    @Override
    protected void updateMessage() {
        if (checked) this.setMessage(Text.literal("ON"));
        else this.setMessage(Text.literal("OFF"));
    }

    @Override
    protected void applyValue() {

    }

    private static double booleanToDouble(boolean b) {
        var d = 0.0;

        if (b) d = 1.0;
        else d = 0.0;

        return d;
    }
}
