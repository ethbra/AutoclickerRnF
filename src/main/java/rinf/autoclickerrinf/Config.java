package rinf.autoclickerrinf;

import net.minecraft.client.MinecraftClient;
import rinf.autoclickerrinf.client.ClickController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    public static final Path CONFIG_DIRECTORY = Paths.get(MinecraftClient.getInstance().runDirectory.getPath() + "/config");
    public static final Path CONFIG_FILE = Paths.get(CONFIG_DIRECTORY + "/autoclicker-rinf.json");
    public static Properties properties = new Properties();
    private boolean lClickerActive;
    private boolean rClickerActive;
    private int lClickDelayInt;
    private int rClickDelayInt;
    private boolean lHoldMode;
    private boolean rHoldMode;
    private boolean lTargetEntityMode;
    private boolean rTargetEntityMode;
    private boolean lCooldownAttackMode;

    public Config() {
    }
    public void set(boolean lClickerActive, boolean rClickerActive, int lClickDelayInt, int rClickDelayInt, boolean lHoldMode, boolean rHoldMode, boolean lTargetEntityMode, boolean rTargetEntityMode, boolean lCooldownAttackMode) {
        this.lClickerActive = lClickerActive;
        this.rClickerActive = rClickerActive;
        this.lClickDelayInt = lClickDelayInt;
        this.rClickDelayInt = rClickDelayInt;
        this.lHoldMode = lHoldMode;
        this.rHoldMode = rHoldMode;
        this.lTargetEntityMode = lTargetEntityMode;
        this.rTargetEntityMode = rTargetEntityMode;
        this.lCooldownAttackMode = lCooldownAttackMode;
        try (FileWriter writer = new FileWriter(CONFIG_FILE.toFile())) {
            writer.write("lClickerActive = " + this.lClickerActive + "\n");
            writer.write("rClickerActive = " + this.rClickerActive + "\n");
            writer.write("lClickDelayInt = " + this.lClickDelayInt + "\n");
            writer.write("rClickDelayInt = " + this.rClickDelayInt + "\n");
            writer.write("lHoldMode = " + this.lHoldMode + "\n");
            writer.write("rHoldMode = " + this.rHoldMode + "\n");
            writer.write("lTargetEntityMode = " + this.lTargetEntityMode + "\n");
            writer.write("rTargetEntityMode = " + this.rTargetEntityMode + "\n");
            writer.write("lAttackCooldownMode = " + this.lCooldownAttackMode + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load() {
        if (!Files.exists(CONFIG_FILE)) create();
        try {
            properties.load(new FileInputStream(CONFIG_FILE.toFile()));
            this.lClickerActive = Boolean.parseBoolean(properties.getProperty("lClickerActive"));
            this.rClickerActive = Boolean.parseBoolean(properties.getProperty("rClickerActive"));
            this.lClickDelayInt = Integer.parseInt(properties.getProperty("lClickDelayInt"));
            this.rClickDelayInt = Integer.parseInt(properties.getProperty("rClickDelayInt"));
            this.lHoldMode = Boolean.parseBoolean(properties.getProperty("lHoldMode"));
            this.rHoldMode = Boolean.parseBoolean(properties.getProperty("rHoldMode"));
            this.lTargetEntityMode = Boolean.parseBoolean(properties.getProperty("lTargetEntityMode"));
            this.rTargetEntityMode = Boolean.parseBoolean(properties.getProperty("rTargetEntityMode"));
            this.lCooldownAttackMode = Boolean.parseBoolean(properties.getProperty("lAttackCooldownMode"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClickController.lClickerActive = this.lClickerActive;
        ClickController.rClickerActive = this.rClickerActive;
        ClickController.lClickDelayInt = this.lClickDelayInt;
        ClickController.rClickDelayInt = this.rClickDelayInt;
        ClickController.lHoldMode = this.lHoldMode;
        ClickController.rHoldMode = this.rHoldMode;
        ClickController.lTargetEntityMode = this.lTargetEntityMode;
        ClickController.rTargetEntityMode = this.rTargetEntityMode;
        ClickController.lCooldownAttackMode = this.lCooldownAttackMode;
    }
    private void create() {
        try {
            Files.createDirectories(CONFIG_DIRECTORY);
            Files.createFile(CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        set(false, false, 0, 0, false, false, false, false, false);
    }
}
