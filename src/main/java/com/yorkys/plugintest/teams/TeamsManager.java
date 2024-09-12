package com.yorkys.plugintest.teams;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.gameManager.GameStates;
import com.yorkys.plugintest.players.MGPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamsManager {
    private MiniGame miniGame;

    private List<MGPlayer> mgPlayers = new ArrayList<>();
    private Team greenTeam;
    private Team redTeam;
    private int minMaxPlayer = 2;

    public TeamsManager(MiniGame miniGame) {
        this.miniGame = miniGame;
        greenTeam = new Team("green", 1, miniGame);
        redTeam = new Team("red", 1, miniGame);
    }


    public boolean addPlayerToQueue(Player player) {
        if (miniGame.getGameManager().getGameState() == GameStates.PLAYING) {
            mgPlayers.add(new MGPlayer(player));
            return true;
        }
        return false;
    }

    public boolean removePlayerToQueue(Player player) {
        if (miniGame.getGameManager().getGameState() == GameStates.PLAYING) {
            MGPlayer mgp = getMGPlayerFromPlayer(player);
            if (mgp == null) return true;
            mgp.setTeam(null);
            mgPlayers.remove(getMGPlayerFromPlayer(player));
            return true;
        }
        return false;
    }

    public boolean addGreenPlayer(Player player) {
        MGPlayer mgp = getMGPlayerFromPlayer(player);
        if (mgp != null) {
            if (greenTeam.addPlayer(mgp)) {
                if (!mgPlayers.contains(mgp)) mgPlayers.add(mgp);
                return  true;
            }
        }
        return false;
    }

    public boolean addRedPlayer(Player player) {
        MGPlayer mgp = getMGPlayerFromPlayer(player);
        if (mgp != null) {
            if (redTeam.addPlayer(mgp)) {
                if (!mgPlayers.contains(mgp)) mgPlayers.add(mgp);
                return  true;
            }
        }
        return false;
    }

    public boolean formTeams() {
        if (mgPlayers.size() < minMaxPlayer) {
            System.out.println("Non ci sono abbastanza giocatori per formare le squadre.");
            return false;
        }

        List<MGPlayer> noTeamPlayers = new ArrayList<>(mgPlayers.stream()
                .filter(p -> (p.getTeam() == null))
                .toList());

        if (greenTeam.getSize() < greenTeam.getMaxSize()) {
            for (int i = 0; i < greenTeam.getMaxSize() - greenTeam.getSize(); i++) {
                addGreenPlayer(noTeamPlayers.get(i).getPlayer());
                noTeamPlayers.remove(i);
            }
        }

        if (redTeam.getSize() < redTeam.getMaxSize()) {
            for (int i = 0; i < redTeam.getMaxSize() - redTeam.getSize(); i++) {
                addRedPlayer(noTeamPlayers.get(i).getPlayer());
                noTeamPlayers.remove(i);
            }
        }

        greenTeam.giveRoles();
        redTeam.giveRoles();

        teleportTeams();

        return  true;
    }

    private void teleportTeams() {
        mgPlayers.forEach(mgp -> {
            mgp.getPlayer().teleport(miniGame.getConfigManager().getSettingsConfig().getPlayerSpawnLocation(mgp));
        });
    }

    public Team getGreenTeam() {
        return greenTeam;
    }

    public Team getRedTeam() {
        return redTeam;
    }

    public List<MGPlayer> getMgPlayers() {
        return  mgPlayers;
    }

    public MGPlayer getMGPlayerFromPlayer(Player player) {
        return mgPlayers.stream()
                .filter(p -> p.getPlayer().getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);
    }
}
