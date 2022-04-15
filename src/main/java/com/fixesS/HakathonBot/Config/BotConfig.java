package com.fixesS.HakathonBot.Config;

import com.fixesS.HakathonBot.Bot.Bot;
import com.fixesS.HakathonBot.Controller.WebhooksController;
import com.fixesS.HakathonBot.DataBase.Services.CategoryService;
import com.fixesS.HakathonBot.DataBase.Services.MachineService;
import com.fixesS.HakathonBot.DataBase.Services.ManagerService;
import com.fixesS.HakathonBot.DataBase.Services.UserService;
import com.fixesS.HakathonBot.Main;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class BotConfig {

    @Bean
    public Main main(){
        return new Main(bot(),userService(),machineService(),categoryService(),managerService());
    }

    @Bean
    public Bot bot(){
        return new Bot(userService(),machineService(),categoryService(),managerService());
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public MachineService machineService(){
        return new MachineService();
    }

    @Bean
    public CategoryService categoryService(){
        return new CategoryService();
    }

    @Bean
    public ManagerService managerService(){
        return new ManagerService();
    }

//    @Bean
//    public WebhooksController webhooksController(){
//        return new WebhooksController();
//    }

}
