package com.yorkys.plugintest.generators;

import com.yorkys.plugintest.MiniGame;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Collection;

@Getter
@Setter
public class Generator {
    private MiniGame miniGame;
    private JavaPlugin plugin;
    private String name; // generator name (for config)
    private Location loc; // generator location
    private GeneratorType generatorType;

    private int spawnTime; // time
    private int upgradeTime; // total time
    private Material blockMaterial;
    private Material itemMaterial;
    private int maxStackSize;

    private ItemStack itemStack;

    private int currentLevel = 1; // generator level
    private int currentSpawnTime;
    private int currentUpgradeTime;

    private BukkitRunnable task;

    public Generator(String name, Location loc, GeneratorType generatorType, MiniGame miniGame) {
        this.name = name;
        this.loc = new Location(loc.getWorld(), loc.getX() + 0.5, loc.getY() + 0.5, loc.getZ() + 0.5);
        this.miniGame = miniGame;

        plugin = miniGame.getPlugin();

        spawnTime = generatorType.getSpawnTime();
        upgradeTime = generatorType.getUpgradeTime();
        blockMaterial = generatorType.getBlockMaterial();
        itemMaterial = generatorType.getItemMaterial();
        maxStackSize = generatorType.getMaxStackSize();

        itemStack = new ItemStack(itemMaterial);

        currentSpawnTime = spawnTime;
        currentUpgradeTime = upgradeTime;

        StartGenerator();
    }

    public void increaseLevel() {
        if (currentLevel >= 3) {
            return;
        }

        spawnTime = spawnTime / 2;
        currentSpawnTime = 0;
        currentLevel += 1;

        Firework firework = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta data = firework.getFireworkMeta();
        data.addEffects(FireworkEffect.builder().withColor(Color.RED).withColor(Color.WHITE).withColor(Color.LIME)
                .withColor(Color.PURPLE).withColor(Color.AQUA).withColor(Color.BLUE).withColor(Color.YELLOW)
                .withColor(Color.GREEN).with(Type.BALL_LARGE).build());
        data.setPower(0);
        firework.setFireworkMeta(data);
    }

    public void StartGenerator() {
        miniGame.runTaskTimer(task = new GeneratorRunnable(this), 0, 20);
    }

    public void remove() {
        if (miniGame.isTaskPresent(task)) {
            task.cancel();
        }
    }
}
