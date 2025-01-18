package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.Command;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.APIModeEmu;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.ChatMainClass;
import com.LiuLiEYEs.BetterChat.API.CommandAPI.ICommandAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class PluginList implements ICommandAPI {
    @Override
    public String getCommandAPIName() {
        return "list";
    }

    @Override
    public List<String> getTabCommandList(CommandSender sender, Command command, String label, String[] args) {
        List<String> res = new ArrayList<>();
        res.add(APIModeEmu.ALL.name());
        res.add(APIModeEmu.OnReAPI.name());
        res.add(APIModeEmu.GetAPI.name());
        res.add(APIModeEmu.YangShiAPI.name());
        return res;
    }

    @Override
    public boolean MainCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            APIModeEmu emu = APIModeEmu.valueOf(args[1]);
            sender.sendMessage(ChatMainClass.GetDescription(emu));
        }else {
            sender.sendMessage(ChatMainClass.GetDescription(APIModeEmu.ALL));
        }
        return false;
    }
}
