package com.yorkys.plugintest.scoreboard;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.gameManager.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
                QueueScoreBoard.updateScoreBoard(player, miniGame);
            } else if (miniGame.getGameManager().getGameState() == GameStates.PLAYING) {
                MatchScoreBoard.updateScoreBoard(player, miniGame);
            }
        }
    }
}