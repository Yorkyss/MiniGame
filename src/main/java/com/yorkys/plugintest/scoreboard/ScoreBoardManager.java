package com.yorkys.plugintest.scoreboard;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.gameManager.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class ScoreBoardManager {

    private final MiniGame miniGame;
    private BukkitRunnable task;
    private Scoreboard mainBoard;


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
//        if (!miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).isPlaying()) return;
//        String teamName = "nametagTeam";
//        Team team = mainBoard.getTeam(teamName);
//
//        if (team == null) team = mainBoard.registerNewTeam(teamName);
//
//        team.addEntry(player.getName());
//        team.setPrefix(miniGame.getTeamsManager().getMGPlayer(player).getTeam().getChatColor() + "");
//        team.setSuffix(ChatColor.GRAY + " " + miniGame.getTeamsManager().getMGPlayer(player).getStars() + ChatColor.DARK_RED + "★");
    }
}