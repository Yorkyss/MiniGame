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

    public boolean start() {
        boolean formedTeams = teamsManager.formTeams();
        teamsManager.getGreenTeam().getPlayers().forEach(p -> System.out.println("GREEN: " + p.getPlayer().getName()));
        teamsManager.getRedTeam().getPlayers().forEach(p -> System.out.println("RED: " + p.getPlayer().getName()));
        return formedTeams;
    }
}
