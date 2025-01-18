package com.LiuLiEYEs.BetterChat.API.MessageAPI;

import net.kyori.adventure.text.Component;

public interface LiuLIGet {
    String getAPIName();
    default Component getAPINameToComponent(){
        return Component.text(getAPIName());
    }
    Component getDescription();
}
