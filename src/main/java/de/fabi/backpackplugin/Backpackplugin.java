/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/
package de.fabi.backpackplugin;

import de.fabi.backpackplugin.Listeners.BackpackListener;
import de.fabi.backpackplugin.Listeners.Openbackpack;
import de.fabi.backpackplugin.commands.BackpackCommand;
import de.fabi.backpackplugin.logic.BackpackManager;
import de.fabi.backpackplugin.recipies.Backpack1recipie;
import de.fabi.backpackplugin.utils.CustomHead;
import de.fabi.backpackplugin.utils.ItemBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Backpackplugin extends JavaPlugin {


    private BackpackManager backpackManager;


    public static ItemStack backpackT1Item;

    @Override
    public void onEnable() {

        createBackpackFolder();


        this.backpackManager = new BackpackManager(this);

        backpackT1Item = BackpackT1.createBackpackItem();


        if (getCommand("backpack") != null) {
            getCommand("backpack").setExecutor(new BackpackCommand(this.backpackManager));
        } else {
            getLogger().warning("Der Befehl 'backpack' konnte nicht registriert werden.");
        }

        Backpack1recipie.addRecipe(this);


        getServer().getPluginManager().registerEvents(new BackpackListener(backpackManager), this);
        getServer().getPluginManager().registerEvents(new Openbackpack(backpackManager), this);


        backpackManager.loadBackpacks();

        getLogger().info("Backpack Plugin aktiviert!");
    }

    @Override
    public void onDisable() {

        for (org.bukkit.entity.Player player : getServer().getOnlinePlayers()) {
            backpackManager.saveBackpack(player);
        }


        getLogger().info("Backpack Plugin deaktiviert!");
    }


    private void createBackpackFolder() {
        File backpacksDir = new File(getDataFolder(), "backpacks");
        if (!backpacksDir.exists()) {
            boolean dirCreated = backpacksDir.mkdirs();
            if (dirCreated) {
                getLogger().info("Ordner 'backpacks' wurde erstellt.");
            } else {
                getLogger().warning("Fehler beim Erstellen des Ordners 'backpacks'. Überprüfe die Berechtigungen!");
            }
        }
    }


    public static class BackpackT1 {


        public static ItemStack createBackpackItem() {
            ItemStack backpack1 = CustomHead.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM1MWU1MDU5ODk4MzhlMjcyODdlN2FmYmM3Zjk3ZTc5NmNhYjVmMzU5OGE3NjE2MGMxMzFjOTQwZDBjNSJ9fX0=");
            ItemMeta meta = backpack1.getItemMeta();
            meta.displayName(MiniMessage.miniMessage().deserialize("<color:#964b00>Backpack</color>"));
            List<String> lore = new ArrayList<>();
            lore.add("§7Backpack Basic Tier");
            meta.setLore(lore);
            meta.setCustomModelData(6);
            backpack1.setItemMeta(meta);


            return backpack1;
        }

    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }
}
