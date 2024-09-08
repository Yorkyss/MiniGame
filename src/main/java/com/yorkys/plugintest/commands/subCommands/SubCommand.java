package com.yorkys.plugintest.commands.subCommands;

import com.google.common.collect.ImmutableList;
import com.yorkys.plugintest.commands.MiniGameCommand;
import com.yorkys.plugintest.utils.Colorizer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class SubCommand {

    protected static MiniGameCommand mgCommand;

    protected final String name;
    protected final String permission;
    protected final String[] aliases;
    @Setter
    private String usage;

    public SubCommand(String name, String permission, String... aliases) {
        this.name = name;
        this.permission = permission;
        this.aliases = aliases;
    }

    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.RED + "ERROR: SENDER IS NOT A PLAYER");
    }
    public void execute(Player player, String[] args) {}

    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return ImmutableList.of();
    }

    protected List<String> filterArgs(String arg, String ...strings){
        return StringUtil.copyPartialMatches(arg, Arrays.asList(strings), new ArrayList<>());
    }

    protected String builder(String[] strings) {
        return builder(strings, 0);
    }

    protected String builder(String[] strings, int start) {
        StringBuilder builder = new StringBuilder(strings[start]);
        for (int i = start + 1; i < strings.length; i++) {
            builder.append(" ").append(strings[i]);
        }
        return builder.toString();
    }

    public void sendMessage(String ...messages){
        CommandSender sender = mgCommand.getSender();
        if (sender == null) return;
        for (String message : messages) {
            sender.sendMessage(Colorizer.colorize(message));
        }
    }
}
