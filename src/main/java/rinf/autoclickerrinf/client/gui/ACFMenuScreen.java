package rinf.autoclickerrinf.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import rinf.autoclickerrinf.client.AutoclickerRinfClient;
import rinf.autoclickerrinf.client.ClickController;

@Environment(EnvType.CLIENT)
public class ACFMenuScreen extends Screen {

    public TextWidget lTitleTextWidget;
    public CheckboxWidget lActiveCheckboxWidget;
    public ClickDelayTextFieldWidget lClickDelayTextFieldWidget;
    public ButtonWidget lClickDelayButtonWidget;
    public ButtonWidget lClickDelaySaveButtonWidget;
    public ButtonWidget lHoldModeButtonWidget;
    public ButtonWidget lTargetEntityModeButtonWidget;
    public ButtonWidget lCooldownAttackModeButtonWidget;

    public TextWidget rTitleTextWidget;
    public CheckboxWidget rActiveCheckboxWidget;
    public ClickDelayTextFieldWidget rClickDelayTextFieldWidget;
    public ButtonWidget rClickDelayButtonWidget;
    public ButtonWidget rClickDelaySaveButtonWidget;
    public ButtonWidget rHoldModeButtonWidget;
    public ButtonWidget rTargetEntityModeButtonWidget;

    public ButtonWidget doneButtonWidget;

    public ACFMenuScreen() {
        super(Text.literal("AutoClicker Settings"));
    }

