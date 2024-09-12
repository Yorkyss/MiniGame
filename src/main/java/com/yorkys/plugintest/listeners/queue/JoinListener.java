package com.yorkys.plugintest.listeners.queue;

import com.yorkys.plugintest.MiniGame;
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
        miniGame.getTeamsManager().addPlayerToQueue(event.getPlayer());

//        DEBUGGING QUEUE JOIN
        System.out.println("Player in queue:");
        miniGame.getTeamsManager().getMgPlayers().forEach(p -> System.out.println(p.getPlayer().getName()));
//        DEBUGGING QUEUE QUIT

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.RED + "- " + ChatColor.GRAY + event.getPlayer().getName());
        miniGame.getTeamsManager().removePlayerToQueue(event.getPlayer());

//        DEBUGGING QUEUE QUIT
        System.out.println("Player in queue:");
        miniGame.getTeamsManager().getMgPlayers().forEach(p -> System.out.println(p.getPlayer().getName()));
//        DEBUGGING QUEUE QUIT
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.setLeaveMessage("");
    }
}

