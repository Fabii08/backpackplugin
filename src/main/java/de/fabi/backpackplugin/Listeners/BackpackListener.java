package de.fabi.backpackplugin.Listeners;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories  
All rights reserved.  

Unauthorized copying, modification, or distribution of this file,  
via any medium, is strictly prohibited.  

DO NOT DISTRIBUTE.
*/

import de.fabi.backpackplugin.logic.BackpackManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class BackpackListener implements Listener {

    private final BackpackManager backpackManager;


    public BackpackListener(BackpackManager backpackManager) {
        this.backpackManager = backpackManager;
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        backpackManager.saveBackpack(event.getPlayer());
    }
}
