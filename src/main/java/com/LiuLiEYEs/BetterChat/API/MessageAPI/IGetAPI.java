package com.LiuLiEYEs.BetterChat.API.MessageAPI;

import com.LiuLiEYEs.BetterChat.API.Temp.MessageComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;

public interface IGetAPI extends LiuLIGet {
    MessageComponent GetAPIAndAddInToTheMessage(ParameterList temp,MessageComponent messageComponent);
}
