package com.LiuLiEYEs.BetterChat.API.Temp;

import net.kyori.adventure.text.Component;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParameterList {
    //记录每个扩展对应的更改位置，便于更改样式
    List<ComponentIndexTemp> list=new ArrayList<>();
    //记录玩家
    Player player;
    String var;
    public ParameterList(Player player,String var) {
        this.player=player;
        this.var=var;
    }
    //add
    public void add(ComponentIndexTemp component) {
        list.add(component);
    }
    //简单实例的add方法
    public void add(int beginIndex, int endIndex, Component component) {
        list.add(ComponentIndexTemp.build(beginIndex,endIndex,component));
    }
    //返回不可更改的list
    public List<ComponentIndexTemp> getComponentList() {
        //排序然后返回，按照顺序排序
        list.sort(Comparator.comparingInt(o->o.BeginIndex));
        return new ArrayList<>(list);
    }
    //返回玩家(也许可更改)
    public Player getPlayer() {
        return player;
    }
    //返回server
    public Server getServer() {
        return player.getServer();
    }
    public String getVar() {
        return var;
    }
}
