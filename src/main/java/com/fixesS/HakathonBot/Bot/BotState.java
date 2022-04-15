package com.fixesS.HakathonBot.Bot;

import com.fixesS.HakathonBot.DataBase.Entities.Machine;
import com.fixesS.HakathonBot.DataBase.Entities.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageId;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.crypto.Mac;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum BotState {

    Start(false){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Приветствую!");

            sendMessage(context,text);
            next = StartMenu;
        }


        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    StartMenu(true){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Чем я могу вам помочь?");

            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow firstKeyboardRow = new KeyboardRow();
            KeyboardRow secondKeyboardRow = new KeyboardRow();
            //...
            KeyboardRow finalKeyboardRow = new KeyboardRow();

            firstKeyboardRow.add("Показать каталог");
            secondKeyboardRow.add("Помочь подобрать");
            //...
            finalKeyboardRow.add("Консультация со специалистом");

            keyboard.add(firstKeyboardRow);
            keyboard.add(secondKeyboardRow);
            //...
            keyboard.add(finalKeyboardRow);

            sendMessageWithKeyboard(context,text,keyboard).getMessageId();

        }

        @Override
        public void handleInput(BotContext context){
            String input = context.getMessage().getText();

            if(input.equals("Показать каталог")){
                next = CatalogMenu;
            }else if(input.equals("Помочь подобрать")){
                next = HelpWithTheSelectionMenu;
            }else if(input.equals("Консультация со специалистом")){
                next = ThreeManager;
            }else{
                next = MainMenu;
            }

        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    MainMenu(true){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Чем еще я могу вам помочь?");

            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow firstKeyboardRow = new KeyboardRow();
            KeyboardRow secondKeyboardRow = new KeyboardRow();
            //...
            KeyboardRow finalKeyboardRow = new KeyboardRow();

            firstKeyboardRow.add("Показать каталог");
            secondKeyboardRow.add("Помочь подобрать");
            //...
            finalKeyboardRow.add("Консультация со специалистом");

            keyboard.add(firstKeyboardRow);
            keyboard.add(secondKeyboardRow);
            //...
            keyboard.add(finalKeyboardRow);

            messageId = sendMessageWithKeyboard(context,text,keyboard).getMessageId();
            context.setLastMessageId(messageId);
        }

        @Override
        public void handleInput(BotContext context){
            String input = context.getMessage().getText();
            if(input.equals("Показать каталог")){
                next = CatalogMenu;
            }else if(input.equals("Помочь подобрать")){
                next = HelpWithTheSelectionMenu;
            }else if(input.equals("Консультация со специалистом")){
                next = ThreeManager;
            }else{
                next = MainMenu;
            }
            deleteMessage(context,context.getLastMessageId());
        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    CatalogMenu(true){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Показываю...");

            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow firstKeyboardRow = new KeyboardRow();
            KeyboardRow secondKeyboardRow = new KeyboardRow();
            KeyboardRow thirdKeyboardRow = new KeyboardRow();
            KeyboardRow fourthKeyboardRow = new KeyboardRow();
            //...
            KeyboardRow finalKeyboardRow = new KeyboardRow();

            firstKeyboardRow.add("Оборудование для добычи камня");
            secondKeyboardRow.add("Оборудование для оброботки камня");
            thirdKeyboardRow.add("Станки для ремонта грузовых вагонов");
            fourthKeyboardRow.add("Нестандартное оборудование");
            //...
            finalKeyboardRow.add("Назад");

            keyboard.add(firstKeyboardRow);
            keyboard.add(secondKeyboardRow);
            keyboard.add(thirdKeyboardRow);
            keyboard.add(fourthKeyboardRow);
            //...
            keyboard.add(finalKeyboardRow);

            messageId = sendMessageWithKeyboard(context,text,keyboard).getMessageId();
            context.setLastMessageId(messageId);
        }

        @Override
        public void handleInput(BotContext context){

            String input = context.getMessage().getText();
            if(input.equals("Оборудование для добычи камня")){
                next = StoneMiningEquipment;
            }else if(input.equals("Оборудование для оброботки камня")){
                next = StoneProcessingEquipment;
            }else if(input.equals("Станки для ремонта грузовых вагонов")){
                next = RepairOfWagons;
            }else if(input.equals("Нестандартное оборудование")){
                next = NonstandardEquipment;
            }else if(input.equals("Назад")){
                next = MainMenu;
            }else{
                next = CatalogMenu;
            }
            deleteMessage(context,context.getLastMessageId());
        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    HelpWithTheSelectionMenu(true){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Вы можете подобрать станок по ключевым словам:");

            messageId = sendMessage(context,text).getMessageId();//todo поиск по ключевым словам !!!!!!!!!!!!!!!!!!!!!!!!!
            context.setLastMessageId(messageId);
        }

        @Override
        public void handleInput(BotContext context) {
            String input = context.getMessage().getText();
            String lowerInput = input.toLowerCase(Locale.ROOT);

            List<Machine> containsMachine = new ArrayList<>();

            List<Machine> machines = context.getMachineService().findAll();
            for(Machine machine: machines){
                String keywords = machine.getKeywords();
                String[] args = machine.getKeywords().split(",");
                List<String> list = Arrays.asList(args);

                if(list.contains(lowerInput)){
                    System.out.println("Есть!");
                    containsMachine.add(machine);
                }
            }
            next = HelpWithTheSelectionMenuCallBack;
            next.setContainsMachines(containsMachine);

            deleteMessage(context,context.getLastMessageId());
        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    HelpWithTheSelectionMenuCallBack(true,true){
        private List<Machine> machineList;
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
            if(!machineList.isEmpty()){
                String text = String.format("Вот что найдено по ключевым словам :");


                for(Machine machine : machineList){
                    InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
                    List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

                    keyboardButton.setText(machine.getName());
                    keyboardButton.setCallbackData(machine.getManagerId().toString()+"-"+machine.getMachineId().toString());

                    keyboardRow.add(keyboardButton);

                    keyboardButtons.add(keyboardRow);
                }
                InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
                List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

                keyboardButton.setText("Вернуться назад");
                keyboardButton.setCallbackData("back");

                keyboardRow.add(keyboardButton);

                keyboardButtons.add(keyboardRow);

                messageId = sendMessageWithInlineKeyboard(context,text,keyboardButtons).getMessageId();
                context.setLastMessageId(messageId);

            }else{
                String text = String.format("К сожалению, по ключевым словам ничего не найдено");

                InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
                List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

                keyboardButton.setText("Вернуться назад");
                keyboardButton.setCallbackData("back");

                keyboardRow.add(keyboardButton);

                keyboardButtons.add(keyboardRow);

                messageId = sendMessageWithInlineKeyboard(context,text,keyboardButtons).getMessageId();
                context.setLastMessageId(messageId);
            }
        }
        @Override
        public void handleInput(BotContext context) {

        }

        @Override
        public void handleCallBack(BotContext context) {
            if(context.getCallbackQuery().getData().equals("back")){
                next = MainMenu;
                deleteMessage(context,context.getLastMessageId());
                return;
            }
            String[] args = context.getCallbackQuery().getData().split("-");
            String managerId = args[0];
            String machineId = args[1];
            if(managerId.equals("1")){
                next = OneManager;
            }else if(managerId.equals("2")){
                next = TwoManager;
            }else if(managerId.equals("3")){
                next = ThreeManager;
            }else if(managerId.equals("4")){
                next = FourManager;
            }else if(managerId.equals("5")){
                next = FiveManager;
            }


            try{
                Machine machine = context.getMachineService().findByMachineId(Long.parseLong(machineId));
                LOGGER.info(machine.getName()+" has been chosen by customer {"+machine.getLink()+"}");
                String text = String.format("Вы выбрали:%s\n%s",
                        machine.getName(),
                        machine.getLink());

                sendMessage(context,text);
            }catch (Exception e){e.printStackTrace();}


            deleteMessage(context,context.getLastMessageId());
        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {
            machineList = machines;
        }
    },
    StoneMiningEquipment(true,true){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Выберите станки:");
            List<Machine> machines = context.getMachineService().findByCategoryId(1);

            List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

            for(Machine machine : machines){
                InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
                List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

                keyboardButton.setText(machine.getName());
                keyboardButton.setCallbackData(machine.getManagerId().toString()+"-"+machine.getMachineId().toString());

                keyboardRow.add(keyboardButton);

                keyboardButtons.add(keyboardRow);
            }
            InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
            List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

            keyboardButton.setText("Вернуться назад");
            keyboardButton.setCallbackData("back");

            keyboardRow.add(keyboardButton);

            keyboardButtons.add(keyboardRow);

            messageId = sendMessageWithInlineKeyboard(context,text,keyboardButtons).getMessageId();
            context.setLastMessageId(messageId);
        }

        @Override
        public void handleCallBack(BotContext context) {
            if(context.getCallbackQuery().getData().equals("back")){
                next = MainMenu;
                deleteMessage(context,context.getLastMessageId());
                return;
            }
            String[] args = context.getCallbackQuery().getData().split("-");
            String managerId = args[0];
            String machineId = args[1];
            if(managerId.equals("1")){
                next = OneManager;
            }else if(managerId.equals("2")){
                next = TwoManager;
            }else if(managerId.equals("3")){
                next = ThreeManager;
            }else if(managerId.equals("4")){
                next = FourManager;
            }else if(managerId.equals("5")){
                next = FiveManager;
            }

            Machine machine = context.getMachineService().findByMachineId(Long.parseLong(machineId));
            LOGGER.info(machine.getName()+" has been chosen by customer {"+machine.getLink()+"}");
            String text = String.format("Вы выбрали:%s\n%s",
                    machine.getName(),
                    machine.getLink());

            sendMessage(context,text);

            deleteMessage(context,context.getLastMessageId());
        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    StoneProcessingEquipment(true,true){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Выберите станки:");
            List<Machine> machines = context.getMachineService().findByCategoryId(2);

            List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

            for(Machine machine : machines){
                InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
                List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

                keyboardButton.setText(machine.getName());
                keyboardButton.setCallbackData(machine.getManagerId().toString()+"-"+machine.getMachineId().toString());

                keyboardRow.add(keyboardButton);

                keyboardButtons.add(keyboardRow);
            }
            InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
            List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

            keyboardButton.setText("Вернуться назад");
            keyboardButton.setCallbackData("back");

            keyboardRow.add(keyboardButton);

            keyboardButtons.add(keyboardRow);

            messageId = sendMessageWithInlineKeyboard(context,text,keyboardButtons).getMessageId();
            context.setLastMessageId(messageId);
        }

        @Override
        public void handleCallBack(BotContext context) {
            if(context.getCallbackQuery().getData().equals("back")){
                next = MainMenu;
                deleteMessage(context,context.getLastMessageId());
                return;
            }
            String[] args = context.getCallbackQuery().getData().split("-");
            String managerId = args[0];
            String machineId = args[1];
            if(managerId.equals("1")){
                next = OneManager;
            }else if(managerId.equals("2")){
                next = TwoManager;
            }else if(managerId.equals("3")){
                next = ThreeManager;
            }else if(managerId.equals("4")){
                next = FourManager;
            }else if(managerId.equals("5")){
                next = FiveManager;
            }

            Machine machine = context.getMachineService().findByMachineId(Long.parseLong(machineId));
            LOGGER.info(machine.getName()+" has been chosen by customer {"+machine.getLink()+"}");
            String text = String.format("Вы выбрали:%s\n%s",
                    machine.getName(),
                    machine.getLink());

            sendMessage(context,text);

            deleteMessage(context,context.getLastMessageId());
        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    RepairOfWagons(true,true){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Выберите станки:");
            List<Machine> machines = context.getMachineService().findByCategoryId(3);

            List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

            for(Machine machine : machines){
                InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
                List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

                keyboardButton.setText(machine.getName());
                keyboardButton.setCallbackData(machine.getManagerId().toString()+"-"+machine.getMachineId().toString());

                keyboardRow.add(keyboardButton);

                keyboardButtons.add(keyboardRow);
            }
            InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
            List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

            keyboardButton.setText("Вернуться назад");
            keyboardButton.setCallbackData("back");

            keyboardRow.add(keyboardButton);

            keyboardButtons.add(keyboardRow);

            messageId = sendMessageWithInlineKeyboard(context,text,keyboardButtons).getMessageId();
            context.setLastMessageId(messageId);
        }

        @Override
        public void handleCallBack(BotContext context) {
            if(context.getCallbackQuery().getData().equals("back")){
                next = MainMenu;
                deleteMessage(context,context.getLastMessageId());
                return;
            }
            String[] args = context.getCallbackQuery().getData().split("-");
            String managerId = args[0];
            String machineId = args[1];
            if(managerId.equals("1")){
                next = OneManager;
            }else if(managerId.equals("2")){
                next = TwoManager;
            }else if(managerId.equals("3")){
                next = ThreeManager;
            }else if(managerId.equals("4")){
                next = FourManager;
            }else if(managerId.equals("5")){
                next = FiveManager;
            }

            Machine machine = context.getMachineService().findByMachineId(Long.parseLong(machineId));
            LOGGER.info(machine.getName()+" has been chosen by customer {"+machine.getLink()+"}");
            String text = String.format("Вы выбрали:%s\n%s",
                    machine.getName(),
                    machine.getLink());

            sendMessage(context,text);

            deleteMessage(context,context.getLastMessageId());
        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    NonstandardEquipment(true,true){
        private Integer messageId;
        private BotState next;
        @Override
        public void enter(BotContext context) {
            String text = String.format("Выберите станки:");
            List<Machine> machines = context.getMachineService().findByCategoryId(4);

            List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

            for(Machine machine : machines){
                InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
                List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

                keyboardButton.setText(machine.getName());
                keyboardButton.setCallbackData(machine.getManagerId().toString()+"-"+machine.getMachineId().toString());

                keyboardRow.add(keyboardButton);

                keyboardButtons.add(keyboardRow);
            }
            InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
            List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

            keyboardButton.setText("Вернуться назад");
            keyboardButton.setCallbackData("back");

            keyboardRow.add(keyboardButton);

            keyboardButtons.add(keyboardRow);

            messageId = sendMessageWithInlineKeyboard(context,text,keyboardButtons).getMessageId();
            context.setLastMessageId(messageId);
        }

        @Override
        public void handleCallBack(BotContext context) {
            if(context.getCallbackQuery().getData().equals("back")){
                next = MainMenu;
                deleteMessage(context,context.getLastMessageId());
                return;
            }
            String[] args = context.getCallbackQuery().getData().split("-");
            String managerId = args[0];
            String machineId = args[1];
            if(managerId.equals("1")){
                next = OneManager;
            }else if(managerId.equals("2")){
                next = TwoManager;
            }else if(managerId.equals("3")){
                next = ThreeManager;
            }else if(managerId.equals("4")){
                next = FourManager;
            }else if(managerId.equals("5")){
                next = FiveManager;
            }

            Machine machine = context.getMachineService().findByMachineId(Long.parseLong(machineId));
            LOGGER.info(machine.getName()+" has been chosen by customer {"+machine.getLink()+"}");
            String text = String.format("Вы выбрали:%s\n%s",
                    machine.getName(),
                    machine.getLink());

            sendMessage(context,text);

            deleteMessage(context,context.getLastMessageId());
        }

        @Override
        public BotState nextState() {
            return next;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    OneManager(false,false){
        @Override
        public void enter(BotContext context) {
            Manager manager = context.getManagerService().findByManagerId(1);
            String text = String.format("Контактные данные специалиста\n\n" +
                                        "Ваш консультант: %s\n" +
                                        "Телефон:Example\n" +
                                        "Email:Example\n" +
                                        "Telegram:%s\n" +
                                        "Whatsapp:Example",
                                        manager.getSurname(),manager.getTelegramName());

            sendMessage(context,text);
        }

        @Override
        public BotState nextState() {
            return MainMenu;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    TwoManager(false,false){
        @Override
        public void enter(BotContext context) {
            Manager manager = context.getManagerService().findByManagerId(2);
            String text = String.format("Контактные данные специалиста\n\n" +
                            "Ваш консультант: %s\n" +
                            "Телефон:Example\n" +
                            "Email:Example\n" +
                            "Telegram:%s\n" +
                            "Whatsapp:Example",
                    manager.getSurname(),manager.getTelegramName());

            sendMessage(context,text);
        }

        @Override
        public BotState nextState() {
            return MainMenu;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    ThreeManager(false,false){
        @Override
        public void enter(BotContext context) {
            Manager manager = context.getManagerService().findByManagerId(3);
            String text = String.format("Контактные данные специалиста\n\n" +
                            "Ваш консультант: %s\n" +
                            "Телефон:Example\n" +
                            "Email:Example\n" +
                            "Telegram:%s\n" +
                            "Whatsapp:Example",
                    manager.getSurname(),manager.getTelegramName());

            sendMessage(context,text);
        }

        @Override
        public BotState nextState() {
            return MainMenu;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    FourManager(false,false){
        @Override
        public void enter(BotContext context) {
            Manager manager = context.getManagerService().findByManagerId(4);
            String text = String.format("Контактные данные специалиста\n\n" +
                            "Ваш консультант: %s\n" +
                            "Телефон:Example\n" +
                            "Email:Example\n" +
                            "Telegram:%s\n" +
                            "Whatsapp:Example",
                    manager.getSurname(),manager.getTelegramName());

            sendMessage(context,text);
        }

        @Override
        public BotState nextState() {
            return MainMenu;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    },
    FiveManager(false,false){
        @Override
        public void enter(BotContext context) {
            Manager manager = context.getManagerService().findByManagerId(5);
            String text = String.format("Контактные данные специалиста\n\n" +
                            "Ваш консультант: %s\n" +
                            "Телефон:Example\n" +
                            "Email:Example\n" +
                            "Telegram:%s\n" +
                            "Whatsapp:Example",
                    manager.getSurname(),manager.getTelegramName());

            sendMessage(context,text);
        }

        @Override
        public BotState nextState() {
            return MainMenu;
        }

        @Override
        public void setContainsMachines(List<Machine> machines) {

        }
    };

    private static BotState[] states;
    private final boolean inputNeeded;
    private final boolean callBack;
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);


    BotState(boolean inputNeeded,boolean callBack) {
        this.inputNeeded = inputNeeded;
        this.callBack = callBack;
    }
    BotState(boolean inputNeeded) {
        this.inputNeeded = inputNeeded;
        this.callBack = false;
    }

    public static BotState getInitialState() {
        return byId(0);
    }

    public static BotState byId(int id) {
        if (states == null) {
            states = BotState.values();
        }
        return states[id];
    }
    protected void deleteMessage(BotContext context, Integer messageId) {
        String chatId = String.valueOf(context.getUser().getChatId());
        DeleteMessage deleteMessage = new DeleteMessage(chatId,messageId);
        SendChatAction chatAction = new SendChatAction(chatId, ActionType.TYPING.toString());


        try {
            context.getBot().execute(chatAction);
            context.getBot().execute(deleteMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    protected Message sendMessage(BotContext context, String text) {
        String chatId = String.valueOf(context.getUser().getChatId());
        SendMessage sendMessage = new SendMessage(chatId,text);
        SendChatAction chatAction = new SendChatAction(chatId, ActionType.TYPING.toString());

        sendMessage.enableMarkdownV2(false);

        Message message = null;
        try {
            context.getBot().execute(chatAction);
            message = context.getBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return message;
    }
    protected Message sendMessageWithMarkdown(BotContext context, String text){
        String chatId = String.valueOf(context.getUser().getChatId());
        SendMessage sendMessage = new SendMessage(chatId,text);
        SendChatAction chatAction = new SendChatAction(chatId,ActionType.TYPING.toString());

        sendMessage.enableMarkdownV2(true);

        Message message = null;
        try {
            context.getBot().execute(chatAction);
            message = context.getBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return message;
    }
    protected Message sendMessageWithKeyboard(BotContext context, String text, List<KeyboardRow> keyboard){
        String chatId = String.valueOf(context.getUser().getChatId());
        SendMessage sendMessage = new SendMessage(chatId,text);
        SendChatAction chatAction = new SendChatAction(chatId,ActionType.TYPING.toString());
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboard);

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        sendMessage.enableMarkdownV2(false);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        Message message = null;
        try {
            context.getBot().execute(chatAction);
            message = context.getBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return message;
    }
    protected Message sendMessageWithInlineKeyboard(BotContext context, String text, List<List<InlineKeyboardButton>> keyboard){
        String chatId = String.valueOf(context.getUser().getChatId());
        SendMessage sendMessage = new SendMessage(chatId,text);
        SendChatAction chatAction = new SendChatAction(chatId,ActionType.TYPING.toString());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboard);


        sendMessage.enableMarkdownV2(false);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        Message message = null;
        try {
            context.getBot().execute(chatAction);
            message = context.getBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return message;
    }
    protected Message sendMessageWithMarkdownAndKeyboard(BotContext context, String text, List<KeyboardRow> keyboard){
        String chatId = String.valueOf(context.getUser().getChatId());
        SendMessage sendMessage = new SendMessage(chatId,text);
        SendChatAction chatAction = new SendChatAction(chatId,ActionType.TYPING.toString());
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboard);

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        sendMessage.enableMarkdownV2(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        Message message = null;
        try {
            context.getBot().execute(chatAction);
            message = context.getBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return message;
    }
    protected Message sendMessageWithMarkdownAndInlineKeyboard(BotContext context, String text, List<List<InlineKeyboardButton>> keyboard){
        String chatId = String.valueOf(context.getUser().getChatId());
        SendMessage sendMessage = new SendMessage(chatId,text);
        SendChatAction chatAction = new SendChatAction(chatId,ActionType.TYPING.toString());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboard);


        sendMessage.enableMarkdownV2(true);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        Message message = null;
        try {
            context.getBot().execute(chatAction);
            message = context.getBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return message;
    }

    public boolean isInputNeeded() {
        return inputNeeded;
    }
    public boolean isCallBack(){
        return callBack;
    }

    public void handleInput(BotContext context) {
        // do nothing by default
    }
    public void handleCallBack(BotContext context) {
        // do nothing by default
    }

    public abstract void enter(BotContext context);
    public abstract BotState nextState();
    public abstract void setContainsMachines(List<Machine> machines);
}
