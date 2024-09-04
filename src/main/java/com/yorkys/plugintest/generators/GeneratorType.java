package com.yorkys.plugintest.generators;

import org.bukkit.Material;

public enum GeneratorType {
    IRON(Material.IRON_BLOCK, Material.IRON_INGOT, 2, -1, 12),
    GOLD(Material.GOLD_BLOCK, Material.GOLD_INGOT, 3, -1, 4),
    DIAMOND(Material.DIAMOND_BLOCK, Material.DIAMOND, 5, 30, 2);
    private final Material blockMaterial;
    private final Material itemMaterial;
    private final int spawnTime;
    private final int upgradeTime;
    private final int maxStackSize;

    GeneratorType(Material blockMaterial, Material itemMaterial, int spawnTime, int upgradeTime, int maxStackSize) {
        this.blockMaterial = blockMaterial;
        this.itemMaterial = itemMaterial;
        this.spawnTime = spawnTime;
        this.upgradeTime = upgradeTime;
        this.maxStackSize = maxStackSize;
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }

    public Material getItemMaterial() {
        return itemMaterial;
    }

    public int getSpawnTime() {
        return spawnTime;
    }

    public int getUpgradeTime() {
        return upgradeTime;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }
}