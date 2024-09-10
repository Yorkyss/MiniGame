package com.yorkys.plugintest.NPC;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

import java.util.UUID;

public class NPC {
    protected String customName;
    protected String ID;
    protected Location location;

    protected Entity nmsEntity;

    protected boolean hasAI;
    protected boolean invulnerable;
    protected boolean silent;


    public NPC(String customName, String ID, Location location) {
        this.customName = customName;
        this.ID = ID;
        this.location = location;
    }

    public void spawn() {
//        EXAMPLE:
//        Entity villager should be declared as global variable

//        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
//        villager.setCustomName(ChatColor.translateAlternateColorCodes('&', customName));
//        villager.setCustomNameVisible(true);
//        villager.setRemoveWhenFarAway(false);
//
//        nmsEntity = ((CraftEntity) villager).getHandle();
//        setAI(hasAI);
//        setSilent(silent);
    }

    public void setAI(boolean value) {
        int ai = value ? 0 : 1;

        NBTTagCompound tag = nmsEntity.getNBTTag();
        if(tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);
        tag.setInt("NoAI", ai);
        nmsEntity.f(tag);
    }

    public void setSilent(boolean value) {
        int silent = value ? 1 : 0;

        NBTTagCompound tag = nmsEntity.getNBTTag();
        if(tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);
        tag.setInt("Silent", silent);
        nmsEntity.f(tag);
    }

    public String getCustomName() {
        return customName;
    }

    public String getID() {
        return ID;
    }

    public Location getLocation() {
        return location;
    }

    public boolean hasAI() {
        return hasAI;
    }

    public void removeNPC() {
        nmsEntity.getBukkitEntity().remove();
    }

    public UUID getUUID() {
        return nmsEntity.getUniqueID();
    }
}
