package com.fixesS.HakathonBot.Bot;

import com.fixesS.HakathonBot.DataBase.Entities.User;
import com.fixesS.HakathonBot.DataBase.Services.CategoryService;
import com.fixesS.HakathonBot.DataBase.Services.MachineService;
import com.fixesS.HakathonBot.DataBase.Services.ManagerService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;


public class BotContext {
    private Bot bot;
    private User user;
    private MachineService machineService;
    private CategoryService categoryService;
    private ManagerService managerService;
    private Message message;
    private CallbackQuery callbackQuery;
    private Integer lastMessageId;

    public Integer getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Integer lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public BotContext(Bot bot,
                      User user,
                      MachineService machineService,
                      CategoryService categoryService,
                      ManagerService managerService,
                      Message message,
                      CallbackQuery callbackQuery){
        this.bot = bot;
        this.user = user;
        this.machineService = machineService;
        this.categoryService = categoryService;
        this.managerService = managerService;
        this.message = message;
        this.callbackQuery = callbackQuery;
    }

    public Bot getBot() {
        return bot;
    }

    public User getUser() {
        return user;
    }

    public MachineService getMachineService() {
        return machineService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public ManagerService getManagerService() {
        return managerService;
    }

    public Message getMessage() {
        return message;
    }
    public CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }
}
