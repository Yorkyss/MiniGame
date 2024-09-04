package com.yorkys.plugintest.generators;

import com.yorkys.plugintest.MiniGame;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GeneratorManager {
    private MiniGame miniGame;
    private JavaPlugin plugin;
    private List<Generator> generatorsList = new ArrayList<>();

    public GeneratorManager(MiniGame miniGame) {
        this.miniGame = miniGame;
        plugin = miniGame.getPlugin();
    }

    public void createGenerator(String name, Location location, GeneratorType generatorType) {
        Generator generator = new Generator(name, location, generatorType, miniGame);
        generatorsList.add(generator);
    }

    public boolean removeGenerator(String name) {
        boolean generatorExist = false;
        Iterator<Generator> iterator = generatorsList.iterator();

        while (iterator.hasNext()) {
            Generator generator = iterator.next();

            if (Objects.equals(generator.getName(), name)) {
                iterator.remove(); // Correct way to remove while iterating
                generator.remove(); // Additional cleanup if needed
                generatorExist = true;
            }
        }

        return generatorExist;
    }
}
