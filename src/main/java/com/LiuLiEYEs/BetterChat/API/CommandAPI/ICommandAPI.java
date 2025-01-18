package com.LiuLiEYEs.BetterChat.API.CommandAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface ICommandAPI {
    String getCommandAPIName();
    List<String> getTabCommandList(CommandSender sender, Command command, String label, String[] args);
    boolean MainCommand(CommandSender sender, Command command, String label, String[] args);
}
