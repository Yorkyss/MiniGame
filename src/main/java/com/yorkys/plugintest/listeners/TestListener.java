package com.yorkys.plugintest.listeners;

import com.yorkys.plugintest.MiniGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Objects;

@RequiredArgsConstructor
public class TestListener implements Listener {

    private final MiniGame miniGame;

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        if (!miniGame.getNpcManager().existNPC(e.getRightClicked().getUniqueId())) return;

        if (Objects.equals(miniGame.getNpcManager().getNPCFromID(e.getRightClicked().getUniqueId()).getID(), "shop")) {
            e.setCancelled(true);
            player.sendMessage("&btest");
        }

    }
}

