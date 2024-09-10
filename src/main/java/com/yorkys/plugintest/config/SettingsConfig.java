package com.yorkys.plugintest.config;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.generators.Generator;
import com.yorkys.plugintest.generators.GeneratorType;
import com.yorkys.plugintest.players.MGPlayer;
import com.yorkys.plugintest.players.PlayerType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SettingsConfig {
    private final FileConfiguration config;
    private final MiniGame miniGame;
    // Players spawn locations
    public HashMap<PlayerType, Location> greenPlayerTypeLocations = new HashMap<>();
    public HashMap<PlayerType, Location> redPlayerTypeLocations = new HashMap<>();


    // Generators settings
    public HashMap<String, Location> generatorLocations = new HashMap<>();
    public HashMap<String, Integer> generatorMaxLevel = new HashMap<>();
    public HashMap<String, Integer> generatorTimeToSpawn = new HashMap<>();
    public HashMap<String, Integer> generatorTimeToUpgrade = new HashMap<>();
    public HashMap<String, Integer> generatorMaxStackSize = new HashMap<>();
    public HashMap<String, String> generatorItemMaterial = new HashMap<>();
    public HashMap<String, String> generatorBlockMaterial = new HashMap<>();
    public List<Generator> generators = new ArrayList<>();

    public SettingsConfig(FileConfiguration config, MiniGame miniGame) {
        this.config = config;
        this.miniGame = miniGame;

        // Storing players spawn locations
        greenPlayerTypeLocations.put(PlayerType.TYPE1, getLocationWithPath("player-spawn.green.type1"));
        greenPlayerTypeLocations.put(PlayerType.TYPE2, getLocationWithPath("player-spawn.green.type2"));
        greenPlayerTypeLocations.put(PlayerType.TYPE3, getLocationWithPath("player-spawn.green.type3"));
        redPlayerTypeLocations.put(PlayerType.TYPE1, getLocationWithPath("player-spawn.red.type1"));
        redPlayerTypeLocations.put(PlayerType.TYPE2, getLocationWithPath("player-spawn.red.type2"));
        redPlayerTypeLocations.put(PlayerType.TYPE3, getLocationWithPath("player-spawn.red.type3"));

        // Storing generators settings
        initializeGeneratorsSettings("green", "iron");
        initializeGeneratorsSettings("green", "gold");
        initializeGeneratorsSettings("green", "diamond");
        initializeGeneratorsSettings("red", "iron");
        initializeGeneratorsSettings("red", "gold");
        initializeGeneratorsSettings("red", "diamond");
    }

    // path should be the word before x, y, z
    // (es: generator.iron.location -> location.x etc..
    // or player-spawn.green.type1 -> type1.x etc..
    private Location getLocationWithPath(String path) {
        return new Location(Bukkit.getWorld("world"), config.getDouble(path + ".x"), config.getDouble(path + ".y"), config.getDouble(path + ".z"));
    }

    private void initializeGeneratorsSettings(String name, String type) {
        try {
            Location location = getLocationWithPath("generators." + name + "." + type + ".location");
            int maxLevel = config.getInt("generators." + name + "." + type + ".max-level");
            int timeToSpawn = config.getInt("generators." + name + "." + type + ".time-to-spawn");
            int timeToUpgrade = config.getInt("generators." + name + "." + type + ".time-to-upgrade");
            int maxStackSize = config.getInt("generators." + name + "." + type + ".max-stack-size");
            Material itemMaterial = Material.getMaterial(config.getString("generators." + name + "." + type + ".item-material"));
            Material blockMaterial = Material.getMaterial(config.getString("generators." + name + "." + type + ".block-material"));

            generatorLocations.put(type, location);
            generatorMaxLevel.put(type, maxLevel);
            generatorTimeToSpawn.put(type, timeToSpawn);
            generatorTimeToUpgrade.put(type, timeToUpgrade);
            generatorMaxStackSize.put(type, maxStackSize);
            generatorItemMaterial.put(type, config.getString("generators." + name + "." + type + ".item-material"));
            generatorBlockMaterial.put(type, config.getString("generators." + name + "." + type + ".block-material"));

            generators.add(new Generator(
                    miniGame,
                    name + "-" + type,
                    GeneratorType.fromName(type),
                    location,
                    maxLevel,
                    timeToSpawn,
                    timeToUpgrade,
                    maxStackSize,
                    itemMaterial,
                    blockMaterial
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Location getPlayerSpawnLocation(MGPlayer mgp) {
        if (Objects.equals(mgp.getTeam().getColor(), "green")) {
            return greenPlayerTypeLocations.get(mgp.getType());
        } else {
            return redPlayerTypeLocations.get(mgp.getType());
        }
    }

    public List<Generator> getGenerators() {
        return generators;
    }
}
