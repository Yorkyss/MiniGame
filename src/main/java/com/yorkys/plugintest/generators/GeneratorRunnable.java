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
        if (generator.getCurrentUpgradeTime() == 0) {
            generator.increaseLevel();
            generator.setCurrentUpgradeTime(generator.getUpgradeTime());
        }

        if (generator.getCurrentSpawnTime() <= 0) {
            World world = generator.getLoc().getWorld();
            Collection<Entity> entityCollection = world.getNearbyEntities(generator.getLoc(), 1, 1, 1);
            long itemsOnGround = entityCollection.stream()
                    .filter(entity -> entity instanceof Item) // Filter items
                    .map(entity -> (Item) entity) // Cast to Item
                    .map(Item::getItemStack) // Get ItemStack from Item
                    .filter(itemStack -> itemStack.getType() == generator.getItemMaterial()) // Filter by material
                    .mapToLong(ItemStack::getAmount) // Get amount of each ItemStack
                    .sum(); // Sum the amounts

            if (itemsOnGround < generator.getMaxStackSize()) {
                generator.getLoc().getWorld().dropItem(generator.getLoc(), generator.getItemStack()).setVelocity(new Vector(0, 0, 0));
                generator.setCurrentSpawnTime(generator.getSpawnTime());
            }
        }

        generator.setCurrentSpawnTime(generator.getCurrentSpawnTime() - 1);
        generator.setCurrentUpgradeTime(generator.getCurrentUpgradeTime() - 1);
    }
}