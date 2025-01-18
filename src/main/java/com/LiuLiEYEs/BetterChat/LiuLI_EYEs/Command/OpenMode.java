package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.Command;

import com.LiuLiEYEs.BetterChat.API.CommandAPI.ICommandAPI;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.APIModeEmu;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.ChatMainClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class OpenMode implements ICommandAPI {
    @Override
    public String getCommandAPIName() {
        return "set";
    }

    @Override
    public List<String> getTabCommandList(CommandSender sender, Command command, String label, String[] args) {
        List<String> res = new ArrayList<>();
        if(args.length == 2) {
            res.add(APIModeEmu.OnReAPI.name());
            res.add(APIModeEmu.GetAPI.name());
            res.add(APIModeEmu.YangShiAPI.name());
            return res;
        }
        else if(args.length == 3) {
            res.addAll(ChatMainClass.getAPIName(APIModeEmu.valueOf(args[1])));
            return res;
        }
        else if(args.length == 4) {
            res.add("true");
            res.add("false");
            return res;
        }
        return null;
    }

    @Override
    public boolean MainCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length >= 4) {
            ChatMainClass.turnAPIMode(APIModeEmu.valueOf(args[1]),args[2],Boolean.parseBoolean(args[3]));
            sender.sendMessage(Component.text("成功将 "+APIModeEmu.YangShiAPI.name()+" 中的 "+args[2]+" 设置为"+args[3]+"状态").color(TextColor.color(0x44FA67)));

            return true;
        }
        if(args.length == 3) {
            ChatMainClass.turnAPIMode(APIModeEmu.valueOf(args[1]),args[2],true);
            sender.sendMessage(Component.text("成功将 "+APIModeEmu.YangShiAPI.name()+" 中的 "+args[2]+" 设置为true状态").color(TextColor.color(0x44FA67)));
            return true;
        }
        return false;
    }
}
