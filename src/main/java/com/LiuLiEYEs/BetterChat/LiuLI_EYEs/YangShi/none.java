package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.YangShi;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.MessageYangShiAPI;
import com.LiuLiEYEs.BetterChat.API.Temp.ComponentIndexTemp;
import com.LiuLiEYEs.BetterChat.API.Temp.MessageComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class none implements MessageYangShiAPI {
    @Override
    public MessageComponent setMessage(MessageComponent nowThereIsNoSettingMessage, ParameterList l) {
        int index=0;
        //拼接获取到的组件
        TextComponent.Builder com=Component.text();
        for (ComponentIndexTemp Re : l.getComponentList()) {
            com.append(Component.text(l.getVar().substring(index,Re.BeginIndex)));
            com.append(Re.component);
            index=Re.EndIndex;
        }
        //将末尾的非组件部分加入
        com.append(Component.text(l.getVar().substring(index)));
        nowThereIsNoSettingMessage.setMessage(com.build());
        return nowThereIsNoSettingMessage;
    }

    @Override
    public String getAPIName() {
        return "None";
    }

    @Override
    public Component getDescription() {
        return Component.text("无任何特殊效果");
    }
}
