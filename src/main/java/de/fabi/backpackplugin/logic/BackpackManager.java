/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/
package de.fabi.backpackplugin.logic;

import de.fabi.backpackplugin.Backpackplugin;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class BackpackManager {

    private final HashMap<UUID, Inventory> backpacks = new HashMap<>();
    private final Backpackplugin plugin;

    public BackpackManager(Backpackplugin plugin) {
        this.plugin = plugin;
        createBackpackDirectory();
        loadBackpacks();
    }

    private void createBackpackDirectory() {
        File backpacksDir = new File(plugin.getDataFolder(), "backpacks");
        if (!backpacksDir.exists()) {
            boolean created = backpacksDir.mkdirs();
            if (created) {
                plugin.getLogger().info("Backpack-Ordner wurde erstellt.");
            } else {
                plugin.getLogger().warning("Fehler beim Erstellen des Backpack-Ordners.");
            }
        }
    }

    public Inventory getBackpack(Player player) {
        UUID playerUUID = player.getUniqueId();
        String playerName = player.getName();


        if (backpacks.containsKey(playerUUID)) {
            return backpacks.get(playerUUID);
        }


        Inventory inventory = Bukkit.createInventory(player, 56, playerName + "'s Backpack");
        File backpackFile = getBackpackFile(player);

        if (backpackFile.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(backpackFile);
            for (int i = 0; i < inventory.getSize(); i++) {
                if (config.contains("backpack." + i)) {
                    inventory.setItem(i, config.getItemStack("backpack." + i));
                }
            }
        }

        backpacks.put(playerUUID, inventory);
        return inventory;
    }

    public void saveBackpack(Player player) {
        Inventory backpack = backpacks.get(player.getUniqueId());
        if (backpack == null) return;

        FileConfiguration config = YamlConfiguration.loadConfiguration(getBackpackFile(player));

        for (int i = 0; i < backpack.getSize(); i++) {
            ItemStack item = backpack.getItem(i);
            if (item != null) {
                config.set("backpack." + i, item);
            } else {
                config.set("backpack." + i, null);
            }
        }

        try {
            config.save(getBackpackFile(player));
        } catch (IOException e) {
            plugin.getLogger().warning("Fehler beim Speichern des Backpacks für " + player.getName() + ": " + e.getMessage());
        }
    }

    private File getBackpackFile(Player player) {
        return new File(plugin.getDataFolder(), "backpacks/" + player.getUniqueId() + ".yml");
    }

    public void loadBackpacks() {
        File backpacksDir = new File(plugin.getDataFolder(), "backpacks");
        if (!backpacksDir.exists()) {
            backpacksDir.mkdirs();
        }

        for (File file : backpacksDir.listFiles()) {
            if (file.getName().endsWith(".yml")) {
                UUID playerUUID = UUID.fromString(file.getName().replace(".yml", ""));
                String playerName = getPlayerNameFromUUID(playerUUID);

                if (playerName == null) playerName = "Unknown Player";

                Inventory inventory = Bukkit.createInventory(null, 27, playerName + "'s Backpack");

                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                for (int i = 0; i < inventory.getSize(); i++) {
                    if (config.contains("backpack." + i)) {
                        inventory.setItem(i, config.getItemStack("backpack." + i));
                    }
                }
                backpacks.put(playerUUID, inventory);
            }
        }
    }

    private String getPlayerNameFromUUID(UUID uuid) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        return offlinePlayer.getName();
    }
}
