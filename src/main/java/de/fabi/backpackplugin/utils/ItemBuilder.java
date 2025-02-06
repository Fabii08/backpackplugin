package de.fabi.backpackplugin.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;


public class ItemBuilder {
    private final ItemStack itemStack;
    private ItemMeta itemMeta;
    private final MiniMessage miniMessage = MiniMessage
            .builder()
            .postProcessor(proc -> proc.decoration(TextDecoration.ITALIC, false))
            .build();

    public ItemBuilder() {
        this.itemStack = new ItemStack(Material.AIR);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this(new ItemStack(material, 1));
    }

    public ItemBuilder(Material material, int amount) {
        this(new ItemStack(material, amount));
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder name(String name) {
        itemMeta.displayName(miniMessage.deserialize(name));
        return this;
    }

    public ItemBuilder name(Component component) {
        itemMeta.displayName(component);
        return this;
    }

    public ItemBuilder lore(ItemStack toCopy, String... lore) {
        var convertedToComponent = Arrays.stream(lore)
                .map(miniMessage::deserialize)
                .toList();

        var list = toCopy.getItemMeta().lore() != null ? toCopy.getItemMeta().lore() : new ArrayList<Component>();

        list.add(Component.empty());
        list.addAll(convertedToComponent);

        itemMeta.lore(list);
        return this;
    }

    public ItemBuilder lore(String... lore) {
        var convertedToComponent = Arrays.stream(lore)
                .map(miniMessage::deserialize)
                .toList();
        itemMeta.lore(convertedToComponent);
        return this;
    }

    public ItemBuilder lore(Component... lore) {
        if (lore == null) {
            return this;
        }

        itemMeta.lore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder customModelData(int customModelData) {
        itemMeta.setCustomModelData(customModelData);
        return this;
    }

    public ItemBuilder itemFlags(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder attributeModifier(Attribute attribute, AttributeModifier modifier) {
        itemMeta.addAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment) {
        itemMeta.addEnchant(enchantment, 1, true);
        return this;
    }

    public ItemBuilder skullOwner(OfflinePlayer player) {
        var meta = (SkullMeta) itemMeta;
        meta.setOwningPlayer(player);
        return this;
    }

    public ItemBuilder leatherColor(Color color) {
        if (color == null) {
            return this;
        }

        var meta = (LeatherArmorMeta) itemMeta;
        meta.setColor(color);
        return this;
    }

    public ItemBuilder damage(short damage) {
        itemStack.setDurability(damage);
        return this;
    }

    public <T, Z> ItemBuilder withDataContainer(NamespacedKey key, PersistentDataType<T, Z> dataType, Z value) {
        itemMeta.getPersistentDataContainer().set(key, dataType, value);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
