package com.yorkys.plugintest.scoreboard;

import com.yorkys.plugintest.MiniGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class QueueScoreBoard {

    public static void createNewScoreboard(Player player, MiniGame miniGame) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("queue", "MiniGame");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "MINIGAME");

        objective.getScore(ChatColor.WHITE + " ").setScore(6);

        objective.getScore(ChatColor.WHITE + "In attesa di players..").setScore(4);
        objective.getScore(ChatColor.RED + " ").setScore(3);

        objective.getScore(ChatColor.GREEN + " ").setScore(1);
        objective.getScore(ChatColor.WHITE + Bukkit.getIp()).setScore(0);

        String teamKey1 = ChatColor.WHITE.toString();
        String teamKey2 = ChatColor.GRAY.toString();

        Team team1 = scoreboard.registerNewTeam("team1");
        team1.addEntry(teamKey1);
        team1.setPrefix(ChatColor.WHITE + "Team: ");
        if (miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam() != null) {
            team1.setSuffix(ChatColor.WHITE + "" + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam().getColor());
        } else {
            team1.setSuffix(ChatColor.WHITE + "Team: nessuno");
        }

        Team team2 = scoreboard.registerNewTeam("team2");
        team2.addEntry(teamKey2);
        team2.setPrefix(ChatColor.WHITE + "Players: ");
        team2.setSuffix(ChatColor.RED + String.valueOf(Bukkit.getOnlinePlayers().size()) + ChatColor.GRAY + "/" + ChatColor.GREEN + "6");

        objective.getScore(teamKey1).setScore(5);
        objective.getScore(teamKey2).setScore(2);

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreBoard(Player player, MiniGame miniGame) {
        if (player.getScoreboard().getObjective("queue") != null) {
            createNewScoreboard(player, miniGame);
        }
        Scoreboard scoreboard = player.getScoreboard();
        Team team1 = scoreboard.getTeam("team1");
        Team team2 = scoreboard.getTeam("team2");

        if (miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam() != null) {
            team1.setSuffix(ChatColor.WHITE + "" + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam().getColor());
        } else {
            team1.setSuffix(ChatColor.WHITE + "Team: nessuno");
        }
        team2.setSuffix(ChatColor.RED + String.valueOf(Bukkit.getOnlinePlayers().size()) + ChatColor.GRAY + "/" + ChatColor.GREEN + "6");
    }
}
