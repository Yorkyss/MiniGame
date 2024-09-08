package com.yorkys.plugintest.inventory;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.utils.Colorizer;
import com.yorkys.plugintest.utils.GlowEnchant;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class Menu {
    private MiniGame miniGame;
    private Player player;

    private Inventory inventory;

    private String name = "GUI";
    private int size = 9*3;

    protected final HashMap<Integer, ClickableItem> items = new HashMap<>();

    public Menu(MiniGame miniGame, Player player) {
        this.miniGame = miniGame;
        this.player = player;
        inventory = Bukkit.createInventory(player, size, Colorizer.colorize(name));
    }

    public Menu(MiniGame miniGame, Player player, int size) {
        this.miniGame = miniGame;
        this.player = player;
        this.size = size;
        inventory = Bukkit.createInventory(player, size, Colorizer.colorize(name));
    }

    public void init() {
        customInit();
    }

    public void customInit() {

    }

    protected ItemStack getItemAtSlot(int slot) {
        return items.get(slot).getItem();
    }

    protected void createItem(int slot, Material material, int amount, String name, Consumer<InventoryClickEvent> consumer, String... lore) {
        createItem(slot, material, amount, (byte) 0, name, false, consumer, lore);
    }

    protected void createItem(int slot, Material material, int amount, String name, Consumer<InventoryClickEvent> consumer, List<String> lore) {
        createItem(slot, material, amount, (byte) 0, name, false, consumer, lore);
    }

    protected void createItem(int slot, Material material, int amount, String name, String... lore) {
        createItem(slot, material, amount, (byte) 0, name, false, null, lore);
    }

    protected void createItem(int slot, Material material, int amount, byte data, String name, Consumer<InventoryClickEvent> consumer, String... lore) {
        createItem(slot, material, amount, data, name, false, consumer, lore);
    }

    protected void createItem(int slot, Material material, int amount, byte data, String name, Consumer<InventoryClickEvent> consumer, List<String> lore) {
        createItem(slot, material, amount, data, name, false, consumer, lore);
    }

    protected void createItem(int slot, Material material, int amount, String name, boolean glowing, Consumer<InventoryClickEvent> consumer,
                              String... lore) {
        createItem(slot, material, amount, (byte) 0, name, glowing, consumer, lore);
    }

    protected void createItem(int slot, Material material, int amount, String name, boolean glowing, Consumer<InventoryClickEvent> consumer,
                              List<String> lore) {
        createItem(slot, material, amount, (byte) 0, name, glowing, consumer, lore);
    }

    protected void createItem(int slot, Material material, int amount, byte data, String name, boolean glowing, Consumer<InventoryClickEvent> consumer,
                              String... lore) {
        ItemStack item = new ItemStack(material, amount, data);
        ItemMeta meta = item.getItemMeta();
        if (name != null) meta.setDisplayName(Colorizer.colorize(name));
        if (lore.length != 0) meta.setLore(Colorizer.colorize(lore));
        item.setItemMeta(meta);
        if (glowing) GlowEnchant.addGlow(item);
        items.put(slot, ClickableItem.of(item, consumer));
        inventory.setItem(slot, item);
    }

    protected void createItem(int slot, Material material, int amount, byte data, String name, boolean glowing, Consumer<InventoryClickEvent> consumer,
                              List<String> lore) {
        ItemStack item = new ItemStack(material, amount, data);
        ItemMeta meta = item.getItemMeta();
        if (name != null) meta.setDisplayName(Colorizer.colorize(name));
        if (lore != null) meta.setLore(Colorizer.colorize(lore));
        item.setItemMeta(meta);
        if (glowing) GlowEnchant.addGlow(item);
        items.put(slot, ClickableItem.of(item, consumer));
        inventory.setItem(slot, item);
    }

    protected void createItem(int slot, ItemStack item, Consumer<InventoryClickEvent> consumer) {
        items.put(slot, ClickableItem.of(item, consumer));
        inventory.setItem(slot, item);
    }

    protected void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    protected void changeName(int slot, String name) {
        ItemMeta meta = items.get(slot).getItem().getItemMeta();
        meta.setDisplayName(Colorizer.colorize(name));
        items.get(slot).getItem().setItemMeta(meta);
        inventory.setItem(slot, items.get(slot).getItem());
    }

    protected void changeLore(int slot, String... lore) {
        ItemMeta meta = items.get(slot).getItem().getItemMeta();
        meta.setLore(Colorizer.colorize(lore));
        items.get(slot).getItem().setItemMeta(meta);
        inventory.setItem(slot, items.get(slot).getItem());
    }

    protected void changeLore(int slot, List<String> lore) {
        ItemMeta meta = items.get(slot).getItem().getItemMeta();
        meta.setLore(Colorizer.colorize(lore));
        items.get(slot).getItem().setItemMeta(meta);
        inventory.setItem(slot, items.get(slot).getItem());
    }

    public void open(HumanEntity player) {
        init();
        customInit();
        player.openInventory(inventory);
        miniGame.getInventoryManager().addOpenedInventory(player.getEntityId(), this);
    }

    public void computeIfPresent(int slot, InventoryClickEvent event) {
        ClickableItem clickableItem = items.get(slot);
        if (clickableItem != null) clickableItem.run(event);
    }

    public void enchantItem(ItemStack itemStack, boolean cleanEnchantedItems) {
        if (cleanEnchantedItems && !items.isEmpty()) {
            for (int i = 0; i < size; i++) {
                try {
                    GlowEnchant.removeGlow(inventory.getItem(i));
                } catch(NullPointerException ignored) {}
            }
        }

        GlowEnchant.addGlow(itemStack);
    }
}
