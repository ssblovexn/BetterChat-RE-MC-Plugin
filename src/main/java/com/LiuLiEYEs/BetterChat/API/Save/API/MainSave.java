package com.LiuLiEYEs.BetterChat.API.Save.API;

import com.LiuLiEYEs.BetterChat.API.Save.ISave;
import com.LiuLiEYEs.BetterChat.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainSave {
    static List<ISave> saves = new ArrayList<>();
    static FileConfiguration config;
    static public void addSave(ISave save) {
        saves.add(save);
    }
    static public void SaveALL(){
        for(ISave s : saves){
            s.Save(config);
        }
        Main.getPlugin(Main.class).saveConfig();
    }
    static public void LoadALL(){
        config = Main.getPlugin(Main.class).getConfig();
        for(ISave s : saves){
            s.Load(config);
        }
    }
}
