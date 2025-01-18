package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC;

import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;

public class ORC_Web extends ORC{
    @Override
    public Component getCom(ParameterList l,String s) {
        return Component.text("[网页链接]").color(TextColor.color(0xFA9DE3)).hoverEvent(HoverEvent.showText(Component.text("打开下方链接(注意分辨)\n"+s))).clickEvent(ClickEvent.openUrl(s));
    }

    @Override
    public String getMatchString() {
        return "(https|ftp|http)://[A-Za-z0-9./#%?=&-]+";
    }

    @Override
    public String getAPIName() {
        return "[网页链接]";
    }

    @Override
    public Component getDescription() {
        return Component.text("支持在聊天框中直接输入网址，后可直接加中文正常聊天，若英文则需要空格");
    }
}
