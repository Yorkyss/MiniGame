package com.yorkys.plugintest.Inventory.menus;

import com.yorkys.plugintest.Inventory.Menu;
import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.players.MGPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.IntStream;

public class SettingsMenu extends Menu {
    private MiniGame miniGame;
    private Player player;
    private MGPlayer mgPlayer;
    private HashMap<Integer, Boolean> enchantedItems = new HashMap<>();

    public SettingsMenu(MiniGame miniGame, Player player) {
        super(miniGame, player, 9*3);
        this.miniGame = miniGame;
        this.player = player;
        mgPlayer = miniGame.getTeamsManager().getMGPlayerFromPlayer(player);
    }

    @Override
    public void init() {
        IntStream.range(0, 9).forEach(i -> enchantedItems.put(i, true));

        if (mgPlayer.getTeam() != null) {
            if (Objects.equals(mgPlayer.getTeam().getColor(), "green")) {
                enchantedItems.put(12, true);
                enchantedItems.put(14, false);
            } else if (Objects.equals(mgPlayer.getTeam().getColor(), "red")) {
                enchantedItems.put(12, false);
                enchantedItems.put(14, true);
            }
        } else {
            enchantedItems.put(12, false);
            enchantedItems.put(14, false);
        }

        enchantedItems.put(26, false);
        super.customInit();
    }

    @Override
    public void customInit() {
        IntStream.range(0, 9).forEach(i -> createItem(i, Material.STAINED_GLASS_PANE, 1, (byte) 7, "&7Settings", enchantedItems.get(i), null));

        createItem(4, Material.REDSTONE_COMPARATOR, 1, "&aImpostazioni", "&7Scegli il colore", "&7del tuo team");

        createItem(12, Material.WOOL, 1, (byte) 5, "&aVERDE", enchantedItems.get(12), event -> {
            if (miniGame.getTeamsManager().addGreenPlayer((Player) event.getWhoClicked())) {
                enchantedItems.put(12, true);
                enchantedItems.put(14, false);
                event.getWhoClicked().sendMessage(ChatColor.AQUA + "Sei entrato nel team " + ChatColor.GREEN + "verde");
                customInit();
            } else {
                event.getWhoClicked().sendMessage(ChatColor.RED + "Team pieno!");
            }
        }, "&eClicca per entrare nel team verde");

        createItem(14, Material.WOOL, 1, (byte) 14, "&cROSSO", enchantedItems.get(14), event -> {
            if (miniGame.getTeamsManager().addRedPlayer((Player) event.getWhoClicked())) {
                enchantedItems.put(12, false);
                enchantedItems.put(14, true);
                event.getWhoClicked().sendMessage(ChatColor.AQUA + "Sei entrato nel team " + ChatColor.RED + "rosso");
                customInit();
            } else {
                event.getWhoClicked().sendMessage(ChatColor.RED + "Team pieno!");
            }
        }, "&eClicca per entrare nel team rosso");

        createItem(26, Material.BARRIER, 1, "&4CHIUDI", enchantedItems.get(26), event -> {
            event.getWhoClicked().sendMessage("CLICCATA PIETRA");
            event.getWhoClicked().closeInventory();
        }, "&7Clicca per chiudere.");

    }
}
