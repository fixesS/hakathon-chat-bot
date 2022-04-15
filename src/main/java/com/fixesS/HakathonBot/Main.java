package com.fixesS.HakathonBot;

import com.fixesS.HakathonBot.Bot.Bot;
import com.fixesS.HakathonBot.DataBase.Entities.Machine;
import com.fixesS.HakathonBot.DataBase.Entities.User;
import com.fixesS.HakathonBot.DataBase.Services.CategoryService;
import com.fixesS.HakathonBot.DataBase.Services.MachineService;
import com.fixesS.HakathonBot.DataBase.Services.ManagerService;
import com.fixesS.HakathonBot.DataBase.Services.UserService;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.util.List;

public class Main {

    private Bot hackathonBot;
    private UserService userService;
    private MachineService machineService;
    private CategoryService categoryService;
    private ManagerService managerService;

    public Main(Bot hackathonBot,UserService userService,MachineService machineService,CategoryService categoryService,ManagerService managerService ){
        this.hackathonBot = hackathonBot;
        this.userService = userService;
        this.machineService = machineService;
        this.categoryService = categoryService;
        this.managerService = managerService;
    }
    public void test(){
        User user = new User();
        user.setChatId(Long.parseLong("1212"));
        user.setStateId(600000);
        userService.createUser(user);
    }
    public void botStart(){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(hackathonBot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void Init(){
        botStart();
    }
}
