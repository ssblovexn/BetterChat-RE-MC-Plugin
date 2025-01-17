package com.LiuLiEYEs.BetterChat.API.CommandAPI.U;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigLoaderCommand {
    String CommandName();
    String TabFunction();
    String ExecuteFunction();
}
