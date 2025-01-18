package com.LiuLiEYEs.BetterChat;

import com.LiuLiEYEs.BetterChat.API.CommandAPI.API.CommandMainClass;
import com.LiuLiEYEs.BetterChat.API.CommandAPI.U.AConfigLoaderCommand;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.ChatMainClass;
import com.LiuLiEYEs.BetterChat.API.Save.API.MainSave;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.Command.OpenMode;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.Command.PluginList;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.Command.ReloadSave;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.GetAPI.GetName;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.GetAPI.GetPlayerTitle;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC.ORC_Hand;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC.ORC_OffHand;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC.ORC_Web;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC.ORC_item;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.SaveAndLoad.LoadGetAPI;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.SaveAndLoad.LoadReAPI;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.SaveAndLoad.LoadYangShiFormat;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ChatFormatAPI.none;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //加载聊天插件
        ChatMainClass.add(new GetPlayerTitle(),this);
        ChatMainClass.add(new ORC_Hand(),this);
        ChatMainClass.add(new ORC_OffHand(),this);
        ChatMainClass.add(new ORC_Web(),this);
        ChatMainClass.add(new ORC_item(),this);
        ChatMainClass.add(new GetName(),this);
        ChatMainClass.add(new none());

        getServer().getPluginManager().registerEvents(new ChatMainClass(), this);
        //加载指令插件
        //   注解类
        AConfigLoaderCommand configLoaderAPI = new AConfigLoaderCommand();
        configLoaderAPI.add(new GetName());
        configLoaderAPI.add(new GetPlayerTitle());


        CommandMainClass.add(configLoaderAPI);
        CommandMainClass.add(new PluginList());
        CommandMainClass.add(new OpenMode());
        CommandMainClass.add(new ReloadSave());
        getServer().getPluginCommand("LiuLIBetterChat").setExecutor(new CommandMainClass());


        //加载启动项
        MainSave.addSave(new LoadGetAPI());
        MainSave.addSave(new LoadReAPI());
        MainSave.addSave(new LoadYangShiFormat());
        MainSave.addSave(new GetName());
        MainSave.addSave(new GetPlayerTitle());
        MainSave.LoadALL();

    }

    @Override
    public void onDisable() {
        MainSave.SaveALL();
    }

}
