package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.SaveAndLoad;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.APIModeEmu;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.ChatMainClass;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.IReToComponent;
import com.LiuLiEYEs.BetterChat.API.Save.ISave;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class LoadReAPI implements ISave {
    @Override
    public void Save(FileConfiguration config) {
        List<String> res = new ArrayList<>();
        for(IReToComponent api:ChatMainClass.OnReMode(true)){
            res.add(api.getAPIName());
        }
        config.set("Save.open.OnReAPI",res);
    }

    @Override
    public void Load(FileConfiguration config) {
        List<String> res = config.getStringList("Save.open.OnReAPI");
        for(String api:res){
            ChatMainClass.turnAPIMode(APIModeEmu.OnReAPI,api,true);
        }
    }
}
