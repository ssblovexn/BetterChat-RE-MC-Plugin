package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ORC;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.IReToComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import net.kyori.adventure.text.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ORC implements IReToComponent {

    public abstract Component getCom(ParameterList l,String s);
    public abstract String getMatchString();
    @Override
    public void getComponent(ParameterList temp) {
        Pattern pat = Pattern.compile(getMatchString());
        Matcher m = pat.matcher(temp.getVar());
        while (m.find()) {
            temp.add(m.start(),m.end(),getCom(temp,temp.getVar().substring(m.start(),m.end())));
        }
    }
}
