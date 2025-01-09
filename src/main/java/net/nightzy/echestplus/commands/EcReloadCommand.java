package net.nightzy.echestplus.commands;

import net.nightzy.echestplus.EChestPlusPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EcReloadCommand implements CommandExecutor {

    private final EChestPlusPlugin plugin;

    public EcReloadCommand(EChestPlusPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("echestplus.reload")) {
            sender.sendMessage(plugin.getLangManager().getLangController().getMessage("no_permission"));
            return true;
        }

        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        plugin.getLangManager().loadLangConfig();

        sender.sendMessage(plugin.getLangManager().getLangController().getMessage("config_reloaded"));
        return true;
    }
}
