package com.yorkys.plugintest.commands;


import com.google.common.collect.ImmutableList;
import com.yorkys.plugintest.MiniGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class MiniGameCommand implements CommandExecutor, TabCompleter {

    private final MiniGame miniGame;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Non sei un player?");
            return true;
        }
        Player player = ((Player) sender).getPlayer();

        if (miniGame.getGameManager().start()) {
            player.sendMessage(ChatColor.AQUA + "partita iniziata!");
        } else {
            player.sendMessage(ChatColor.RED + "impossibile iniziare la partita!!");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }
}
