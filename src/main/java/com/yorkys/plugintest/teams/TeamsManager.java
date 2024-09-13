package com.yorkys.plugintest.teams;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.players.MGPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

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
        greenTeam = new Team("green", 1, ChatColor.GREEN, miniGame);
        redTeam = new Team("red", 1, ChatColor.RED, miniGame);
    }

    public boolean formQueue() {
        if (miniGame.getMgPlayersManager().getMgPlayers().size() < minMaxPlayer) {
            System.out.println("Non ci sono abbastanza giocatori per formare le squadre.");
            return false;
        }
        mgPlayers = miniGame.getMgPlayersManager().getMgPlayers().subList(0, minMaxPlayer);
        formTeams();
        giveBelowNameScoreboards();
        mgPlayers.forEach(mgPlayer -> {
            mgPlayer.setPlaying(true);
            mgPlayer.getPlayer().getInventory().clear();
            // give kit
        });
        miniGame.getMgPlayersManager().getMgPlayers().forEach(mgPlayer -> {
            if (!mgPlayers.contains(mgPlayer)) {
                mgPlayer.setSpectator(true);
                mgPlayer.getPlayer().getInventory().clear();
                // give spectator kit
            }
        });
        return true;
    }

    public boolean addGreenPlayer(Player player) {
        MGPlayer mgp = miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player);
        if (mgp != null) {
            if (greenTeam.addPlayer(mgp)) {
                if (!mgPlayers.contains(mgp)) mgPlayers.add(mgp);
                return  true;
            }
        }
        return false;
    }

    public boolean addRedPlayer(Player player) {
        MGPlayer mgp = miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player);
        if (mgp != null) {
            if (redTeam.addPlayer(mgp)) {
                if (!mgPlayers.contains(mgp)) mgPlayers.add(mgp);
                return  true;
            }
        }
        return false;
    }

    public void formTeams() {
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
    }

    private void teleportTeams() {
        mgPlayers.forEach(mgp -> {
            mgp.getPlayer().teleport(miniGame.getConfigManager().getSettingsConfig().getPlayerSpawnLocation(mgp));
        });
    }

    public void resetQueue() {
        mgPlayers = new ArrayList<>();
        miniGame.getMgPlayersManager().getMgPlayers().forEach(mgPlayer -> {
            mgPlayer.setPlaying(false);
            mgPlayer.setSpectator(false);
            mgPlayer.resetStats();
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

    public int getMinMaxPlayer() {
        return minMaxPlayer;
    }

    public void giveBelowNameScoreboards() {
        mgPlayers.forEach(mgPlayer -> {
            Scoreboard scoreboard = mgPlayer.getPlayer().getScoreboard();

            Objective healthObjective = scoreboard.registerNewObjective("health", "health");
            healthObjective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            healthObjective.setDisplayName(ChatColor.RED + "❤");

            mgPlayer.getPlayer().setScoreboard(scoreboard);
            mgPlayer.getPlayer().setHealth(mgPlayer.getPlayer().getHealth());
        });
    }

    public MGPlayer getMGPlayer(Player player) {
        return mgPlayers.stream()
                .filter(p -> p.getPlayer().getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);
    }
}
