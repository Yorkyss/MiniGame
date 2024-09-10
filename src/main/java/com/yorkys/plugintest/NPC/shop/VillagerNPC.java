package com.yorkys.plugintest.NPC.shop;

import com.yorkys.plugintest.NPC.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class VillagerNPC extends NPC {
    private Villager villager;

    public VillagerNPC(String customName, String ID, Location location) {
        super(customName, ID, location);
        hasAI = false;
        invulnerable = true;
        silent = true;
    }

    @Override
    public void spawn() {
        villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        villager.setCustomName(ChatColor.translateAlternateColorCodes('&', customName));
        villager.setCustomNameVisible(true);
        villager.setRemoveWhenFarAway(false);

        nmsEntity = ((CraftEntity) villager).getHandle();
        setAI(hasAI);
        setSilent(silent);
    }
}
