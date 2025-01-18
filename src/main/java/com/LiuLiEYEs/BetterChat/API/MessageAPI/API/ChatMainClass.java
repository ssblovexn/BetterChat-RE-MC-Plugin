package com.LiuLiEYEs.BetterChat.API.MessageAPI.API;

import com.LiuLiEYEs.BetterChat.API.MessageAPI.IGetAPI;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.IMessageFormatAPI;
import com.LiuLiEYEs.BetterChat.API.MessageAPI.IReToComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.MessageComponent;
import com.LiuLiEYEs.BetterChat.API.Temp.ParameterList;
import com.LiuLiEYEs.BetterChat.LiuLI_EYEs.ChatFormatAPI.none;
import com.LiuLiEYEs.BetterChat.Main;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static net.kyori.adventure.text.Component.text;


public class ChatMainClass implements Listener {
    static Component KongGe=Component.text(" ");
    static Component KaiQiZhuangTai =Component.text("正在使用:").color(TextColor.color(0x44FA67)).appendNewline();
    static Component GuanBiZhuangTai =Component.text("未开启:").color(TextColor.color(0xFA826F)).appendNewline();
    static List<IMessageFormatAPI> AllYangShi = new ArrayList<>();
    static public IMessageFormatAPI usingMessageYangShiAPI=new none();
    static List<GetAPIInfo> getAPIs= new ArrayList<>();
    static List<IReInfo> onReToComponents = new ArrayList<>();


    MessageComponent PingJie(AsyncChatEvent e){
        ParameterList tempList=new ParameterList(e.getPlayer(),LegacyComponentSerializer.legacyAmpersand().serialize(e.message()));
        MessageComponent willReturn = new MessageComponent();
        //先进行OnRe的获取
        for(IReToComponent onRe: OnReMode(true)){
            onRe.getComponent(tempList);
        }

        //GetApi获取
        for (IGetAPI api:getAPIMode(true)){
            api.GetAPIAndAddInToTheMessage(tempList,willReturn);
        }
        //获取样式
        return usingMessageYangShiAPI.setMessage(willReturn,tempList);

    }

    //add列表，获取组件
    static public void add(IGetAPI api, Plugin p){
        getAPIs.add(new GetAPIInfo(api,p));
    }

    static public void add(IReToComponent api, Plugin p){
        onReToComponents.add(new IReInfo(api,p));
    }
    static public void add(IMessageFormatAPI api){
        AllYangShi.add(api);
    }

