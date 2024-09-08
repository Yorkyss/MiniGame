package com.yorkys.plugintest.config;

import com.yorkys.plugintest.players.MGPlayer;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SettingsConfig {
    private final FileConfiguration config;

    public SettingsConfig(FileConfiguration config) {
        this.config = config;
    }

    public Location getPlayerSpawnLocation(MGPlayer mgPlayer) {
        if (mgPlayer.getType() == null) return null;

        String path = "playersSpawn." + mgPlayer.getTeam().getColor() + "." + mgPlayer.getType().getName();
        Location location = new Location(mgPlayer.getPlayer().getWorld(), config.getDouble(path + ".x"), config.getDouble(path + ".y"), config.getDouble(path + ".z"));
        return location;
    }
}
