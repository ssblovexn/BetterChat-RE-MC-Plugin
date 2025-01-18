package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.GetAPI;

import com.LiuLiEYEs.BetterChat.API.CommandAPI.U.ConfigLoaderCommand;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.IGetAPI;
import com.LiuLiEYEs.BetterChat.API.Save.ISave;
import com.LiuLiEYEs.BetterChat.API.Temp.MessageComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import com.LiuLiEYEs.BetterChat.API.Utils.Color;
import com.LiuLiEYEs.BetterChat.API.Utils.Replacing;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ConfigLoaderCommand(CommandName = "GetAPI.GetName", TabFunction = "onTabComplete", ExecuteFunction = "SetCommandExecute")
public class GetName implements IGetAPI, ISave {
    static List<String> format;
    static String Click;
    static String Hover;
    private Component NameEvent(ParameterList t){
        String c= Replacing.get(t,Click);
        Component h= Color.colorInHex(Replacing.get(t,Hover));
        Component p=Color.colorInHex(Replacing.get(t,format.getFirst()+t.getPlayer().getName()+format.getLast()));
        return p.clickEvent(ClickEvent.runCommand(c)).hoverEvent(HoverEvent.showText(h));
    }

    //指令->执行 写入配置文件 /lbc config[0] GetAPI.GetName[1] [2] [3]
    public boolean SetCommandExecute(CommandSender sender, Command command, String label, String @NotNull [] args) {
        if (args.length > 3) {
            if (args[2].equals("click")) {
                StringBuilder temp = new StringBuilder();
                for(int i =3;i<args.length;i++){
                    temp.append(args[i]);
                    temp.append(" ");
                }

                Click = temp.substring(0,temp.length()-1);
                sender.sendMessage(Component.text("操作完成").color(TextColor.color(0x00FF00)));
                return true;
            }
            if (args[2].equals("hover")) {
                StringBuilder temp = new StringBuilder();
                for(int i =3;i<args.length;i++){
                    temp.append(args[i]);
                    temp.append(" ");
                }
                Hover = temp.substring(0,temp.length()-1);
                sender.sendMessage(Component.text("操作完成").color(TextColor.color(0x00FF00)));
                return true;
            }
            if (args[2].equals("format")||args.length>4) {
                format = List.of(args[3].split("\\|"));
                format= new ArrayList<>(Arrays.asList(args).subList(3, args.length));
                sender.sendMessage(Component.text("操作完成").color(TextColor.color(0x00FF00)));
                return true;
            }else {
                sender.sendMessage("§c参数错误！");
            }
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String @NotNull [] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 3) {
            list.add("format");
            list.add("click");
            list.add("hover");
        }
        if (args.length >3) {
            if (args[2].equals("format")) {
                list.add("< >");
            }
            if (args[2].equals("click")) {
                list.add("/tpa [PlayerName]");
            }
            if (args[2].equals("hover")) {
                list.add("#38cfff点#48d2f0我#58d5e0传#68d8d1送#77dbc2到 #6361ea[PlayerName] #87deb2位#97e1a3置");
            }
        }
        return list;
    }


    @Override
    public MessageComponent GetAPIAndAddInToTheMessage(ParameterList temp, MessageComponent messageComponent) {
        messageComponent.add(NameEvent(temp));
        return messageComponent;
    }

    @Override
    public String getAPIName() {
        return "GetName";
    }

    @Override
    public Component getDescription() {
        return Component.text("获取玩家名称");
    }

    @Override
    public void Save(FileConfiguration config) {
        config.set("GetName.format", format);
        config.set("GetName.click", Click);
        config.set("GetName.hover", Hover);
    }

    @Override
    public void Load(FileConfiguration config) {
        format = config.getStringList("GetName.format");
        Click = config.getString("GetName.click");
        Hover = config.getString("GetName.hover");
    }
}
