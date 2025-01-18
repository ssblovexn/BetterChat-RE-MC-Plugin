package com.LiuLiEYEs.BetterChat.API.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {
    public static Component colorInHex(String s) {
        Pattern pattern = Pattern.compile("#[0-9a-fA-F]{6}");
        Matcher matcher = pattern.matcher(s);
        Component component = Component.text("");
        int Index = 0;
        TextColor temp = TextColor.color(0xFFFFFF);
        while (matcher.find()) {
            component = component.append(Component.text(s.substring(Index, matcher.start())).color(temp));
            temp = TextColor.fromHexString(s.substring(matcher.start(), matcher.end()));
            Index = matcher.end();
        }
        return component.append(Component.text(s.substring(Index)).color(temp));
    }
}
