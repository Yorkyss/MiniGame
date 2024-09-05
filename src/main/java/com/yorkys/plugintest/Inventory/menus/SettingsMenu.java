package com.yorkys.plugintest.Inventory.menus;

import com.yorkys.plugintest.Inventory.ClickableItem;
import com.yorkys.plugintest.Inventory.Menu;
import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.utils.GlowEnchant;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.stream.IntStream;

public class SettingsMenu extends Menu {
    private MiniGame miniGame;
    private Player player;
    private HashMap<Integer, Boolean> enchantedItems = new HashMap<>();

    public SettingsMenu(MiniGame miniGame, Player player) {
        super(miniGame, player, 9*3);
        this.miniGame = miniGame;
        this.player = player;
    }

    @Override
    public void init() {
        IntStream.range(0, 9).forEach(i -> enchantedItems.put(i, true));
        enchantedItems.put(12, false);
        enchantedItems.put(14, false);
        enchantedItems.put(26, false);
        super.customInit();
    }

    @Override
    public void customInit() {
        IntStream.range(0, 9).forEach(i -> createItem(i, Material.STAINED_GLASS_PANE, 1, (byte) 7, "&7Settings", enchantedItems.get(i), null));

        createItem(4, Material.REDSTONE_COMPARATOR, 1, "&aImpostazioni Aim Practice", "&7Modifica le impostazioni", "&7della tua sessione");

        createItem(12, Material.WOOL, 1, "&bLANA", enchantedItems.get(12), event -> {
            event.getWhoClicked().sendMessage("CLICCATA LANA");
            enchantedItems.put(12, true);
            enchantedItems.put(14, false);
            customInit();
        }, "&eClicca per LANA.");

        createItem(14, Material.STONE, 1, "&bPIETRA", enchantedItems.get(14), event -> {
            event.getWhoClicked().sendMessage("CLICCATA PIETRA");
            enchantedItems.put(12, false);
            enchantedItems.put(14, true);
            customInit();
        }, "&eClicca per PIETRA.");

        createItem(26, Material.BARRIER, 1, "&4CHIUDI", enchantedItems.get(26), event -> {
            event.getWhoClicked().sendMessage("CLICCATA PIETRA");
            event.getWhoClicked().closeInventory();
        }, "&7Clicca per chiudere.");

    }
}
