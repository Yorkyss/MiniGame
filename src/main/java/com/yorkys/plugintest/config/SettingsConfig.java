package com.yorkys.plugintest.config;

import com.yorkys.plugintest.players.MGPlayer;
import com.yorkys.plugintest.players.PlayerType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Objects;

public class SettingsConfig {
    private final FileConfiguration config;
    public HashMap<PlayerType, Location> greenPlayerTypeLocations = new HashMap<>();
    public HashMap<PlayerType, Location> redPlayerTypeLocations = new HashMap<>();

    public SettingsConfig(FileConfiguration config) {
        this.config = config;
        greenPlayerTypeLocations.put(PlayerType.TYPE1, getPlayerTypeLocation("player-spawn.green.type1"));
        greenPlayerTypeLocations.put(PlayerType.TYPE2, getPlayerTypeLocation("player-spawn.green.type2"));
        greenPlayerTypeLocations.put(PlayerType.TYPE3, getPlayerTypeLocation("player-spawn.green.type3"));
        redPlayerTypeLocations.put(PlayerType.TYPE1, getPlayerTypeLocation("player-spawn.red.type1"));
        redPlayerTypeLocations.put(PlayerType.TYPE2, getPlayerTypeLocation("player-spawn.red.type2"));
        redPlayerTypeLocations.put(PlayerType.TYPE3, getPlayerTypeLocation("player-spawn.red.type3"));

    }

    private Location getPlayerTypeLocation(String path) {
        return new Location(Bukkit.getWorld("world"), config.getDouble(path + ".x"), config.getDouble(path + ".y"), config.getDouble(path + ".z"));
    }

    public Location getPlayerSpawnLocation(MGPlayer mgp) {
        if (Objects.equals(mgp.getTeam().getColor(), "green")) {
            return greenPlayerTypeLocations.get(mgp.getType());
        } else {
            return redPlayerTypeLocations.get(mgp.getType());
        }
    }
}
