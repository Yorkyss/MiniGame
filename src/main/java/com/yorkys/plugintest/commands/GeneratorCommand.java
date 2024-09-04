package com.yorkys.plugintest.commands;

import com.google.common.collect.ImmutableList;
import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.generators.GeneratorType;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class GeneratorCommand implements CommandExecutor, TabCompleter {

    private final MiniGame miniGame;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Non sei un player?");
            return true;
        }

        Player player = ((Player) sender).getPlayer();

        if (args.length == 0 || (args.length < 2 && args[0].equals("remove")) || (args.length < 3 && args[0].equals("add"))) {
            sendSyntaxError(player);
            return true;
        }



        if (args[0].equals("remove")) {
            if (miniGame.getGeneratorManager().removeGenerator(args[1])) {
                player.sendMessage(ChatColor.GREEN + "Generatore rimosso correttamente!");
            } else {
                player.sendMessage(ChatColor.RED + "Generatore non trovato.");
            }
        } else if (args[0].equals("add")) {
            switch (args[1]) {
                case "iron" ->
                        miniGame.getGeneratorManager().createGenerator(args[2], player.getLocation().getBlock().getLocation(), GeneratorType.IRON);
                case "gold" ->
                        miniGame.getGeneratorManager().createGenerator(args[2], player.getLocation().getBlock().getLocation(), GeneratorType.GOLD);
                case "diamond" ->
                        miniGame.getGeneratorManager().createGenerator(args[2], player.getLocation().getBlock().getLocation(), GeneratorType.DIAMOND);
                default -> {
                    player.sendMessage(ChatColor.RED + "Sintassi errata: /generator add <diamond/gold/iron> <name>");
                    return true;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Generatore aggiunto correttamente!");
        } else {
            sendSyntaxError(player);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }

    private void sendSyntaxError(Player player) {
        player.sendMessage(ChatColor.DARK_RED + "Sinstassi errata:");
        player.sendMessage(ChatColor.RED + "-> /generator add <diamond/gold/iron> <name>");
        player.sendMessage(ChatColor.RED + "-> /generator remove <name>");
    }
}