    //获取描述信息
    static public Component GetDescription(APIModeEmu mode){
        TextComponent.Builder builder = text().content("");
        builder.append(Component.text("-----------------").color(TextColor.color(0xFA9DE3)));
        if (mode== APIModeEmu.OnReAPI||mode== APIModeEmu.ALL){

            builder.append(Component.text("聊天插件组件:").color(TextColor.color(0xFA9DE3)).appendNewline());
            OnReDes(builder);
            builder.append(Component.text("-----------------").color(TextColor.color(0xFA9DE3)));
        }
        if (mode== APIModeEmu.GetAPI||mode== APIModeEmu.ALL){
            builder.append(Component.text("Get组插件插件:").color(TextColor.color(0xFA9DE3)).appendNewline());
            GetAPIDes(builder);
            builder.append(Component.text("-----------------").color(TextColor.color(0xFA9DE3)));
        }
        if (mode== APIModeEmu.YangShiAPI||mode== APIModeEmu.ALL){
            builder.append(Component.text("聊天样式插件:").color(TextColor.color(0xFA9DE3)).appendNewline());
            YangShiDes(builder);
            builder.append(Component.text("-----------------").color(TextColor.color(0xFA9DE3)));
        }
        return builder.build();
    }
    static private @NotNull String callAndSet(iInFo<?> needToOpenOrClose){
        if (needToOpenOrClose instanceof IReInfo i){
            if (i.Mode(true)){
                return "/lbc set OnReAPI "+i.API.getAPIName()+" false";
            }else{
                return "/lbc set OnReAPI "+i.API.getAPIName()+" true";
            }
        }
        if (needToOpenOrClose instanceof GetAPIInfo i){
            if (i.Mode(true)){
                return "/lbc set GetAPI "+i.API.getAPIName()+" false";
            }else{
                return "/lbc set GetAPI "+i.API.getAPIName()+" true";
            }
        }
        return "/";

    }
    static private TextComponent.Builder YangShiDes(TextComponent.Builder builder){
        //全部
        for (IMessageFormatAPI on:AllYangShi){
            builder.append(on.getAPINameToComponent()).append(KongGe).append(on.getDescription()).appendNewline();
        }
        builder.append(KaiQiZhuangTai);
        builder.append(usingMessageYangShiAPI.getAPINameToComponent()).append(KongGe).append(usingMessageYangShiAPI.getDescription()).appendNewline();
        return builder;
    }
    static private TextComponent.Builder GetAPIDes(TextComponent.Builder builder){
        builder.append(KaiQiZhuangTai);
        for (IGetAPI on: getAPIMode(true)){
            builder.append(on.getAPINameToComponent()).hoverEvent(HoverEvent.showText(Component.text("点我打开/关闭"))).clickEvent(ClickEvent.runCommand(callAndSet((iInFo<?>) on))).append(KongGe).append(on.getDescription()).appendNewline();
        }
        //关闭状态
        builder.append(GuanBiZhuangTai);
        for (IGetAPI on: getAPIMode(false)){
            builder.append(on.getAPINameToComponent()).hoverEvent(HoverEvent.showText(Component.text("点我打开/关闭"))).clickEvent(ClickEvent.runCommand(callAndSet((iInFo<?>) on))).append(KongGe).append(on.getDescription()).appendNewline();
        }
        return builder;
    }
    static private TextComponent.Builder OnReDes(TextComponent.Builder builder){
        //开启状态
        builder.append(KaiQiZhuangTai);
        for (IReToComponent on: OnReMode(true)){
            builder.append(on.getAPINameToComponent()).hoverEvent(HoverEvent.showText(Component.text("点我打开/关闭"))).clickEvent(ClickEvent.runCommand(callAndSet((iInFo<?>) on))).append(KongGe).append(on.getDescription()).appendNewline();
        }
        //关闭状态
        builder.append(GuanBiZhuangTai);
        for (IReToComponent on: OnReMode(false)){
            builder.append(on.getAPINameToComponent()).hoverEvent(HoverEvent.showText(Component.text("点我打开/关闭"))).clickEvent(ClickEvent.runCommand(callAndSet((iInFo<?>) on))).append(KongGe).append(on.getDescription()).appendNewline();
        }
        return builder;
    }

    //返回对应ID
    static public List<String> getAPIName(APIModeEmu mode) {
        List<String> list = new ArrayList<>();
        if (mode == APIModeEmu.GetAPI) {
            for (GetAPIInfo api: getAPIs){
                list.add(api.API.getAPIName());
            }
        }
        if (mode == APIModeEmu.YangShiAPI) {
            for (IMessageFormatAPI api: AllYangShi){
                list.add(api.getAPIName());
            }
        }
        if (mode == APIModeEmu.OnReAPI) {
            for (IReInfo api: onReToComponents){
                list.add(api.API.getAPIName());
            }
        }
        return list;
    }

    //查找对应ID并<>
    static public void turnAPIMode(APIModeEmu mode, String name, boolean is){
        if (mode == APIModeEmu.GetAPI){
            for (GetAPIInfo api: getAPIs){
                if (api.API.getAPIName().equalsIgnoreCase(name)){
                    api.setMode(is);
                }
            }
        }
        if (mode == APIModeEmu.YangShiAPI){
            for(IMessageFormatAPI api: AllYangShi){
                if (api.getAPIName().equalsIgnoreCase(name)){
                    usingMessageYangShiAPI =api;
                }
            }
        }
        if (mode== APIModeEmu.OnReAPI){
            for (IReInfo api: onReToComponents){
                if (api.API.getAPIName().equalsIgnoreCase(name)){
                    api.setMode(is);
                }
            }
        }
    }


