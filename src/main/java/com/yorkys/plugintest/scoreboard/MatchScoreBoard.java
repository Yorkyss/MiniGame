package com.yorkys.plugintest.scoreboard;

import com.yorkys.plugintest.MiniGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class MatchScoreBoard {

    public static void createNewScoreboard(Player player, MiniGame miniGame) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("match", "MiniGame");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "MINIGAME");

        objective.getScore(ChatColor.WHITE + " ").setScore(3);

        objective.getScore(ChatColor.RED + " ").setScore(1);

        objective.getScore(ChatColor.WHITE + Bukkit.getIp()).setScore(0);

        String teamKey1 = ChatColor.WHITE.toString();

        Team team1 = scoreboard.registerNewTeam("team1");
        team1.addEntry(teamKey1);
        team1.setPrefix(ChatColor.WHITE + "Team: ");
        if (miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam() != null) {
            team1.setSuffix(ChatColor.WHITE + "" + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam().getColor());
        } else {
            team1.setSuffix(ChatColor.WHITE + "Team: nessuno");
        }

        objective.getScore(teamKey1).setScore(2);

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreBoard(Player player, MiniGame miniGame) {
        if (player.getScoreboard().getObjective("match") != null) {
            createNewScoreboard(player, miniGame);
        }
        Scoreboard scoreboard = player.getScoreboard();
        Team team1 = scoreboard.getTeam("team1");

        if (miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam() != null) {
            team1.setSuffix(ChatColor.WHITE + "" + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam().getColor());
        } else {
            team1.setSuffix(ChatColor.WHITE + "Team: nessuno");
        }
    }
}
