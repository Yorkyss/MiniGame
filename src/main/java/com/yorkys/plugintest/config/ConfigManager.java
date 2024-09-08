package com.yorkys.plugintest.config;

import com.yorkys.plugintest.MiniGame;
import lombok.Getter;

@Getter
public class ConfigManager {
    private MiniGame miniGame;

    private final SettingsConfig settingsConfig;

    public ConfigManager(MiniGame miniGame) {
        this.settingsConfig = new SettingsConfig(miniGame.getPlugin().getConfig());
    }
}