package com.LiuLiEYEs.BetterChat.API.CommandAPI.API;

import com.LiuLiEYEs.BetterChat.API.CommandAPI.ICommandAPI;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandMainClass implements CommandExecutor, TabCompleter {
    static List<ICommandAPI> api = new ArrayList<>();
    static public void add(ICommandAPI addIn){
        api.add(addIn);
    }

    //获取子命令列表，之后交给子命令处理
    public List<String> APICommandTabList(){
        List<String> list = new ArrayList<>();
        for(ICommandAPI command : api){
            list.add(command.getCommandAPIName());
        }
        return list;
    }

    public List<String> SecondCommandTabList(CommandSender sender, Command command, String label, String[] args){
        for(ICommandAPI s : api){
            if(s.getCommandAPIName().equalsIgnoreCase(args[0])){
                return s.getTabCommandList(sender, command, label, args);
            }
        }
        return List.of();
    }
    boolean MainCommandToDo(CommandSender sender, Command command, String label, String[] args){
        for(ICommandAPI s : api){
            if(s.getCommandAPIName().equalsIgnoreCase(args[0])){
                return s.MainCommand(sender, command, label, args);
            }
        }
        return false;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return MainCommandToDo(sender, command, label, args);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length <= 1){
            return APICommandTabList();
        }else{
            return SecondCommandTabList(sender, command, label, args);
        }
    }
}
