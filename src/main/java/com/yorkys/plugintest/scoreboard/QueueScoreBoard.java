package com.yorkys.plugintest.scoreboard;

import com.yorkys.plugintest.MiniGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class QueueScoreBoard {

    public static void updateScoreBoard(Player player, MiniGame miniGame) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("match", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR); //DisplaySlot.BELOW_NAME IS UNDER PLAYER NAME || TO DO ADD STAR AND HP HEART UNDER PLAYER NAME
        objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "MINIGAME");
        objective.getScore(ChatColor.WHITE + "In attesa di players..").setScore(5);
        objective.getScore(ChatColor.RED + String.valueOf(Bukkit.getOnlinePlayers().size()) + ChatColor.GRAY + "/" + ChatColor.GREEN + miniGame.getTeamsManager().getMinMaxPlayer()).setScore(4);
        objective.getScore(ChatColor.BLACK + " ").setScore(3);
        if (miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam() != null) {
            objective.getScore(ChatColor.WHITE + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam().getColor()).setScore(7);
        } else {
            objective.getScore(ChatColor.WHITE + "nessuno").setScore(2);
        }
        objective.getScore(ChatColor.GREEN + " ").setScore(1);
        objective.getScore(ChatColor.WHITE + Bukkit.getIp()).setScore(0);

        player.setScoreboard(board);
    }
}
