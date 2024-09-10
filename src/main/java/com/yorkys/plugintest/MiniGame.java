package com.yorkys.plugintest;

import com.yorkys.plugintest.NPC.NPCManager;
import com.yorkys.plugintest.commands.GMCommand;
import com.yorkys.plugintest.commands.MiniGameCommand;
import com.yorkys.plugintest.config.ConfigManager;
import com.yorkys.plugintest.gameManager.GameManager;
import com.yorkys.plugintest.generators.GeneratorManager;
import com.yorkys.plugintest.inventory.InventoryManager;
import com.yorkys.plugintest.listeners.JoinListener;
import com.yorkys.plugintest.listeners.MenuListener;
import com.yorkys.plugintest.listeners.TestListener;
import com.yorkys.plugintest.teams.TeamsManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public final class MiniGame {
    private final JavaPlugin plugin;

    private final GeneratorManager generatorManager;
    private final TeamsManager teamsManager;
    private final GameManager gameManager;
    private final InventoryManager inventoryManager;
    private final ConfigManager configManager;
    private final NPCManager npcManager;

    MiniGame(JavaPlugin plugin) {
        this.plugin = plugin;
        generatorManager = new GeneratorManager(this);
        teamsManager = new TeamsManager(this);
        gameManager = new GameManager(this);
        inventoryManager = new InventoryManager(this);
        configManager = new ConfigManager(this);
        npcManager = new NPCManager(this);
    }

    public void onEnable() {
        registerCommands();
        registerListeners();
    }

    public void onDisable() {

    }

    private void registerCommands() {
        plugin.getCommand("gmc").setExecutor(new GMCommand(this));
        plugin.getCommand("mg").setExecutor(new MiniGameCommand(this));
    }

    private void registerListeners() {
        plugin.getServer().getPluginManager().registerEvents(new JoinListener(this), plugin);
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(this), plugin);
        plugin.getServer().getPluginManager().registerEvents(new TestListener(this), plugin);
    }

    public BukkitTask runTask(Runnable runnable){
        return runTaskLater(runnable, 0);
    }

    public BukkitTask runAsync(Runnable runnable){
        return runAsyncLater(runnable, 0);
    }

    public BukkitTask runTaskLater(Runnable runnable, long delay){
        return plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }

    public BukkitTask runAsyncLater(Runnable runnable, long delay){
        return plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    public BukkitTask runTaskTimer(Runnable runnable, long delay, long period){
        return plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, delay, period);
    }

    public BukkitTask runTaskTimer(BukkitRunnable bukkitRunnable, long delay, long period){
        return bukkitRunnable.runTaskTimer(plugin, delay, period);
    }

    public BukkitTask runTaskTimerAsynchronously(Runnable runnable, long delay, long period){
        return plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, period);
    }

    public boolean isTaskPresent(BukkitRunnable runnable) {
        try {
            return plugin.getServer().getScheduler().isQueued(runnable.getTaskId());
        } catch (IllegalStateException | NullPointerException ignore) {
            return false;
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public GeneratorManager getGeneratorManager() {
        return generatorManager;
    }

    public TeamsManager getTeamsManager() {
        return teamsManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public InventoryManager getInventoryManager() { return  inventoryManager; }

    public ConfigManager getConfigManager() { return configManager; }

    public NPCManager getNpcManager() { return npcManager; }

}
