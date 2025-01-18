package com.LiuLiEYEs.BetterChat.API.CommandAPI.U;

import com.LiuLiEYEs.BetterChat.API.CommandAPI.ICommandAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AConfigLoaderCommand implements ICommandAPI {
    static private List<String> GetSecondTabAsConfigNeedLoader(){
        List<String> list = new ArrayList<>();
        //遍历classList
        for(Object clazz : classList) {
            if (clazz.getClass().isAnnotationPresent(ConfigLoaderCommand.class)) {
                list.add(clazz.getClass().getAnnotation(ConfigLoaderCommand.class).CommandName());
            }
        }
        return list;
    }
    //获取TabList的方法
    static private List<String> GetTabList(CommandSender sender, Command command, String label, String[] args) throws InvocationTargetException, IllegalAccessException {
        //创建将要返回的List
        List<String> list = null;
        //遍历classList
        for(Object clazz : classList){
            if(clazz.getClass().isAnnotationPresent(ConfigLoaderCommand.class)){
                //获取标注的函数名
                if (!clazz.getClass().getAnnotation(ConfigLoaderCommand.class).CommandName().equalsIgnoreCase(args[1])){
                    continue;
                }
                String functionName = clazz.getClass().getAnnotation(ConfigLoaderCommand.class).TabFunction();
                for(Method method : clazz.getClass().getDeclaredMethods()){
                    //查找函数并调用
                    if(method.getName().equalsIgnoreCase(functionName)){
                        list = (List<String>) method.invoke(clazz, sender, command, label, args);
                    }
                }

            }
        }
        return list;
    }
    static private boolean Execute(CommandSender sender, Command command, String label, String[] args) throws InvocationTargetException, IllegalAccessException {
        //创建将要返回的List
        //遍历classList
        for(Object clazz : classList){
            if(clazz.getClass().isAnnotationPresent(ConfigLoaderCommand.class)){
                if (!clazz.getClass().getAnnotation(ConfigLoaderCommand.class).CommandName().equalsIgnoreCase(args[1])){
                    continue;
                }
                //获取标注的函数名
                String functionName = clazz.getClass().getAnnotation(ConfigLoaderCommand.class).ExecuteFunction();
                for(Method method : clazz.getClass().getDeclaredMethods()){
                    //查找函数并调用
                    if(method.getName().equalsIgnoreCase(functionName)){
                        return (boolean) method.invoke(clazz, sender, command, label, args);
                    }
                }

            }
        }
        return false;
    }

    static List<Object> classList = new ArrayList<>();
    @Override
    public String getCommandAPIName() {
        return "config";
    }
    public void add(Object a){
        classList.add(a);
    }
    @Override
    public List<String> getTabCommandList(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            return GetSecondTabAsConfigNeedLoader();
            //return CommandTabList.GetSecondTabAsConfigNeedLoader();
        } else {
            try {
                return GetTabList(sender, command, label, args);
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public boolean MainCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            return Execute(sender, command, label, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
