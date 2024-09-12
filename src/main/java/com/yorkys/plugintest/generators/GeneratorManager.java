package com.yorkys.plugintest.generators;

import com.yorkys.plugintest.MiniGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GeneratorManager {
    private MiniGame miniGame;
    private List<Generator> generatorsList = new ArrayList<>();

    public GeneratorManager(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    public void spawnGenerator(Generator generator) {
        generator.start();
        generatorsList.add(generator);
    }

    public void createAllGenerators() {
        miniGame.getConfigManager().getSettingsConfig().getGenerators().forEach(this::spawnGenerator);
    }

    public void removeAllGenerators() {
        miniGame.getConfigManager().getSettingsConfig().getGenerators().forEach(Generator::remove);
    }

    public boolean removeGenerator(String name) {
        boolean generatorExist = false;
        Iterator<Generator> iterator = generatorsList.iterator();

        while (iterator.hasNext()) {
            Generator generator = iterator.next();

            if (Objects.equals(generator.getName(), name)) {
                iterator.remove();
                generator.remove();
                generatorExist = true;
            }
        }

        return generatorExist;
    }
}
