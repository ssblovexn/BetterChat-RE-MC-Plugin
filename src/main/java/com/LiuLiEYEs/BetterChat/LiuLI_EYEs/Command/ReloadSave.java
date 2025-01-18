package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.Command;

import com.LiuLiEYEs.BetterChat.API.CommandAPI.ICommandAPI;
import com.LiuLiEYEs.BetterChat.API.Save.API.MainSave;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadSave implements ICommandAPI {
    @Override
    public String getCommandAPIName() {
        return "reload";
    }

    @Override
    public List<String> getTabCommandList(CommandSender sender, Command command, String label, String[] args) {
        return List.of();
    }

    @Override
    public boolean MainCommand(CommandSender sender, Command command, String label, String[] args) {
        MainSave.LoadALL();
        sender.sendMessage(Component.text("重加载格式完成").color(TextColor.color(0x44FA67)));
        return true;
    }
}
