package com.yorkys.plugintest.listeners.queue;


import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.inventory.Menu;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class MenuListener implements Listener {

    private final MiniGame miniGame;

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        ItemStack comparatorItem = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
        Player player = event.getPlayer();

        if (player.getInventory().getItemInHand().isSimilar(comparatorItem)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                miniGame.getInventoryManager().openSettingsMenu(event.getPlayer());
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Menu menu = miniGame.getInventoryManager().getOpened((Player) event.getWhoClicked());
        if (menu == null) return;
        switch (event.getAction()) {
            case PICKUP_ALL, PICKUP_HALF, MOVE_TO_OTHER_INVENTORY -> menu.computeIfPresent(event.getSlot(), event);
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onCloseMenu(InventoryCloseEvent event) {
        miniGame.getInventoryManager().closeMenu((Player) event.getPlayer());
    }
}
