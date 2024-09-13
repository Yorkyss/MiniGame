package com.yorkys.plugintest.gameManager;

import com.yorkys.plugintest.MiniGame;

public class GameManager {
    private MiniGame miniGame;
    private GameStates gameState;
    private GameRounds gameRounds;

    public GameManager(MiniGame miniGame) {
        this.miniGame = miniGame;
        gameState = GameStates.QUEUE;
    }

    public boolean start() {
        if (gameState != GameStates.QUEUE) return false;
        if (miniGame.getTeamsManager().formQueue()) {
            try {
                miniGame.getGeneratorManager().createAllGenerators();
            } catch (Exception e) {
                return false;
            }
            miniGame.getNpcManager().spawnAllNPCs(miniGame.getConfigManager().getSettingsConfig().shopNPCID);

            gameState = GameStates.PLAYING;
            gameRounds = GameRounds.ROUND1;
            return true;
        }
        return  false;
    }

    public void stop() {
        if (gameState != GameStates.PLAYING) return;
        gameState = GameStates.FINISHING;
        miniGame.getNpcManager().removeAllNPCs(miniGame.getConfigManager().getSettingsConfig().shopNPCID);
        miniGame.getGeneratorManager().removeAllGenerators();
        miniGame.getTeamsManager().resetQueue();
        gameState = GameStates.QUEUE;
        gameRounds = GameRounds.ROUND1;
    }

    public GameStates getGameState() {
        return  gameState;
    }

    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    public GameRounds getGameRounds() {
        return gameRounds;
    }

    public void setGameRounds(GameRounds gameRounds) {
        this.gameRounds = gameRounds;
    }
}
