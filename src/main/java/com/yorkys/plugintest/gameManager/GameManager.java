package com.yorkys.plugintest.gameManager;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.teams.TeamsManager;

public class GameManager {
    private MiniGame miniGame;
    private TeamsManager teamsManager;

    public GameManager(MiniGame miniGame) {
        this.miniGame = miniGame;
        teamsManager = miniGame.getTeamsManager();

    }

    public void start() {
        miniGame.getTeamsManager().formTeams();
    }
}
