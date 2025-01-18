package com.LiuLiEYEs.BetterChat.API.Utils;

import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import com.handy.playertitle.api.PlayerTitleApi;
import org.bukkit.entity.Player;

public class Replacing {
    static public String de="#6b696b萌新";
    static public boolean isPlayerTitleOpen=false;
    static public String get(ParameterList list, String s){
        Player p =list.getPlayer();
        if (s==null){
            return " ";
        }
        String temp=s.replace("[PlayerName]",p.getName());
        if(isPlayerTitleOpen){
            if (PlayerTitleApi.getInstance().findByPlayerUuidAndIsUse(p)!=null){
                temp=temp.replace("[Title]", PlayerTitleApi.getInstance().findByPlayerUuidAndIsUse(p).getTitleName());
            }else{
                temp=temp.replace("[Title]","#FFFFFF[ "+de+" #FFFFFF]");
            }
        }
        return temp;
    }
}
