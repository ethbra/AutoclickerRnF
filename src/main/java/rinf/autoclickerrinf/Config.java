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
    private boolean clickerActive;
    private int clickDelayInt;
    private boolean targetEntityMode;
    private boolean attackCooldownMode;

    public Config() {
    }
    public void set(boolean clickerActive, int clickDelayInt, boolean targetEntityMode, boolean attackCooldownMode) {
        this.clickerActive = clickerActive;
        this.clickDelayInt = clickDelayInt;
        this.targetEntityMode = targetEntityMode;
        this.attackCooldownMode = attackCooldownMode;
        try (FileWriter writer = new FileWriter(CONFIG_FILE.toFile())) {
            writer.write("clickerActive = " + this.clickerActive + "\n");
            writer.write("clickDelayInt = " + this.clickDelayInt + "\n");
            writer.write("targetEntityMode = " + this.targetEntityMode + "\n");
            writer.write("attackCooldownMode = " + this.attackCooldownMode + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load() {
        if (!Files.exists(CONFIG_FILE)) create();
        try {
            properties.load(new FileInputStream(CONFIG_FILE.toFile()));
            this.clickerActive = Boolean.parseBoolean(properties.getProperty("clickerActive"));
            this.clickDelayInt = Integer.parseInt(properties.getProperty("clickDelayInt"));
            this.targetEntityMode = Boolean.parseBoolean(properties.getProperty("targetEntityMode"));
            this.attackCooldownMode = Boolean.parseBoolean(properties.getProperty("attackCooldownMode"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClickController.clickerActive = this.clickerActive;
        ClickController.clickDelayInt = this.clickDelayInt;
        ClickController.targetEntityMode = this.targetEntityMode;
        ClickController.attackCooldownMode = this.attackCooldownMode;
    }
    private void create() {
        try {
            Files.createDirectories(CONFIG_DIRECTORY);
            Files.createFile(CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        set(false, 0, false, false);
    }
}
