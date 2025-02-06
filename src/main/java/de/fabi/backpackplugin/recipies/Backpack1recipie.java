package de.fabi.backpackplugin.recipies;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories  
All rights reserved.  

Unauthorized copying, modification, or distribution of this file,  
via any medium, is strictly prohibited.  

DO NOT DISTRIBUTE.
*/

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
