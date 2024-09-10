package com.yorkys.plugintest.commands;

import com.google.common.collect.ImmutableList;
import com.yorkys.plugintest.MiniGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class GMCommand implements CommandExecutor, TabCompleter {

    private final MiniGame miniGame;
    private final String permission = "mg.gmc";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Non sei un player?");
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.RED + "Comando non valido");
            return true;
        }

        Player player = ((Player) sender).getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            player.setGameMode(GameMode.CREATIVE);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }
}
