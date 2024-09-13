package com.yorkys.plugintest.listeners;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.gameManager.GameStates;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

@RequiredArgsConstructor
public class GeneralListener implements Listener {

    private final MiniGame miniGame;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (miniGame.getGameManager().getGameState() != GameStates.PLAYING
                || miniGame.getMgPlayersManager().getMGPlayerFromPlayer(event.getPlayer()).isSpectator()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (miniGame.getGameManager().getGameState() != GameStates.PLAYING
                || miniGame.getMgPlayersManager().getMGPlayerFromPlayer(event.getPlayer()).isSpectator()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (((Player) event.getEntity()).getPlayer());
        Player damager = (((Player) event.getDamager()).getPlayer());
        if (!(event.getEntity() instanceof Player) || miniGame.getMgPlayersManager().getMGPlayerFromPlayer(damager).isSpectator()) event.setCancelled(true);


        if (miniGame.getGameManager().getGameState() != GameStates.PLAYING
                || miniGame.getMgPlayersManager().getMGPlayerFromPlayer(player).isSpectator()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemPickUP(PlayerPickupItemEvent event) {
        if (miniGame.getGameManager().getGameState() != GameStates.PLAYING
                || miniGame.getMgPlayersManager().getMGPlayerFromPlayer(event.getPlayer()).isSpectator()) {
            event.setCancelled(true);
        }
    }
}
