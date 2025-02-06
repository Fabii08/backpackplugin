package de.fabi.backpackplugin.utils;



import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class CustomHead {

    public static ItemStack getSkull(String base64Texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        PlayerProfile profile = Bukkit.createProfile(new UUID(0l, 0l), "");
        profile.setProperty(new ProfileProperty("textures", base64Texture));

        meta.setPlayerProfile(profile);
        head.setItemMeta(meta);

        return head;

    }

}
