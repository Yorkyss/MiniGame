package com.yorkys.plugintest.listeners;

import com.yorkys.plugintest.MiniGame;
import lombok.RequiredArgsConstructor;
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
        miniGame.getTeamsManager().addPlayerToQueue(event.getPlayer());

        System.out.println("Player in queue:");
        miniGame.getTeamsManager().getMgPlayers().forEach(p -> System.out.println(p.getPlayer().getName()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("- " + event.getPlayer().getName());
        miniGame.getTeamsManager().removePlayerToQueue(event.getPlayer());

        System.out.println("Player in queue:");
        miniGame.getTeamsManager().getMgPlayers().forEach(p -> System.out.println(p.getPlayer().getName()));
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.setLeaveMessage("");
    }
}

