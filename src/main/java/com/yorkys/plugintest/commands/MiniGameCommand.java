package com.yorkys.plugintest.commands;


import com.google.common.collect.ImmutableList;
import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.commands.subCommands.MiniGame.Start;
import com.yorkys.plugintest.commands.subCommands.SubCommand;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Getter
public class MiniGameCommand implements CommandExecutor, TabCompleter {

    private final Map<String, SubCommand> subCommands = new HashMap<>();

    private final MiniGame miniGame;
    private CommandSender sender;

    public MiniGameCommand(MiniGame miniGame) {
        this.miniGame = miniGame;
        SubCommand.mgCommand = this;

        registerModule(new Start());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Comando non valido");
            return true;
        }
        SubCommand subCommand = getByName(args[0]);
        if (subCommand == null) {
            sender.sendMessage(ChatColor.RED + "Comando non valido");
            return true;
        }
        if (!sender.hasPermission(subCommand.getPermission())) {
            sender.sendMessage(ChatColor.RED + "Comando non valido");
            return true;
        }
        String[] subCommandArgs;
        if (args.length == 1) {
            subCommandArgs = new String[0];
        } else {
            subCommandArgs = new String[args.length - 1];
            System.arraycopy(args, 1, subCommandArgs, 0, args.length - 1);
        }
        try {
            if (sender instanceof Player player &&
                    subCommand.getClass() == subCommand.getClass().getMethod("execute", Player.class, String[].class).getDeclaringClass()) {
                subCommand.execute(player, subCommandArgs);
            } else {
                subCommand.execute(sender, subCommandArgs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return subCommands.keySet().stream()
                    .filter(module -> module.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        } else if (args.length > 1) {
            SubCommand subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) return subCommand.onTabComplete(sender, args);
        }
        return ImmutableList.of();
    }

    private SubCommand getByName(String name) {
        return subCommands.get(name.toLowerCase());
    }

    private void registerModule(SubCommand subCommand) {
        subCommands.put(subCommand.getName().toLowerCase(), subCommand);
        for (String alias : subCommand.getAliases())
            subCommands.put(alias.toLowerCase(), subCommand);
    }
}
