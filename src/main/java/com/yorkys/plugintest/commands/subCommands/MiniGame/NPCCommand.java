package com.yorkys.plugintest.commands.subCommands.MiniGame;

import com.yorkys.plugintest.NPC.shop.VillagerNPC;
import com.yorkys.plugintest.commands.subCommands.SubCommand;
import org.bukkit.entity.*;

public class NPCCommand extends SubCommand {
    public NPCCommand() {
        super("spawnNPC", "mg.admin");
    }

    @Override
    public void execute(Player player, String[] args) {
        mgCommand.getMiniGame().getNpcManager().createNPC(new VillagerNPC("&e&lSHOP", "shop", player.getLocation(), false));
    }
}
