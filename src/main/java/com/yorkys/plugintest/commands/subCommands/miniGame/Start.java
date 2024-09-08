package com.yorkys.plugintest.commands.subCommands.miniGame;

import com.yorkys.plugintest.commands.subCommands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Start extends SubCommand {
    public Start() {
        super("start", "mg.admin");
    }

    @Override
    public void execute(Player player, String[] args) {
        if (mgCommand.getMiniGame().getGameManager().start()) {
            player.sendMessage(ChatColor.AQUA + "partita iniziata!");
        } else {
            player.sendMessage(ChatColor.RED + "impossibile iniziare la partita!");
        }
    }
}
