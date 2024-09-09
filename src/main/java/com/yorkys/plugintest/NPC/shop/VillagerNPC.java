package com.yorkys.plugintest.NPC.shop;

import com.yorkys.plugintest.NPC.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class VillagerNPC extends NPC {

    public VillagerNPC(String customName, String ID, Location location, boolean hasAI) {
        super(customName, ID, location, hasAI);
    }

    @Override
    public void spawn() {
        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);

        nmsEntity = ((CraftEntity) villager).getHandle();
        setAI(hasAI());

        villager.setCustomName(ChatColor.translateAlternateColorCodes('&', customName));
        villager.setCustomNameVisible(true);
    }
}
