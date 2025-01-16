package com.LiuLiEYEs.BetterChat.API.API;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.GetAPI;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.LiuLIDescription;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.MessageYangShiAPI;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.OnReToComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ComponentIndexTemp;
import com.LiuLiEYEs.BetterChat.API.Temp.MessageComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.YangShi.none;
import com.LiuLiEYEs.BetterChat.Main;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

import static net.kyori.adventure.text.Component.text;


public class ChatMainClass implements Listener {
    static Component KongGe=Component.text(" ");
    static Component KaiQiZhuangTai =Component.text("正在使用:").color(TextColor.color(0x44FA67)).appendNewline();
    static Component GuanBiZhuangTai =Component.text("未开启:").color(TextColor.color(0xFA826F)).appendNewline();
    static List<MessageYangShiAPI> AllYangShi = new ArrayList<>();
    static MessageYangShiAPI usingMessageYangShiAPI=new none();
    static List<GetAPIInfo> getAPIs= new ArrayList<>();
    static List<OnReInfo> onReToComponents = new ArrayList<>();
    MessageComponent PingJie(AsyncChatEvent e){
        ParameterList tempList=new ParameterList(e.getPlayer(),LegacyComponentSerializer.legacyAmpersand().serialize(e.message()));
        MessageComponent willReturn = new MessageComponent();
        //先进行OnRe的获取
        for(OnReToComponent onRe: OnReMode(true)){
            onRe.getComponent(tempList);
        }

        //GetApi获取
        for (GetAPI api:getAPIMode(true)){
            api.GetAPIAndAddInToTheMessage(tempList,willReturn);
        }
        //获取样式
        return usingMessageYangShiAPI.setMessage(willReturn,tempList);

    }
    //
    //
    //
    //add列表，获取组件
    public void add(GetAPI api,Plugin p){
        getAPIs.add(new GetAPIInfo(api,p));
    }
    public void add(OnReToComponent api,Plugin p){
        onReToComponents.add(new OnReInfo(api,p));
    }
    public void add(MessageYangShiAPI api){
        AllYangShi.add(api);
    }

    //
    //
    //获取描述信息
    public Component GetDescription(GetDesMode mode){
        TextComponent.Builder builder = text().content("");
        if (mode==GetDesMode.OnReAPI||mode==GetDesMode.ALL){
            builder.append(Component.text("聊天插件组件:").color(TextColor.color(0xFA9DE3)).appendNewline()).appendNewline();
            OnReDes(builder);
            builder.append(Component.text("-----------------").color(TextColor.color(0xFA9DE3)));
        }
        if (mode==GetDesMode.GetAPI||mode==GetDesMode.ALL){
            builder.append(Component.text("Get组插件插件:").color(TextColor.color(0xFA9DE3)).appendNewline()).appendNewline();
            GetAPIDes(builder);
            builder.append(Component.text("-----------------").color(TextColor.color(0xFA9DE3)));
        }
        if (mode==GetDesMode.YangShiAPI||mode==GetDesMode.ALL){
            builder.append(Component.text("聊天样式插件:").color(TextColor.color(0xFA9DE3)).appendNewline()).appendNewline();
            YangShiDes(builder);
            builder.append(Component.text("-----------------").color(TextColor.color(0xFA9DE3)));
        }
        return builder.build();
    }
    private TextComponent.Builder YangShiDes(TextComponent.Builder builder){
        //全部
        for (MessageYangShiAPI on:AllYangShi){
            builder.append(on.getAPINameToComponent()).append(KongGe).append(on.getDescription()).appendNewline();
        }
        builder.append(KaiQiZhuangTai);
        builder.append(usingMessageYangShiAPI.getAPINameToComponent()).append(KongGe).append(usingMessageYangShiAPI.getDescription()).appendNewline();
        return builder;
    }
    private TextComponent.Builder GetAPIDes(TextComponent.Builder builder){
        builder.append(KaiQiZhuangTai);
        for (GetAPI on: getAPIMode(true)){
            builder.append(on.getAPINameToComponent()).append(KongGe).append(on.getDescription()).appendNewline();
        }
        //关闭状态
        builder.append(GuanBiZhuangTai);
        for (GetAPI on: getAPIMode(false)){
            builder.append(on.getAPINameToComponent()).append(KongGe).append(on.getDescription()).appendNewline();
        }
        return builder;
    }
    private TextComponent.Builder OnReDes(TextComponent.Builder builder){
        //开启状态
        builder.append(KaiQiZhuangTai);
        for (OnReToComponent on: OnReMode(true)){
            builder.append(on.getAPINameToComponent()).append(KongGe).append(on.getDescription()).appendNewline();
        }
        //关闭状态
        builder.append(GuanBiZhuangTai);
        for (OnReToComponent on: OnReMode(false)){
            builder.append(on.getAPINameToComponent()).append(KongGe).append(on.getDescription()).appendNewline();
        }
        return builder;
    }
    //
    //
    //
    //返回对应ID
    public List<String> getAPIName(GetDesMode mode) {
        List<String> list = new ArrayList<>();
        if (mode == GetDesMode.GetAPI) {
            for (GetAPIInfo api: getAPIs){
                list.add(api.API.getAPIName());
            }
        }
        if (mode == GetDesMode.YangShiAPI) {
            for (MessageYangShiAPI api: AllYangShi){
                list.add(api.getAPIName());
            }
        }
        if (mode == GetDesMode.OnReAPI) {
            for (OnReInfo api: onReToComponents){
                list.add(api.API.getAPIName());
            }
        }
        return list;
    }
    //查找对应ID并<>
    public void Turn(GetDesMode mode,String name,boolean is){
        if (mode == GetDesMode.GetAPI){
            for (GetAPIInfo api: getAPIs){
                if (api.API.getAPIName().equalsIgnoreCase(name)){
                    api.setOpenMode(is);
                }
            }
        }
        if (mode == GetDesMode.YangShiAPI){
            for(MessageYangShiAPI api: AllYangShi){
                if (api.getAPIName().equalsIgnoreCase(name)){
                    usingMessageYangShiAPI =api;
                }
            }
        }
        if (mode==GetDesMode.OnReAPI){
            for (OnReInfo api: onReToComponents){
                if (api.API.getAPIName().equalsIgnoreCase(name)){
                    api.setOpenMode(is);
                }
            }
        }
    }


