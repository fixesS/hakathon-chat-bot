package com.fixesS.HakathonBot.Bot;

import com.fixesS.HakathonBot.DataBase.Entities.User;
import com.fixesS.HakathonBot.DataBase.Services.CategoryService;
import com.fixesS.HakathonBot.DataBase.Services.MachineService;
import com.fixesS.HakathonBot.DataBase.Services.ManagerService;
import com.fixesS.HakathonBot.DataBase.Services.UserService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    private UserService userService;
    private MachineService machineService;
    private CategoryService categoryService;
    private ManagerService managerService;

    public Bot(UserService userService,MachineService machineService,CategoryService categoryService,ManagerService managerService ){
        this.userService = userService;
        this.machineService = machineService;
        this.categoryService = categoryService;
        this.managerService = managerService;
    }

    @Override
    public String getBotUsername() {
        return "pilot_plant_bot";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            handleMessage(update);
        }else if(!update.hasMessage() && update.hasCallbackQuery()){
            handleCallBackQuery(update);
        }
        LOGGER.info("Update has received");
    }
    public void handleCallBackQuery(Update update){
        final Message message = update.getCallbackQuery().getMessage();
        final String text = message.getText();
        final long chatId = update.getCallbackQuery().getMessage().getChatId();
        final CallbackQuery callbackQuery = update.getCallbackQuery();

        User user = userService.findByChatId(chatId);

        BotContext context;
        BotState state;

        if(user == null){
            state = BotState.getInitialState();

            user = new User();
            user.setChatId(chatId);
            user.setStateId(state.ordinal());

            LOGGER.info("New user {"+user.getChatId().toString()+"} has registered");

            context = new BotContext(this, user,machineService,categoryService,managerService,message,callbackQuery);
            state.enter(context);


        }else{
            state = BotState.byId(user.getStateId());
            context = new BotContext(this, user,machineService,categoryService,managerService,message,callbackQuery);
            context.setLastMessageId(user.getLastMessageId());

        }
        state.handleCallBack(context);
        state.handleInput(context);

        do {
            state = state.nextState();
            state.enter(context);
        } while (!state.isInputNeeded());

        user.setStateId(state.ordinal());
        user.setLastMessageId(context.getLastMessageId());
        userService.updateUser(user);
    }
    public void handleMessage(Update update){

        final Message message = update.getMessage();
        final String text = message.getText();
        final long chatId = message.getChatId();
        final CallbackQuery callbackQuery = update.getCallbackQuery();

        User user = userService.findByChatId(chatId);

        BotContext context;
        BotState state;

        if(user == null){
            state = BotState.getInitialState();

            user = new User();
            user.setChatId(chatId);
            user.setStateId(state.ordinal());

            LOGGER.info("New user {"+user.getChatId().toString()+"} has registered");

            context = new BotContext(this, user,machineService,categoryService,managerService,message,callbackQuery);
            state.enter(context);


        }else{
            state = BotState.byId(user.getStateId());
            context = new BotContext(this, user,machineService,categoryService,managerService,message,callbackQuery);
            context.setLastMessageId(user.getLastMessageId());

        }
        state.handleInput(context);


        do {
            state = state.nextState();
            state.enter(context);
        } while (!state.isInputNeeded());

        user.setStateId(state.ordinal());
        user.setLastMessageId(context.getLastMessageId());
        userService.updateUser(user);
    }
}
