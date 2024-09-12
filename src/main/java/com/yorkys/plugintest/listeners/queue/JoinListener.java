package com.yorkys.plugintest.listeners.queue;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.gameManager.GameStates;
import com.yorkys.plugintest.players.MGPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class JoinListener implements Listener {

    private final MiniGame miniGame;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.GREEN + "+ " + ChatColor.GRAY + event.getPlayer().getName());

        MGPlayer mgp = new MGPlayer(event.getPlayer());
        if (miniGame.getGameManager().getGameState() != GameStates.QUEUE) {
            mgp.setSpectator(true);
        }
        miniGame.getMgPlayersManager().addPlayer(mgp);

        if (miniGame.getMgPlayersManager().getMgPlayers().size() >= 6) {
            miniGame.getTeamsManager().formQueue();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.RED + "- " + ChatColor.GRAY + event.getPlayer().getName());

        miniGame.getMgPlayersManager().removePlayer(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.setLeaveMessage("");
    }
}