    List<GetAPI> getAPIMode(boolean is){
        List<GetAPI> tempList=new ArrayList<>();
        for (GetAPIInfo info : getAPIs){
            if (info.Mode(is)){
                tempList.add(info.API);
            }
        }
        return tempList;
    }
    List<OnReToComponent> OnReMode(boolean is){
        List<OnReToComponent> tempList=new ArrayList<>();
        for (OnReInfo info : onReToComponents){
            if (info.Mode(is)){
                tempList.add(info.API);
            }
        }
        return tempList;
    }
    //存储GetAPI的开启状态
    static class GetAPIInfo{
        public final GetAPI API;
        public final Plugin plugin;
        private boolean isOpening = false;
        public void setOpenMode(boolean mode){
            this.isOpening = mode;
        }
        public GetAPIInfo(GetAPI api, Plugin plugin){
            this.API = api;
            this.plugin = plugin;
        }
        //是否为isOpening的状态
        public boolean Mode(boolean isOpening){
            return this.isOpening == isOpening;
        }
        //插件是否为某个插件
        public boolean Mode(String PluginName){
            return this.plugin.getName().equalsIgnoreCase(PluginName);
        }
        public String getPluginName(){
            return this.plugin.getName();
        }

    }
    //存储OnReToComponent的开启状态
    static class OnReInfo{
        public final OnReToComponent API;
        public final Plugin plugin;
        private boolean isOpening = false;
        public void setOpenMode(boolean mode){
            this.isOpening = mode;
        }
        public OnReInfo(OnReToComponent api, Plugin plugin){
            this.API = api;
            this.plugin = plugin;
        }
        //是否为isOpening的状态
        public boolean Mode(boolean isOpening){
            return this.isOpening == isOpening;
        }
        //插件是否为某个插件
        public boolean Mode(String PluginName){
            return this.plugin.getName().equalsIgnoreCase(PluginName);
        }
        public String getPluginName(){
            return this.plugin.getName();
        }
    }







    @EventHandler
    public void onChat(AsyncChatEvent event) {
        if (event.isCancelled()){
            return;
        }
        event.setCancelled(true);
        event.getPlayer().getServer().getGlobalRegionScheduler().run(Main.getPlugin(Main.class),task->event.getPlayer().getServer().sendMessage(PingJie(event).out()));
        //event.getPlayer().getServer().getGlobalRegionScheduler().run(Main.getPlugin(Main.class),task->event.getPlayer().getServer().sendMessage(GetDescription(GetDesMode.ALL)));
    }
}
