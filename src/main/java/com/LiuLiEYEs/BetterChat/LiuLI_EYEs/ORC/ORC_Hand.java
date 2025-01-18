package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC;

import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class ORC_Hand extends ORC{
    @Override
    public Component getCom(ParameterList l,String s) {
        return l.getPlayer().getInventory().getItemInMainHand().displayName();
    }

    @Override
    public String getMatchString() {
        return "\\[hand]";
    }

    @Override
    public String getAPIName() {
        return "[hand]";
    }
    @Override
    public Component getAPINameToComponent(){
        return Component.text("[hand]").color(TextColor.color(0xBA76F4));
    }
    @Override
    public Component getDescription() {
        return Component.text("展示手中的物品");
    }
}
