package com.yorkys.plugintest.utils;


import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.List;

public class GlowEnchant extends EnchantmentWrapper {

    private static final Enchantment glow;

    static {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        glow = new GlowEnchant(255);
        if(!List.of(Enchantment.values()).contains(glow)) Enchantment.registerEnchantment(glow);
    }

    public GlowEnchant(int id) {
        super(id);
    }

    public static ItemStack addGlow(ItemStack item) {
        item.addEnchantment(glow, 1);
        return item;
    }
    public static ItemMeta addGlow(ItemMeta meta) {
        meta.addEnchant(glow, 1, true);
        return meta;
    }

    public static ItemStack removeGlow(ItemStack item) {
        item.removeEnchantment(glow);
        return item;
    }
    public static ItemMeta removeGlow(ItemMeta meta) {
        meta.removeEnchant(glow);
        return meta;
    }

    public static boolean isGlowing(ItemStack item) {
        return item.containsEnchantment(glow);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public String getName() {
        return "Glow";
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}