package com.yorkys.plugintest.generators;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;

public class GeneratorRunnable extends BukkitRunnable {

    private Generator generator;
    private int counter = 0;

    public GeneratorRunnable(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        if (generator.getCurrentLevel() < generator.getMaxLevel() && generator.getCurrentUpgradeTime() == 0) {
            generator.increaseLevel();
            generator.setCurrentUpgradeTime(generator.getUpgradeTime());
        }

        if (generator.getCurrentSpawnTime() <= 0) {
            World world = generator.getLocation().getWorld();
            Collection<Entity> entityCollection = world.getNearbyEntities(generator.getLocation(), 1, 1, 1);
            long itemsOnGround = entityCollection.stream()
                    .filter(entity -> entity instanceof Item)
                    .map(entity -> (Item) entity)
                    .map(Item::getItemStack)
                    .filter(itemStack -> itemStack.getType() == generator.getItemMaterial())
                    .mapToLong(ItemStack::getAmount)
                    .sum();

            if (itemsOnGround < generator.getMaxStackSize()) {
                generator.getLocation().getWorld().dropItem(generator.getLocation(), generator.getItemStack()).setVelocity(new Vector(0, 0, 0));
                generator.setCurrentSpawnTime(generator.getSpawnTime());
            }
        }

        generator.setCurrentSpawnTime(generator.getCurrentSpawnTime() - 1);
        generator.setCurrentUpgradeTime(generator.getCurrentUpgradeTime() - 1);
    }
}