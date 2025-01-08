package net.nightzy.echestplus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

import net.nightzy.echestplus.commands.EcReloadCommand;

public final class EChestPlusPlugin extends JavaPlugin {

    private static EChestPlusPlugin main;
    private FileConfiguration langConfig; // Deklaracja zmiennej do przechowywania pliku językowego

    @Override
    public void onEnable() {
        main = this;
        this.loadPlugin();
        saveDefaultConfig();
        loadLangConfig(); // Ładowanie pliku językowego
        getCommand("ecreload").setExecutor(new EcReloadCommand(this));
    }

    private void loadPlugin() {
        if (!main.getDescription().getAuthors().contains("Nightzy.net")) {
            getLogger().log(Level.WARNING, "Plugin disabled due to an error in the plugin.yml section");
            main.getPluginLoader().disablePlugin(this);
        }

        getLogger().log(Level.INFO, "EChestPlus has been enabled!");
    }

    @Override
    public void onDisable() {
    }

    // Metoda ładująca plik językowy
    private void loadLangConfig() {
        String lang = getConfig().getString("langType", "en_US");
        File langFile = new File(getDataFolder(), "lang/" + lang + ".yml");

        if (!langFile.exists()) {
            lang = "en_US"; // Domyślny język
            langFile = new File(getDataFolder(), "lang/" + lang + ".yml");
        }

        try {
            langConfig = YamlConfiguration.loadConfiguration(langFile);
        } catch (Exception e) {
            getLogger().warning("Could not load language file for: " + lang);
            langConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "lang/en_US.yml"));
        }
    }

    // Metoda do pobierania komunikatów z pliku językowego
    public String getLangMessage(String key) {
        return langConfig.getString(key, "§cTranslation missing for key: " + key);
    }

    public static EChestPlusPlugin getMain() {
        return main;
    }
}
