package rinf.autoclickerrinf.client.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;

public class ACFMenuScreen extends CottonClientScreen {
    public static int screenWidth, screenHeight;
    public ACFMenuScreen(GuiDescription description) {
        super(description);
    }
    public void init() {
            screenWidth = this.width; screenHeight = this.height;
    }
}
