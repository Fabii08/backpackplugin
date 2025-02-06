package de.fabi.backpackplugin.Listeners;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories  
All rights reserved.  

Unauthorized copying, modification, or distribution of this file,  
via any medium, is strictly prohibited.  

DO NOT DISTRIBUTE.
*/

import de.fabi.backpackplugin.logic.BackpackManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Openbackpack implements Listener {

    private final BackpackManager backpackManager;

    public Openbackpack(BackpackManager backpackManager) {
        this.backpackManager = backpackManager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        if (ChatColor.stripColor(meta.getDisplayName()).equals("Backpack") && meta.hasCustomModelData() && meta.getCustomModelData() == 69) {

            if (event.getAction().toString().contains("RIGHT_CLICK")) {
                Player player = event.getPlayer();
                player.openInventory(backpackManager.getBackpack(player));
                player.sendMessage(ChatColor.GREEN + "Dein Backpack wurde geöffnet!");
            }
        }
    }
}
