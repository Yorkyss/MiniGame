package com.yorkys.plugintest;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private MiniGame miniGame;

    @Override
    public void onEnable() {
        miniGame = new MiniGame(this);
        miniGame.onEnable();
    }

    @Override
    public void onDisable() {
        miniGame.onDisable();
    }
}
