package com.yorkys.plugintest.listeners;

import com.yorkys.plugintest.MiniGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
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
        event.setJoinMessage("+ " + event.getPlayer().getName());

        if (Bukkit.getOnlinePlayers().size() == 6) {
            miniGame.getGameManager().start();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("- " + event.getPlayer().getName());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.setLeaveMessage("");
    }
}

