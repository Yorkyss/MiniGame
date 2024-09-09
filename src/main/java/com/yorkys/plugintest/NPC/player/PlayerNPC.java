package com.yorkys.plugintest.NPC.player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerNPC {
    private Location location;
    private String name;
    private GameProfile gameProfile;
    private EntityPlayer entityPlayer;
    private String uuid;
    private String texture;
    private String signature;

    public PlayerNPC(Location location, String name, String uuid, String texture, String signature) {
        this.location = location;
        this.name = name;
        this.uuid = uuid;
        this.texture = texture;
        this.signature = signature;
    }

    public void spawn() {
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();

        gameProfile = new GameProfile(UUID.fromString(uuid), ChatColor.translateAlternateColorCodes('&', name)) ;
        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));

        entityPlayer = new EntityPlayer(minecraftServer, worldServer, gameProfile, new PlayerInteractManager(worldServer));
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public void show(Player player) {
        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;

        playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));
        playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));
        playerConnection.sendPacket(new PacketPlayOutEntityHeadRotation(entityPlayer,(byte) (entityPlayer.yaw * 256 / 300)));
    }

    public String getName() {
        return name;
    }

    public EntityPlayer getEntityPlayer() {
        return entityPlayer;
    }
}

//        SPAWN NPC
//
//        NPC npc = new NPC(player.getLocation(), "&bTEST",  "75241ea6-fba7-4075-8c2d-621084803c73","ewogICJ0aW1lc3RhbXAiIDogMTcyNTg0ODc5MjI1NywKICAicHJvZmlsZUlkIiA6ICI3NTI0MWVhNmZiYTc0MDc1OGMyZDYyMTA4NDgwM2M3MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJZb3JreXNfIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E4MmM5MTc1YTJhMzhiMGE0OTI0YzhkNDU2YzBlNzFmMmM3ZjM4OWU4MzY0ZTNjMmNiNzM1MjdlYTQxMDBjZCIKICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjM0MGMwZTAzZGQyNGExMWIxNWE4YjMzYzJhN2U5ZTMyYWJiMjA1MWIyNDgxZDBiYTdkZWZkNjM1Y2E3YTkzMyIKICAgIH0KICB9Cn0=", "ugp4qb4xsE2c+wLTgND32LNUHWCUHoSB1MyWglBm7hyWFGQwIG8cAJXCd2MNajRUSsTa3+hla63RJ/iKD/WeDRwyM0PpCyqQYtNQ0NfmXU2quAfr51cG8EhPxPgOLEeC56OqnuZWEGMOmL+uTcYYnbm86+zrMCoJ45v7dsqZB9Nc3ah8LBt73elQ0sC562YF84M2DKjDs/cVD+jm8XUnj3eYZgoZ7wgyiGaYmcLo1aA0xWw5AbRwkf7pMogkGvs+FcpKDH86s/UL5dRiLOKq0Cfn9wSjgsB83iyrMwpGJGphPAqYTLfwCtTLIP+pHqOLEp8z4MVsx98vWDyHTp1w+ybSXAejbVlAQuKkaaKXMzELUzMbDMdAdFcs7SgGRJPpMnT+zEiIVe0L1gSs7z8cqRcZjqaA0KqTtdUCiBtiX5tSnV2gNjRXcTxrLNsGSWESuiSy2CkaOT2TshopJDng6/vs/PX4li1rDjMR+/edbiE91MUH/7gcDy89obu/SF0Q7SzrnVJmvpggxPy8XVUmEQkdHry6uXxA8cirdyyjXMUZ3iihff+HlFNRAL6ZDJs79f6q3MAFxZa8xIgWSrmt9kV7AowJ0I9grWoycMjlw83m/q0icbEXUA+rMn4Mi0A7IEelFSi3mFaQwSFsAYx2HJVLoQmqOBdYQqyS7YrUyJE=");
//        npc.spawn();
//        Bukkit.getOnlinePlayers().forEach(npc::show);
//        mgCommand.getMiniGame().getNpcManager().addNPC(npc);
