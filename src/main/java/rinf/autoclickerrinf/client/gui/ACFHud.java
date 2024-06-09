package rinf.autoclickerrinf.client.gui;

import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;
import rinf.autoclickerrinf.client.ClickController;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ACFHud {
    private final DrawContext drawContext;
    private final float tickDelta;
    private final List<Drawable> drawables = Lists.newArrayList();
    public final IconWidget jobIndicator;
    public final IconWidget lIndicator;
    public final IconWidget rIndicator;

    public ACFHud(DrawContext drawContext, float tickDelta) {
        this.drawContext = drawContext;
        this.tickDelta = tickDelta;

        Window window = MinecraftClient.getInstance().getWindow();
        int x = window.getScaledWidth() / 2;
        int y = window.getScaledHeight() - 54;

        jobIndicator = IconWidget.create(16, 16,
                Identifier.of("minecraft", "textures/item/barrier.png"), 16, 16);
        lIndicator = IconWidget.create(4, 4,
                Identifier.of("minecraft",
                        ClickController.lClickerActive ? "textures/block/white_shulker_box.png" : "textures/block/gray_shulker_box.png"),
                16, 16);
        rIndicator = IconWidget.create(4, 4,
                Identifier.of("minecraft",
                        ClickController.rClickerActive ? "textures/block/white_shulker_box.png" : "textures/block/gray_shulker_box.png"),
                16, 16);

        jobIndicator.setPosition(x - 8, y);
        lIndicator.setPosition(x - 14, y);
        rIndicator.setPosition(x + 10, y);
        drawables.add(jobIndicator);
        drawables.add(lIndicator);
        drawables.add(rIndicator);

        if (ClickController.getClickerActive()) render();
    }

    private void render() {
        for (Drawable drawable : this.drawables) {
            drawable.render(this.drawContext, -1, -1, tickDelta);
        }
    }
}
