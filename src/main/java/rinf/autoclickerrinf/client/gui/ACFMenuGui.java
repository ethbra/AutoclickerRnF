package rinf.autoclickerrinf.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import rinf.autoclickerrinf.Config;
import rinf.autoclickerrinf.client.ClickController;

public class ACFMenuGui extends LightweightGuiDescription {
    Config config = new Config();
    WPlainPanel rootP = new WPlainPanel();
    WLabel settingsMenuL = new WLabel(Text.translatable("label.autoclicker-rinf.settingmenul"), 0xFFFFFF);
    WText clickDelayT = new WText(Text.translatable("text.autoclicker-rinf.clickdelay"), 0xFFFFFF);
    WText targetEntityModeT = new WText(Text.translatable("text.autoclicker-rinf.targetentitymode"), 0xFFFFFF);
    WText attackCooldownModeT = new WText(Text.translatable("text.autoclicker-rinf.attackcooldowncode"), 0xFFFFFF);
    WSlider clickDelayS = new WSlider(0, 40, Axis.HORIZONTAL);
    WToggleButton targetEntityModeTB = new WToggleButton();
    WToggleButton attackCooldownModeTB = new WToggleButton();
    WButton setSettingsB = new WButton(Text.translatable("gui.done"));
    public ACFMenuGui() {
        setRootPanel(rootP);
        rootP.setInsets(Insets.ROOT_PANEL);
        this.setFullscreen(true);
        rootP.setSize(ACFMenuScreen.screenWidth, ACFMenuScreen.screenHeight);

        rootP.add(settingsMenuL, 0, 0, 125, 1);
        rootP.add(clickDelayT, 0, 25, 125, 10);
        rootP.add(clickDelayS, 0, 35, 125, 10);
        rootP.add(new WText(Text.literal("- - - - - - - - - - - - -")), 0, 45, 130, 10);
        rootP.add(targetEntityModeT, 0, 55, 125, 10);
        rootP.add(targetEntityModeTB, 0, 65);
        rootP.add(new WText(Text.literal("- - - - - - - - - - - - -")), 0, 81, 130, 10);
        rootP.add(attackCooldownModeT, 0, 91, 200, 10);
        rootP.add(attackCooldownModeTB, 0, 101, 125, 10);
        rootP.add(new WText(Text.literal("- - - - - - - - - - - - -")), 0, 117, 130, 10);
        rootP.add(setSettingsB, 0, rootP.getHeight() - 36, 50, 10);

        rootP.validate(this);

        rootP.setSize(ACFMenuScreen.screenWidth, ACFMenuScreen.screenHeight);
        setSettingsB.setLocation(7, rootP.getHeight() - 29);

        ACFMenuGuiLogic();
    }
    public void ACFMenuGuiLogic() {
        clickDelayS.setValue(ClickController.clickDelayInt);
        targetEntityModeTB.setToggle(ClickController.targetEntityMode);
        attackCooldownModeTB.setToggle(ClickController.attackCooldownMode);
        setSettingsB.setOnClick(() -> {
            ClickController.clickDelayInt = clickDelayS.getValue();
            ClickController.targetEntityMode = targetEntityModeTB.getToggle();
            ClickController.attackCooldownMode = attackCooldownModeTB.getToggle();

            config.set(
                    ClickController.clickerActive,
                    ClickController.clickDelayInt,
                    ClickController.targetEntityMode,
                    ClickController.attackCooldownMode
            );

            assert MinecraftClient.getInstance().currentScreen != null;
            MinecraftClient.getInstance().currentScreen.close();
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> clickDelayT.setText(Text.translatable("text.autoclicker-rinf.clickdelay", clickDelayS.getValue())));
    }
}
