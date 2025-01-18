package com.LiuLiEYEs.BetterChat.API.Temp;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import java.util.ArrayList;
import java.util.List;

import static net.kyori.adventure.text.Component.text;

public class MessageComponent
{
    List<Component> GettingList = new ArrayList<>();
    Component NewMessage;

    public MessageComponent add(Component component){
        GettingList.add(component);
        return this;
    }
    public List<Component> getList(){
        return GettingList;
    }
    public MessageComponent setMessage(Component newMessage){
        NewMessage = newMessage;
        return this;
    }
    //返回拼接好的完整组件
    public Component out(){
        TextComponent.Builder builder = text();
        for(Component getOne : GettingList){
            builder.append(getOne);
        }
        builder.append(NewMessage);

        return builder.build();
    }
}
