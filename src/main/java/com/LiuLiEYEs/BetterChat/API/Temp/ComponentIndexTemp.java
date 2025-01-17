package com.LiuLiEYEs.BetterChat.API.Temp;

import net.kyori.adventure.text.Component;

public class ComponentIndexTemp {
    public final int BeginIndex;
    public final int EndIndex;
    public final Component component;

    //工厂构建
    private ComponentIndexTemp(int beginIndex,int endIndex,Component component){
        this.BeginIndex =beginIndex;
        this.EndIndex =endIndex;
        this.component = component;
    }
    public static ComponentIndexTemp build(int beginIndex,int endIndex,Component component){
        return new ComponentIndexTemp(beginIndex,endIndex,component);
    }
}
