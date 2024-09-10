package com.yorkys.plugintest.gameManager;

import com.yorkys.plugintest.MiniGame;

public class GameManager {
    private MiniGame miniGame;

    public GameManager(MiniGame miniGame) {
        this.miniGame = miniGame;

    }

    public boolean start() {
        if (miniGame.getTeamsManager().formTeams()) {
            try {
                miniGame.getGeneratorManager().createAllGenerators();
            } catch (Exception e) {
                return false;
            }
            miniGame.getNpcManager().spawnAllNPCs(miniGame.getConfigManager().getSettingsConfig().shopNPCID);

            return true;
        }
        return  false;
    }

    public void stop() {
        miniGame.getNpcManager().removeAllNPCs();
    }
}