    static public List<IGetAPI> getAPIMode(boolean is){
        List<IGetAPI> tempList=new ArrayList<>();
        for (GetAPIInfo info : getAPIs){
            if (info.Mode(is)){
                tempList.add(info.API);
            }
        }
        return tempList;
    }
    static public List<IReToComponent> OnReMode(boolean is){
        List<IReToComponent> tempList=new ArrayList<>();
        for (IReInfo info : onReToComponents){
            if (info.Mode(is)){
                tempList.add(info.API);
            }
        }
        return tempList;
    }
    //存储样式的开启状态
    static class FormatAPIInfo implements iInFo<IMessageFormatAPI> {
        public final IMessageFormatAPI API;
        public final Plugin plugin;
        private boolean isOpening = false;
        public FormatAPIInfo(IMessageFormatAPI api, Plugin plugin){
            this.API = api;
            this.plugin = plugin;
        }
        @Override
        //是否为isOpening的状态
        public boolean Mode(boolean isOpening){
            return this.isOpening == isOpening;
        }
        @Override
        //插件是否为某个插件
        public boolean Mode(String PluginName){
            return this.plugin.getName().equalsIgnoreCase(PluginName);
        }
        public String getPluginName(){
            return this.plugin.getName();
        }
        @Override
        public IMessageFormatAPI getApi() {
            return this.API;
        }
        @Override
        public void setMode(boolean isOpening) {
            this.isOpening = isOpening;
        }

    }
    //存储GetAPI的开启状态
    static class GetAPIInfo implements iInFo<IGetAPI>{
        public final IGetAPI API;
        public final Plugin plugin;
        private boolean isOpening = false;
        public GetAPIInfo(IGetAPI api, Plugin plugin){
            this.API = api;
            this.plugin = plugin;
        }
        @Override
        //是否为isOpening的状态
        public boolean Mode(boolean isOpening){
            return this.isOpening == isOpening;
        }
        @Override
        //插件是否为某个插件
        public boolean Mode(String PluginName){
            return this.plugin.getName().equalsIgnoreCase(PluginName);
        }
        public String getPluginName(){
            return this.plugin.getName();
        }
        @Override
        public IGetAPI getApi() {
            return this.API;
        }
        @Override
        public void setMode(boolean isOpening) {
            this.isOpening = isOpening;
        }

    }
    //存储OnReToComponent的开启状态
    static class IReInfo implements iInFo<IReToComponent>{
        public final IReToComponent API;
        public final Plugin plugin;
        private boolean isOpening = false;
        public IReInfo(IReToComponent api, Plugin plugin){
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

        @Override
        public IReToComponent getApi() {
            return this.API;
        }
        @Override
        public void setMode(boolean isOpening) {
            this.isOpening = isOpening;
        }
    }

    interface iInFo<T> {

        boolean Mode(boolean isOpening);
        boolean Mode(String PluginName);
        String getPluginName();
        T getApi();
        void setMode(boolean isOpening);
    }





    @EventHandler
    public void onChat(AsyncChatEvent event) {
        if (event.isCancelled()){
            return;
        }
        event.setCancelled(true);
        event.getPlayer().getServer().getGlobalRegionScheduler().run(Main.getPlugin(Main.class),task->event.getPlayer().getServer().sendMessage(PingJie(event).out()));
        //event.getPlayer().getServer().getGlobalRegionScheduler().run(Main.getPlugin(Main.class),task->event.getPlayer().getServer().sendMessage(GetDescription(APIModeEmu.ALL)));
    }
}
