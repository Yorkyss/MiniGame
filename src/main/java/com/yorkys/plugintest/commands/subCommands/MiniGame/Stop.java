package com.yorkys.plugintest.commands.subCommands.MiniGame;

import com.yorkys.plugintest.commands.subCommands.SubCommand;
import org.bukkit.entity.Player;

public class Stop extends SubCommand {
    public Stop() {
        super("stop", "mg.admin");
    }

    @Override
    public void execute(Player player, String[] args) {
        mgCommand.getMiniGame().getGameManager().stop();
        player.sendMessage("Partita finita");
    }
}
