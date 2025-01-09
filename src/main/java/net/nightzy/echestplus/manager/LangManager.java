package net.nightzy.echestplus.managers;

import net.nightzy.echestplus.controllers.LangController;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class LangManager {

    private final JavaPlugin plugin;
    private LangController langController;

    public LangManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadLangConfig() {
        String langType = plugin.getConfig().getString("langType", "en_US");
        langController = new LangController(langType, plugin.getDataFolder());
        plugin.getLogger().info("Language configuration reloaded. Current language: " + langType);
    }

    public void createLangFolder() {
        File langFolder = new File(plugin.getDataFolder(), "lang");
        if (!langFolder.exists()) {
            if (langFolder.mkdirs()) {
                plugin.getLogger().info("Created 'lang' folder.");
            } else {
                plugin.getLogger().warning("Failed to create 'lang' folder.");
                return;
            }
        }

        copyResourceToFile("lang/en_US.yml", new File(langFolder, "en_US.yml"));
        copyResourceToFile("lang/pl_PL.yml", new File(langFolder, "pl_PL.yml"));
    }

    private void copyResourceToFile(String resourcePath, File outputFile) {
        if (!outputFile.exists()) {
            try {
                plugin.saveResource(resourcePath, false);
                plugin.getLogger().info("Copied default language file: " + outputFile.getName());
            } catch (Exception e) {
                plugin.getLogger().warning("Failed to copy language file: " + resourcePath);
                e.printStackTrace();
            }
        }
    }

    public LangController getLangController() {
        return langController;
    }
}
