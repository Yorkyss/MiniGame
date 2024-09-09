package com.yorkys.plugintest.commands.subCommands.MiniGame;

import com.yorkys.plugintest.NPC.NPC;
import com.yorkys.plugintest.commands.subCommands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NPCCommand extends SubCommand {
    public NPCCommand() {
        super("spawnNPC", "mg.admin");
    }

    @Override
    public void execute(Player player, String[] args) {
        NPC npc = new NPC(player.getLocation(), "TEST");

        npc.spawn();
        Bukkit.getOnlinePlayers().forEach(npc::show);
    }
}
