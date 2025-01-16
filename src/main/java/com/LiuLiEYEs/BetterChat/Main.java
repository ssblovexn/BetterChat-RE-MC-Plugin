package com.LiuLiEYEs.BetterChat;

import com.LiuLiEYEs.BetterChat.API.API.ChatMainClass;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.GetAPI.GetName;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC.ORC_Hand;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC.ORC_OffHand;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC.ORC_Web;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC.ORC_item;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.YangShi.none;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ChatMainClass chat = new ChatMainClass();
        chat.add(new ORC_Hand(),this);
        chat.add(new ORC_OffHand(),this);
        chat.add(new ORC_Web(),this);
        chat.add(new ORC_item(),this);
        chat.add(new GetName(),this);
        chat.add(new none());
        getServer().getPluginManager().registerEvents(chat, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
