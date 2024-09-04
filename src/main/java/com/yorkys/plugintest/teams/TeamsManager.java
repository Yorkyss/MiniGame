package com.yorkys.plugintest.teams;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.players.MGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class TeamsManager {
    private MiniGame miniGame;

    private List<MGPlayer> mgPlayers = new ArrayList<>();
    private Team greenTeam = new Team("green", 3);
    private Team redTeam = new Team("red", 3);

    private Location locationGreenTYPE1 = new Location(Bukkit.getWorld("world"), 7, 52, 1);
    private Location locationGreenTYPE2 = new Location(Bukkit.getWorld("world"), 7, 52, 3);
    private Location locationGreenTYPE3 = new Location(Bukkit.getWorld("world"), 7, 52, -0);
    private Location locationRedTYPE1 = new Location(Bukkit.getWorld("world"), -12, 52, 1);
    private Location locationRedTYPE2 = new Location(Bukkit.getWorld("world"), -12, 52, 3);
    private Location locationRedTYPE3 = new Location(Bukkit.getWorld("world"), -12, 52, -0);

    public TeamsManager(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    public void formTeams() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            mgPlayers.add(new MGPlayer(player));
        });

        if (mgPlayers.size() < 6) {
            System.out.println("Non ci sono abbastanza giocatori per formare le squadre.");
            return;
        }


        if (greenTeam.getSize() != 3) {
            List<MGPlayer> greenTeamPlayers = mgPlayers.subList(greenTeam.getSize(), 3);
            greenTeamPlayers.forEach(greenPlayer -> {
                if (greenPlayer.getTeam() == null) {
                    greenTeam.addPlayer(greenPlayer);
                }
            });
        }

        if (redTeam.getSize() != 3) {
            List<MGPlayer> redTeamPlayers = mgPlayers.subList(3 + redTeam.getSize(), 6);
            redTeamPlayers.forEach(redPlayer -> {
                if (redPlayer.getTeam() == null) {
                    redTeam.addPlayer(redPlayer);
                }
            });
        }

        greenTeam.getPlayers().forEach(p -> System.out.println(ChatColor.GREEN + p.getPlayer().getName()));
        System.out.println();
        System.out.println(greenTeam.getMaxSize());
        redTeam.getPlayers().forEach(p -> System.out.println(ChatColor.RED + p.getPlayer().getName()));
        System.out.println(redTeam.getMaxSize());

        greenTeam.formRoles();
        redTeam.formRoles();

        teleportTeams();
    }

    private void teleportTeams() {
        greenTeam.getType1().getPlayer().teleport(locationGreenTYPE1);
        greenTeam.getType2().getPlayer().teleport(locationGreenTYPE2);
        greenTeam.getType3().getPlayer().teleport(locationGreenTYPE3);

        redTeam.getType1().getPlayer().teleport(locationRedTYPE1);
        redTeam.getType2().getPlayer().teleport(locationRedTYPE2);
        redTeam.getType3().getPlayer().teleport(locationRedTYPE3);
    }

    public Team getGreenTeam() {
        return greenTeam;
    }

    public Team getRedTeam() {
        return redTeam;
    }
}
