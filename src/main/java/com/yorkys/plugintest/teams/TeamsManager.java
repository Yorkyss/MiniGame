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

    private List<MGPlayer> MGPlayers = new ArrayList<>();
    private Team greenTeam;
    private Team redTeam;
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
            MGPlayers.add(new MGPlayer(player));
        });

        if (MGPlayers.size() >= 6) {
            List<MGPlayer> greenTeamPlayers = MGPlayers.subList(0, 3);
            greenTeam = new Team(greenTeamPlayers, "green", 3);

            List<MGPlayer> redTeamPlayers = MGPlayers.subList(3, 6);
            redTeam = new Team(redTeamPlayers, "red", 3);
        } else {
            System.out.println("Non ci sono abbastanza giocatori per formare le squadre.");
        }

        greenTeam.formRoles();
        redTeam.formRoles();

        greenTeam.getPlayers().forEach(MGPlayer -> Bukkit.getPlayer("Yorkys_").sendMessage( ChatColor.GREEN + MGPlayer.getPlayer().getName()));
        redTeam.getPlayers().forEach(MGPlayer -> Bukkit.getPlayer("Yorkys_").sendMessage( ChatColor.RED + MGPlayer.getPlayer().getName()));

        teleportTeams();
        Bukkit.getPlayer("Yorkys_").sendMessage("teleported players in their team");
    }

    private void teleportTeams() {
        greenTeam.getType1().getPlayer().teleport(locationGreenTYPE1);
        greenTeam.getType2().getPlayer().teleport(locationGreenTYPE2);
        greenTeam.getType3().getPlayer().teleport(locationGreenTYPE3);

        redTeam.getType1().getPlayer().teleport(locationRedTYPE1);
        redTeam.getType2().getPlayer().teleport(locationRedTYPE2);
        redTeam.getType3().getPlayer().teleport(locationRedTYPE3);
    }
}
