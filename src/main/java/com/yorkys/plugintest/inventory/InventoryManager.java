package com.yorkys.plugintest.inventory;

import com.yorkys.plugintest.inventory.menus.SettingsMenu;
import com.yorkys.plugintest.MiniGame;
import lombok.Getter;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

@Getter
public class InventoryManager {

    private final MiniGame miniGame;
    private static final Map<Integer, Menu> openedInventory = new HashMap<>();

    public InventoryManager(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    public void openSettingsMenu(Player player) {
        new SettingsMenu(miniGame, player).open(player.getPlayer());
    }

    public Menu getOpened(Player player) {
        return openedInventory.get(player.getEntityId());
    }

    public void closeMenu(Player player) {
        openedInventory.remove(player.getEntityId());
    }

    public void addOpenedInventory(int entityId, Menu menu) {
        openedInventory.put(entityId, menu);
    }

}
