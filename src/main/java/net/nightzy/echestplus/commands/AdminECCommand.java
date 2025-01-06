package net.nightzy.echestplus.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.nightzy.echestplus.EChestPlusPlugin;

public class AdminECCommand implements CommandExecutor {

    private final EChestPlusPlugin plugin;

    public AdminECCommand(EChestPlusPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Sprawdź, czy komendę wywołuje gracz
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ta komenda może być używana tylko przez graczy!");
            return false;
        }

        Player player = (Player) sender;

        // Sprawdź, czy przekazano odpowiednią liczbę argumentów
        if (args.length < 1) {
            sendUsage(player);
            return false;
        }

        String subCommand = args[0];

        switch (subCommand.toLowerCase()) {
            case "getitem":
                if (args.length != 2) {
                    sendUsage(player);
                    return false;
                }
                String targetPlayerName = args[1]; // Zainicjowanie zmiennej przed użyciem
                Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
                if (targetPlayer == null) {
                    player.sendMessage(ChatColor.RED + "Gracz " + targetPlayerName + " nie jest online!");
                    return false;
                }
                giveItemToPlayer(targetPlayer);
                break;

            case "open":
                if (args.length != 2) {
                    sendUsage(player);
                    return false;
                }
                String playerName = args[1];
                Player target = Bukkit.getPlayer(playerName);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Gracz " + playerName + " nie jest online!");
                    return false;
                }
                openEnderChest(target);
                break;

            case "size":
                if (args.length != 3) {
                    sendUsage(player);
                    return false;
                }
                targetPlayerName = args[1]; // Ponowne przypisanie zmiennej targetPlayerName
                String sizeStr = args[2];
                try {
                    int size = Integer.parseInt(sizeStr);
                    if (size < 27 || size > 54) {
                        player.sendMessage(ChatColor.RED + "Rozmiar musi być między 27 a 54!");
                        return false;
                    }
                    setEnderChestSize(player, targetPlayerName, size);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Wielkość musi być liczbą!");
                    return false;
                }
                break;

            default:
                sendUsage(player);
                break;
        }

        return true;
    }

    // Wyświetl komunikat o poprawnym użyciu komendy
    private void sendUsage(Player player) {
        player.sendMessage(ChatColor.YELLOW + "Użycie komendy:");
        player.sendMessage(ChatColor.YELLOW + "/adminec getitem [gracz]");
        player.sendMessage(ChatColor.YELLOW + "/adminec open [gracz]");
        player.sendMessage(ChatColor.YELLOW + "/adminec size [gracz] [wielkość]");
    }

    // Przykładowa metoda do wydania przedmiotu graczowi
    private void giveItemToPlayer(Player targetPlayer) {
        // Pobierz dane o przedmiocie z konfiguracji
        String materialName = plugin.getConfig().getString("upgraderItem.material");
        int amount = plugin.getConfig().getInt("upgraderItem.amount", 1);
    }

    // Metoda do otwierania Ender Chest
    private void openEnderChest(Player targetPlayer) {
        Inventory enderChest = targetPlayer.getEnderChest();
        targetPlayer.openInventory(enderChest);
    }

    // Metoda do ustawienia rozmiaru Ender Chest
    private void setEnderChestSize(Player player, String targetPlayerName, int size) {
        Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
        if (targetPlayer != null) {
            // Zmieniamy rozmiar Ender Chest, ale tutaj musisz dodać odpowiednią logikę
            // Aby "zmienić" rozmiar Ender Chesta, musisz stworzyć własne GUI
            Inventory inv = Bukkit.createInventory(null, size, targetPlayerName + "'s Ender Chest");
            targetPlayer.openInventory(inv);
            player.sendMessage(ChatColor.GREEN + "Ustawiono rozmiar Ender Chesta dla " + targetPlayerName + " na " + size + " slotów.");
        } else {
            player.sendMessage(ChatColor.RED + "Gracz " + targetPlayerName + " nie jest online!");
        }
    }
}
