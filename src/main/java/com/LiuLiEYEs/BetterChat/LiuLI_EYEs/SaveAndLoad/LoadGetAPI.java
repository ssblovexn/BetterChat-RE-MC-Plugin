package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.SaveAndLoad;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.APIModeEmu;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.ChatMainClass;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.IGetAPI;
import com.LiuLiEYEs.BetterChat.API.Save.ISave;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class LoadGetAPI implements ISave {
    @Override
    public void Save(FileConfiguration config) {
        List<String> res = new ArrayList<>();
        for(IGetAPI api: ChatMainClass.getAPIMode(true)){
            res.add(api.getAPIName());
        }
        config.set("Save.open.GetAPI",res);
    }

    @Override
    public void Load(FileConfiguration config) {
        List<String> res = config.getStringList("Save.open.GetAPI");
        for(String api:res){
            ChatMainClass.turnAPIMode(APIModeEmu.GetAPI,api,true);
        }
    }
}
