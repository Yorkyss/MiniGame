package com.yorkys.plugintest.generators;

import com.yorkys.plugintest.MiniGame;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
@Setter
public class Generator {
    private MiniGame miniGame;

    private GeneratorType type;
    private String name;
    private Location location;
    private int maxLevel;
    private int spawnTime;
    private int upgradeTime;
    private int maxStackSize;
    private ItemStack itemStack;
    private Material itemMaterial;
    private Material blockMaterial;

    private int currentLevel = 1;
    private int currentSpawnTime;
    private int currentUpgradeTime;

    private BukkitRunnable task;

    public Generator(MiniGame miniGame, String name, GeneratorType type, Location location,
                     int maxLevel, int spawnTime, int upgradeTime, int maxStackSize,
                     Material itemMaterial, Material blockMaterial) {
        this.miniGame = miniGame;
        this.type = type;
        this.name = name;
        this.location = location;
        this.maxLevel = maxLevel;
        this.spawnTime = spawnTime;
        this.upgradeTime = upgradeTime;
        this.maxStackSize = maxStackSize;
        this.itemMaterial = itemMaterial;
        this.blockMaterial = blockMaterial;

        itemStack = new ItemStack(itemMaterial);

        currentSpawnTime = spawnTime;
        currentUpgradeTime = upgradeTime;
    }

    public void increaseLevel() {
        spawnTime = spawnTime / 2;
        currentSpawnTime = 0;
        currentLevel += 1;
    }

    public void start() {
        miniGame.runTaskTimer(task = new GeneratorRunnable(this), 0, 20);
    }

    public void remove() {
        if (miniGame.isTaskPresent(task)) {
            task.cancel();
        }
    }
}
