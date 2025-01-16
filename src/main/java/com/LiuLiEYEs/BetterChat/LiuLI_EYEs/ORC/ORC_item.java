package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.OnReToComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ORC_item extends ORC {

    @Override
    public Component getCom(ParameterList l, String s) {
        ItemStack item;
        if ((item = l.getPlayer().getInventory().getItem(Integer.parseInt(s) - 1)) == null) {
            item = new ItemStack(Material.AIR);
        }
        return item.displayName().color(TextColor.color(0x44FA67));
    }

    @Override
    public String getMatchString() {
        return "\\[item[1-9]]";
    }

    @Override
    public void getComponent(ParameterList temp) {
        Pattern pat = Pattern.compile(getMatchString());
        Matcher m = pat.matcher(temp.getVar());
        while (m.find()) {
            temp.add(m.start(),m.end(),getCom(temp,temp.getVar().substring(m.start() +"[item".length(), m.start() + "[itemN".length())));
        }
    }

    @Override
    public String getAPIName() {
        return "[item1-9]";
    }



    @Override
    public Component getDescription() {
        return Component.text("获取玩家物品栏对应物品");
    }
}
