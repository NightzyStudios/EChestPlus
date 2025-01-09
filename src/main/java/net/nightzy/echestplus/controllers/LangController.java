package net.nightzy.echestplus.controllers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LangController {
    private final FileConfiguration langConfig;

    public LangController(String langType, File pluginFolder) {
        File langFile = new File(pluginFolder, "lang/" + langType + ".yml");

        if (!langFile.exists()) {
            langFile = new File(pluginFolder, "lang/en_US.yml");
        }

        langConfig = YamlConfiguration.loadConfiguration(langFile);
    }

    public String getMessage(String key) {
        return langConfig.getString(key, "§cTranslation missing for key: " + key);
    }
}
