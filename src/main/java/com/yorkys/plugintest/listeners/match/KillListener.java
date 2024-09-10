package com.yorkys.plugintest.listeners.match;

import com.yorkys.plugintest.MiniGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@RequiredArgsConstructor
public class KillListener implements Listener {

    private final MiniGame miniGame;

    @EventHandler
    public void onEntityInteract(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        if (deadPlayer.getKiller() == null) {
            event.setDeathMessage(ChatColor.GRAY + deadPlayer.getName() + " è morto");
            return;
        }

        Player killer = event.getEntity().getKiller();
        killer.sendMessage("+1");

    }
}

