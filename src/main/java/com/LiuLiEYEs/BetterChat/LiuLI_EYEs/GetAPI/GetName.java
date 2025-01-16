package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.GetAPI;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.GetAPI;
import com.LiuLiEYEs.BetterChat.API.Temp.ComponentIndexTemp;
import com.LiuLiEYEs.BetterChat.API.Temp.MessageComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import net.kyori.adventure.text.Component;


public class GetName implements GetAPI {

    @Override
    public MessageComponent GetAPIAndAddInToTheMessage(ParameterList temp, MessageComponent messageComponent) {
        messageComponent.add(Component.text(" <"+temp.getPlayer().getName()+"> "));
        return messageComponent;
    }

    @Override
    public String getAPIName() {
        return "GetName";
    }

    @Override
    public Component getDescription() {
        return Component.text("获取玩家id");
    }
}
