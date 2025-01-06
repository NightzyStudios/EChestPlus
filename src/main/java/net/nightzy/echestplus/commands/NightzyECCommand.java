package net.nightzy.echestplus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.nightzy.echestplus.EChestPlusPlugin;

public class NightzyECCommand implements CommandExecutor {

    private final EChestPlusPlugin plugin;

    public NightzyECCommand(EChestPlusPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Tylko administratorzy mogą używać tej komendy
        if (!sender.hasPermission("echestplus.admin")) {
            sender.sendMessage("Nie masz uprawnień, aby użyć tej komendy.");
            return false;
        }

        if (args.length == 0) {
            plugin.reloadConfig(); // Reload the config
            sender.sendMessage("Plugin configuration reloaded!");
            return true;
        }

        return false;
    }
}
