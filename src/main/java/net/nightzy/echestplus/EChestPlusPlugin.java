package net.nightzy.echestplus;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

import net.nightzy.echestplus.managers.LangManager;
import net.nightzy.echestplus.controllers.LangController;
import net.nightzy.echestplus.commands.EcReloadCommand;

public final class EChestPlusPlugin extends JavaPlugin {

    private static EChestPlusPlugin main;
    private LangManager langManager;

    @Override
    public void onEnable() {
        main = this;
        this.loadPlugin();
        saveDefaultConfig();

        langManager = new LangManager(this);
        langManager.createLangFolder();
        langManager.loadLangConfig();

        getCommand("ecreload").setExecutor(new net.nightzy.echestplus.commands.EcReloadCommand(this));
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
        getLogger().info("EChestPlus has been disabled!");
    }

    public static EChestPlusPlugin getMain() {
        return main;
    }

    public LangManager getLangManager() {
        return langManager;
    }
}