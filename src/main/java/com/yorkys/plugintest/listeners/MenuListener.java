package com.yorkys.plugintest.listeners;


import com.yorkys.plugintest.MiniGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class MenuListener implements Listener {

    private final MiniGame miniGame;

    public void PlayerInteractEvent(PlayerInteractEvent event) {
        ItemStack comparatorItem = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
        Player player = event.getPlayer();

        if (player.getInventory().getItemInHand().isSimilar(comparatorItem)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                //open menu
            }
        }
    }
}
