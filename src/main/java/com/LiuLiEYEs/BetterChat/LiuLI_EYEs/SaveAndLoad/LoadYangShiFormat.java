package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.SaveAndLoad;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.APIModeEmu;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.API.ChatMainClass;
import com.LiuLiEYEs.BetterChat.API.Save.ISave;
import org.bukkit.configuration.file.FileConfiguration;

public class LoadYangShiFormat implements ISave{
    @Override
    public void Save(FileConfiguration config) {
        config.set("Save.open.Format",ChatMainClass.usingMessageYangShiAPI.getAPIName());
    }
    @Override
    public void Load(FileConfiguration config) {
        String format = config.getString("Save.open.Format");
        ChatMainClass.turnAPIMode(APIModeEmu.YangShiAPI,format,true);
    }
}
