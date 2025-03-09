/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/
package de.fabi.backpackplugin.commands;


import de.fabi.backpackplugin.logic.BackpackManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackpackCommand implements CommandExecutor {
    private final BackpackManager backpackManager;

    public BackpackCommand(BackpackManager backpackManager) {
        this.backpackManager = backpackManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nur Spieler können diesen Befehl verwenden!");
            return true;
        }

        Player player = (Player) sender;


        if (args.length == 0) {
            player.openInventory(backpackManager.getBackpack(player));
            player.sendMessage(ChatColor.GREEN + "Dein Backpack wurde geöffnet!");
            return true;
        }


        if (args.length == 1) {
            if (!player.hasPermission("backpack.view.others")) {
                player.sendMessage(ChatColor.RED + "Du hast keine Berechtigung, die Backpacks anderer Spieler anzusehen!");
                return true;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage(ChatColor.RED + "Spieler " + args[0] + " ist nicht online oder existiert nicht!");
                return true;
            }


            player.openInventory(backpackManager.getBackpack(targetPlayer));
            player.sendMessage(ChatColor.GREEN + "Du hast das Backpack von " + targetPlayer.getName() + " geöffnet.");
            return true;
        }


        player.sendMessage(ChatColor.RED + "Benutzung: /backpack [Spielername]");
        return true;
    }
}
