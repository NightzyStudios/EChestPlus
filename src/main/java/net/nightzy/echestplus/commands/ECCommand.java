package net.nightzy.echestplus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.nightzy.echestplus.EChestPlusPlugin;

public class ECCommand implements CommandExecutor {

    private final EChestPlusPlugin plugin;
    private final FileConfiguration config;

    public ECCommand(EChestPlusPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getPluginConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda może być używana tylko przez graczy.");
            return false;
        }

        Player player = (Player) sender;

        // Zdobądź nazwę EnderChest z konfiguracji
        String enderChestName = config.getString("enderChestName", "<gradient:#086BFB:#4385FF>Twój EnderChest</gradient>");
        int enderChestSize = config.getInt("enderChestSize", 27);

        // Otwórz EnderChest gracza
        player.openInventory(player.getEnderChest());

        return true;
    }
}
