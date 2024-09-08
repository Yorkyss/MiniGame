package com.yorkys.plugintest.teams;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.players.MGPlayer;
import com.yorkys.plugintest.players.PlayerType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamsManager {
    private MiniGame miniGame;

    private List<MGPlayer> mgPlayers = new ArrayList<>();
    private Team greenTeam;
    private Team redTeam;
    private int minMaxPlayer = 2;

    private Location locationGreenTYPE1 = new Location(Bukkit.getWorld("world"), 7, 52, 1);
    private Location locationGreenTYPE2 = new Location(Bukkit.getWorld("world"), 7, 52, 3);
    private Location locationGreenTYPE3 = new Location(Bukkit.getWorld("world"), 7, 52, -0);
    private Location locationRedTYPE1 = new Location(Bukkit.getWorld("world"), -12, 52, 1);
    private Location locationRedTYPE2 = new Location(Bukkit.getWorld("world"), -12, 52, 3);
    private Location locationRedTYPE3 = new Location(Bukkit.getWorld("world"), -12, 52, -0);

    public TeamsManager(MiniGame miniGame) {
        this.miniGame = miniGame;
        greenTeam = new Team("green", 1, miniGame);
        redTeam = new Team("red", 1, miniGame);
    }


    public void addPlayerToQueue (Player player) {
        mgPlayers.add(new MGPlayer(player));
    }

    public void removePlayerToQueue (Player player) {
        MGPlayer mgp = getMGPlayerFromPlayer(player);
        if (mgp == null) return;
        mgp.setTeam(null);
        mgPlayers.remove(getMGPlayerFromPlayer(player));
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
        greenTeam.getPlayerFromType(PlayerType.TYPE1).getPlayer().teleport(locationGreenTYPE1);
//        greenTeam.getPlayerFromType(PlayerType.TYPE2).getPlayer().teleport(locationGreenTYPE2);
//        greenTeam.getPlayerFromType(PlayerType.TYPE3).getPlayer().teleport(locationGreenTYPE3);

        redTeam.getPlayerFromType(PlayerType.TYPE1).getPlayer().teleport(locationRedTYPE1);
//        redTeam.getPlayerFromType(PlayerType.TYPE2).getPlayer().teleport(locationRedTYPE2);
//        redTeam.getPlayerFromType(PlayerType.TYPE3).getPlayer().teleport(locationRedTYPE3);
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
