package com.LiuLiEYEs.BetterChat.API.Save;

import org.bukkit.configuration.file.FileConfiguration;

public interface ISave {
    void Save(FileConfiguration config);
    void Load(FileConfiguration config);
}
