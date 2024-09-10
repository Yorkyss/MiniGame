package com.yorkys.plugintest.listeners;

import com.yorkys.plugintest.MiniGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class TestListener implements Listener {

    private final MiniGame miniGame;

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (!miniGame.getNpcManager().existNPC(event.getRightClicked().getUniqueId())) return;

        if (Objects.equals(miniGame.getNpcManager().getNPCFromID(event.getRightClicked().getUniqueId()).getID(), "shop")) {
            event.setCancelled(true);
            player.sendMessage("&btest");
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        UUID entityUUID = event.getEntity().getUniqueId();

        if (miniGame.getNpcManager().existNPC(entityUUID) && Objects.equals(miniGame.getNpcManager().getNPCFromID(entityUUID).getID(), "shop")) {
            event.setCancelled(true);
        }
    }
}