    @Override
    protected void init() {
        lTitleTextWidget = new TextWidget(10, 7, 110, 20, Text.translatable("autoclicker-rinf.leftmousebutton"), textRenderer);
        lTitleTextWidget.setTextColor(0xffff00);
        rTitleTextWidget = new TextWidget(148, 7, 110, 20, Text.translatable("autoclicker-rinf.rightmousebutton"), textRenderer);
        rTitleTextWidget.setTextColor(0xffff00);

        lActiveCheckboxWidget = new CheckboxWidget(118, 6, 20, 20, Text.empty(), ClickController.lClickerActive);
        rActiveCheckboxWidget = new CheckboxWidget(258, 6, 20, 20, Text.empty(), ClickController.rClickerActive);

        lClickDelayTextFieldWidget = new ClickDelayTextFieldWidget(textRenderer, 10, 30, 110, 20, Text.literal("0"));
        lClickDelayTextFieldWidget.visible = false;
        rClickDelayTextFieldWidget = new ClickDelayTextFieldWidget(textRenderer, 148, 30, 110, 20, Text.literal("0"));
        rClickDelayTextFieldWidget.visible = false;

        lClickDelaySaveButtonWidget = ButtonWidget.builder(Text.literal("OK"), button -> {
            if (!lClickDelayTextFieldWidget.getText().equals("")) ClickController.lClickDelayInt = convertStringToInt(lClickDelayTextFieldWidget.getText());
            lClickDelayButtonWidget.setMessage(Text.translatable("autoclicker-rinf.clickdelay", ClickController.lClickDelayInt));
            lClickDelayTextFieldWidget.setText("");

            lClickDelayTextFieldWidget.visible = false;
            lClickDelayButtonWidget.visible = true;
            button.setFocused(false);
            button.visible = false;
        }).dimensions(120, 30, 20, 20).build();
        lClickDelaySaveButtonWidget.visible = false;
        rClickDelaySaveButtonWidget = ButtonWidget.builder(Text.literal("OK"), button -> {
            if (!rClickDelayTextFieldWidget.getText().equals("")) ClickController.rClickDelayInt = convertStringToInt(rClickDelayTextFieldWidget.getText());
            rClickDelayButtonWidget.setMessage(Text.translatable("autoclicker-rinf.clickdelay", ClickController.rClickDelayInt));
            rClickDelayTextFieldWidget.setText("");

            rClickDelayTextFieldWidget.visible = false;
            rClickDelayButtonWidget.visible = true;
            button.setFocused(false);
            button.visible = false;
        }).dimensions(260, 30, 20, 20).build();
        rClickDelaySaveButtonWidget.visible = false;

        lClickDelayButtonWidget = ButtonWidget.builder(Text.translatable("autoclicker-rinf.clickdelay", ClickController.lClickDelayInt), button -> {
            lClickDelayTextFieldWidget.visible = true;
            lClickDelaySaveButtonWidget.visible = true;
            button.setFocused(false);
            button.visible = false;
        }).dimensions(10, 30, 130, 20).build();
        rClickDelayButtonWidget = ButtonWidget.builder(Text.translatable("autoclicker-rinf.clickdelay", ClickController.rClickDelayInt), button -> {
            rClickDelayTextFieldWidget.visible = true;
            rClickDelaySaveButtonWidget.visible = true;
            button.setFocused(false);
            button.visible = false;
        }).dimensions(148, 30, 130, 20).build();

        lHoldModeButtonWidget = ButtonWidget.builder(Text.translatable("autoclicker-rinf.holdmode", ClickController.lHoldMode), button -> {
            ClickController.lHoldMode = !ClickController.lHoldMode;
            lHoldModeButtonWidget.setMessage(Text.translatable("autoclicker-rinf.holdmode", ClickController.lHoldMode));
        }).dimensions(10, 54, 130, 20).build();
        rHoldModeButtonWidget = ButtonWidget.builder(Text.translatable("autoclicker-rinf.holdmode", ClickController.rHoldMode), button -> {
            ClickController.rHoldMode = !ClickController.rHoldMode;
            rHoldModeButtonWidget.setMessage(Text.translatable("autoclicker-rinf.holdmode", ClickController.rHoldMode));
        }).dimensions(148, 54, 130, 20).build();

        lTargetEntityModeButtonWidget = ButtonWidget.builder(Text.translatable("autoclicker-rinf.targetentitymode", ClickController.lTargetEntityMode), button -> {
            ClickController.lTargetEntityMode = !ClickController.lTargetEntityMode;
            lTargetEntityModeButtonWidget.setMessage(Text.translatable("autoclicker-rinf.targetentitymode", ClickController.lTargetEntityMode));
        }).dimensions(10, 78, 130, 20).build();
        rTargetEntityModeButtonWidget = ButtonWidget.builder(Text.translatable("autoclicker-rinf.targetentitymode", ClickController.rTargetEntityMode), button -> {
            ClickController.rTargetEntityMode = !ClickController.rTargetEntityMode;
            rTargetEntityModeButtonWidget.setMessage(Text.translatable("autoclicker-rinf.targetentitymode", ClickController.rTargetEntityMode));
        }).dimensions(148, 78, 130, 20).build();

        lCooldownAttackModeButtonWidget = ButtonWidget.builder(Text.translatable("autoclicker-rinf.attackcooldownmode", ClickController.lCooldownAttackMode), button -> {
            ClickController.lCooldownAttackMode = !ClickController.lCooldownAttackMode;
            lCooldownAttackModeButtonWidget.setMessage(Text.translatable("autoclicker-rinf.attackcooldownmode", ClickController.lCooldownAttackMode));
        }).dimensions(10, 102, 130, 20).build();

        doneButtonWidget = ButtonWidget.builder(Text.translatable("gui.done"), button -> this.close() ).dimensions(10, height - 30, 50, 20).build();


        if (AutoclickerRinfClient.isME && !MinecraftClient.getInstance().isIntegratedServerRunning()) {
            lTargetEntityModeButtonWidget.active = false;
            rTargetEntityModeButtonWidget.active = false;

            ClickController.lTargetEntityMode = false;
            ClickController.rTargetEntityMode = false;
        }


        addDrawableChild(lTitleTextWidget);
        addDrawableChild(lActiveCheckboxWidget);
        addDrawableChild(lClickDelayTextFieldWidget);
        addDrawableChild(lClickDelaySaveButtonWidget);
        addDrawableChild(lClickDelayButtonWidget);
        addDrawableChild(lHoldModeButtonWidget);
        addDrawableChild(lTargetEntityModeButtonWidget);
        addDrawableChild(lCooldownAttackModeButtonWidget);

        addDrawableChild(rTitleTextWidget);
        addDrawableChild(rActiveCheckboxWidget);
        addDrawableChild(rClickDelayTextFieldWidget);
        addDrawableChild(rClickDelaySaveButtonWidget);
        addDrawableChild(rClickDelayButtonWidget);
        addDrawableChild(rHoldModeButtonWidget);
        addDrawableChild(rTargetEntityModeButtonWidget);

        addDrawableChild(doneButtonWidget);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        this.renderBackground(drawContext);
        super.render(drawContext, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        ClickController.lClickerActive = lActiveCheckboxWidget.isChecked();
        ClickController.rClickerActive = rActiveCheckboxWidget.isChecked();
        ClickController.saveValues();
        super.close();
    }

    private int convertStringToInt(String str) {
        int value = 0;
        try {
            value = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("Convert String To Int Exception: " + e);
        }
        return value;
    }
}
