package com.yorkys.plugintest.gameManager;

import com.yorkys.plugintest.MiniGame;

public class GameManager {
    private MiniGame miniGame;
    private GameStates gameState;

    public GameManager(MiniGame miniGame) {
        this.miniGame = miniGame;
        gameState = GameStates.QUEUE;
    }

    public boolean start() {
        if (miniGame.getTeamsManager().formQueue()) {
            try {
                miniGame.getGeneratorManager().createAllGenerators();
            } catch (Exception e) {
                return false;
            }
            miniGame.getNpcManager().spawnAllNPCs(miniGame.getConfigManager().getSettingsConfig().shopNPCID);

            gameState = GameStates.PLAYING;
            return true;
        }
        return  false;
    }

    public void stop() {
        miniGame.getNpcManager().removeAllNPCs(miniGame.getConfigManager().getSettingsConfig().shopNPCID);
        gameState = GameStates.FINISHING;
    }

    public GameStates getGameState() {
        return  gameState;
    }

    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }
}
