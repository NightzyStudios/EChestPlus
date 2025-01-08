package net.nightzy.echestplus.commands;

import net.nightzy.echestplus.EChestPlusPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

import java.util.logging.Level;

public class EcReloadCommand implements CommandExecutor {

    private final EChestPlusPlugin plugin;

    public EcReloadCommand(EChestPlusPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ecreload")) {
            if (!sender.hasPermission("echestplus.reload")) {
                // Komunikat o braku uprawnień zależny od języka
                sender.sendMessage(plugin.getLangMessage("no_permission"));
                return true;
            }

            // Reload config
            plugin.reloadConfig();
            // Komunikat o przeładowaniu konfiguracji zależny od języka
            sender.sendMessage(plugin.getLangMessage("config_reloaded"));

            // Log reload action
            plugin.getLogger().log(Level.INFO, "Configuration reloaded by " + sender.getName());
            return true;
        }
        return false;
    }
}
