package com.yorkys.plugintest.scoreboard;

import com.yorkys.plugintest.MiniGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class MatchScoreBoard {

    public static void updateScoreBoard(Player player, MiniGame miniGame) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("match", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR); //DisplaySlot.BELOW_NAME IS UNDER PLAYER NAME || TO DO ADD STAR AND HP HEART UNDER PLAYER NAME
        objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "MINIGAME");

        if (miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam() != null) {
            objective.getScore(ChatColor.WHITE + "Team: " + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam().getColor()).setScore(7);
        } else {
            objective.getScore(ChatColor.WHITE + "Team: nessuno").setScore(7);
        }
        objective.getScore((ChatColor.WHITE + "Stelle blu: " + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam().getBlueStars())).setScore(6);
        objective.getScore((ChatColor.BLACK + "" + ChatColor.WHITE + "Stelle rosse: " + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getTeam().getRedStars())).setScore(5);
        objective.getScore(ChatColor.GRAY + " ").setScore(4);

        objective.getScore(ChatColor.WHITE + "Tu: ").setScore(3);
        objective.getScore((ChatColor.WHITE + "Stelle rosse: " + miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).getStars())).setScore(2);

        objective.getScore(ChatColor.GREEN + " ").setScore(1);
        objective.getScore(ChatColor.WHITE + Bukkit.getIp()).setScore(0);

        player.setScoreboard(board);
    }
}
