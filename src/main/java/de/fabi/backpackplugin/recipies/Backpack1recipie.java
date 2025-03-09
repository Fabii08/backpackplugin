/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/
package de.fabi.backpackplugin.recipies;


import de.fabi.backpackplugin.Backpackplugin;
import de.fabi.backpackplugin.Backpackplugin.BackpackT1;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ItemStack;

public class Backpack1recipie {

    public static void addRecipe(Backpackplugin plugin) {

        ItemStack result = BackpackT1.createBackpackItem();
        NamespacedKey key = new NamespacedKey(plugin, "backpack_tier_1_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, result);


        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', Material.LEATHER);
        recipe.setIngredient('B', Material.CHEST);


        plugin.getServer().addRecipe(recipe);
    }
}
