package com.yorkys.plugintest.scoreboard;

import com.yorkys.plugintest.MiniGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class MatchScoreBoard {

    public static void createOrUpdateScoreboard(Player player, MiniGame miniGame) {
        Scoreboard board = player.getScoreboard();
        Objective objective = board.getObjective("match");

        // Crea la scoreboard se non esiste ancora
        if (objective == null) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            board = manager.getNewScoreboard();
            objective = board.registerNewObjective("match", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        // Reset dei punteggi esistenti per evitare duplicazioni
        for (String entry : board.getEntries()) {
            board.resetScores(entry);
        }

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