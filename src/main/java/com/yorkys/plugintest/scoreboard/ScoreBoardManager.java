package com.yorkys.plugintest.scoreboard;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.gameManager.GameStates;
import com.yorkys.plugintest.players.MGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class ScoreBoardManager {

    private final MiniGame miniGame;
    private BukkitRunnable task;

    public ScoreBoardManager(MiniGame miniGame) {
        this.miniGame = miniGame;
        startTask();
    }

    public void startTask() {
        miniGame.runTaskTimer(task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    updateScoreboard(player);
                    updateScoreboardNameTag(player);
                }
            }
        }, 0, 20);
    }

    public void cancelTask() {
        if (task != null) {
            task.cancel();
        }
    }

    public void updateScoreboard(Player player) {
        if (miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).isSpectator() && !miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).isPlaying()) {
            SpectatorScoreBoard.updateScoreBoard(player, miniGame);
        } else {
            if (miniGame.getGameManager().getGameState() == GameStates.QUEUE) {
                QueueScoreBoard.createOrUpdateScoreboard(player, miniGame);
            } else if (miniGame.getGameManager().getGameState() == GameStates.PLAYING) {
                MatchScoreBoard.createOrUpdateScoreboard(player, miniGame);
            }
        }
    }

    private void updateScoreboardNameTag(Player player) {
        Scoreboard board = player.getScoreboard();
        MGPlayer mgPlayer = miniGame.getTeamsManager().getMGPlayer(player);

        if (mgPlayer == null || !mgPlayer.isPlaying()) return;

        Team team = board.getTeam(player.getName());
        if (team == null) {
            team = board.registerNewTeam(player.getName());
        }

        String prefix = mgPlayer.getTeam().getChatColor() + ""; // Personalizza il prefisso come desideri
        String suffix =  ChatColor.GRAY + " " + mgPlayer.getStars() + ChatColor.DARK_RED + "★";
        team.setPrefix(prefix);
        team.setSuffix(suffix);

        team.addEntry(player.getName());

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.setScoreboard(board);
        }
    }
}