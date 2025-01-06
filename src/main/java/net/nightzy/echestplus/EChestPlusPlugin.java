package net.nightzy.echestplus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.nightzy.echestplus.commands.AdminECCommand;
import net.nightzy.echestplus.commands.ECCommand;
import net.nightzy.echestplus.commands.NightzyECCommand;

public class EChestPlusPlugin extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Załaduj konfigurację
        saveDefaultConfig();
        config = getConfig();

        // Rejestracja komend
        this.getCommand("ec").setExecutor(new ECCommand(this));
        this.getCommand("nightzyec").setExecutor(new NightzyECCommand(this));
        this.getCommand("adminec").setExecutor(new AdminECCommand(this));

        // Logowanie do konsoli, że plugin został załadowany
        getLogger().info("EChestPlus has been enabled!");

        // Odczyt z konfiguracji
        String databaseType = config.getString("basteType", "MYSQL");
        String databaseUri = config.getString("databaseConnectionUri");
        String enderChestName = config.getString("enderChestName", "<gradient:#086BFB:#4385FF>Twój EnderChest</gradient>");
        int enderChestSize = config.getInt("enderChestSize", 27);

        getLogger().info("Database Type: " + databaseType);
        getLogger().info("EnderChest GUI Name: " + enderChestName);
        getLogger().info("Default EnderChest Size: " + enderChestSize);
        getLogger().info("Database Connection URI: " + databaseUri);
    }

    @Override
    public void onDisable() {
        getLogger().info("EChestPlus has been disabled.");
    }

    public FileConfiguration getPluginConfig() {
        return config;
    }
}
