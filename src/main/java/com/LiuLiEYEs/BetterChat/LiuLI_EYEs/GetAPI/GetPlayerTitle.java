package com.LiuLiEYEs.BetterChat.LiuLI_EYEs.GetAPI;

import com.LiuLiEYEs.BetterChat.API.CommandAPI.U.ConfigLoaderCommand;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.IGetAPI;
import com.LiuLiEYEs.BetterChat.API.Save.ISave;
import com.LiuLiEYEs.BetterChat.API.Temp.MessageComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import com.LiuLiEYEs.BetterChat.API.Utils.Color;
import com.LiuLiEYEs.BetterChat.API.Utils.Replacing;
import com.LiuLiEYEs.BetterChat.Main;
import com.handy.playertitle.api.PlayerTitleApi;
import com.handy.playertitle.entity.TitlePlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@ConfigLoaderCommand(CommandName = "GetAPI.GetPlayerTitle", TabFunction = "onTabComplete", ExecuteFunction = "SetCommandExecute")
public class GetPlayerTitle implements IGetAPI, ISave {
    private static String click;
    private static String hover;
    private static String defaultTitle;
    private static boolean isPltOpen=false;
    public GetPlayerTitle() {
        isPltOpen= Main.getPlugin(Main.class).getServer().getPluginManager().isPluginEnabled("PlayerTitle");
        Replacing.isPlayerTitleOpen =isPltOpen;
    }
    @Override
    public MessageComponent GetAPIAndAddInToTheMessage(ParameterList temp, MessageComponent messageComponent)
    {
        if(isPltOpen){
            Player playerHasTitle = temp.getPlayer();
            PlayerTitleApi pltAPI = PlayerTitleApi.getInstance();
            //获取plt部分
            //检查plt是否为为空
            TitlePlayer plt = pltAPI.findByPlayerUuidAndIsUse(playerHasTitle);
            Component res;
            //转换字符串将关键词转为对应玩意
            String c = Replacing.get(temp, click);
            String h = Replacing.get(temp, hover);
            if (plt != null) {
                //不为null，获取plt
                res = Color.colorInHex(plt.getTitleName()).hoverEvent(HoverEvent.showText(Color.colorInHex(h))).clickEvent(ClickEvent.runCommand(c));
            }else {
                //默认
                res = Color.colorInHex("[ " + defaultTitle+" #FFFFFF]").hoverEvent(HoverEvent.showText(Color.colorInHex(h))).clickEvent(ClickEvent.runCommand(c));
            }
            messageComponent.add(res);
        }
        return messageComponent;
    }

    @Override
    public String getAPIName() {
        return "GetPlayerTitle";
    }

    @Override
    public Component getDescription() {
        return Component.text("兼容称号模组使用的API，使称号正常显示(并添加点击效果)，没有装称号插件请不要使用").color(TextColor.color(0xFA826F));
    }

    @Override
    public void Save(FileConfiguration config) {
        config.set("GetPlayerTitle.Click", click);
        config.set("GetPlayerTitle.Hover", hover);
        config.set("GetPlayerTitle.DefaultTitle", defaultTitle);
    }

    @Override
    public void Load(FileConfiguration config) {
        hover=config.getString("GetPlayerTitle.Hover");
        defaultTitle = config.getString("GetPlayerTitle.DefaultTitle");
        click=config.getString("GetPlayerTitle.Click");
        Replacing.de=defaultTitle;
    }

    public boolean SetCommandExecute(CommandSender sender, Command command, String label, String @NotNull [] args) {
        if (args.length > 3) {
            if (args[2].equals("click")) {
                StringBuilder temp = new StringBuilder();
                for(int i =3;i<args.length;i++){
                    temp.append(args[i]);
                    temp.append(" ");
                }

                click = temp.substring(0,temp.length()-1);
                sender.sendMessage(Component.text("操作完成").color(TextColor.color(0x00FF00)));
                return true;
            }
            if (args[2].equals("hover")) {
                StringBuilder temp = new StringBuilder();
                for(int i =3;i<args.length;i++){
                    temp.append(args[i]);
                    temp.append(" ");
                }
                hover = temp.substring(0,temp.length()-1);
                sender.sendMessage(Component.text("操作完成").color(TextColor.color(0x00FF00)));
                return true;
            }
            if (args[2].equals("defaultTitle")) {
                StringBuilder temp = new StringBuilder();
                for(int i =3;i<args.length;i++){
                    temp.append(args[i]);
                    temp.append(" ");
                }
                defaultTitle = temp.substring(0,temp.length()-1);
                Replacing.de=defaultTitle;
                sender.sendMessage(Component.text("操作完成").color(TextColor.color(0x00FF00)));
                return true;
            }
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String @NotNull [] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 3) {
            list.add("defaultTitle");
            list.add("click");
            list.add("hover");
        }
        if (args.length >3) {
            if (args[2].equals("defaultTitle")) {
                list.add("#AAAAAA萌新");
            }
            if (args[2].equals("click")) {
                list.add("/plt open");
            }
            if (args[2].equals("hover")) {
                list.add("#d9afd9点击打开称号背包");
            }
        }
        return list;
    }
}
