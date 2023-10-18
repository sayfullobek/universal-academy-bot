//  Create by Jahongir and Oybek

import config.BotConfig;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Bot extends TelegramWebhookBot implements LongPollingBot {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            System.out.println("not api");
        }
    }

    @Override
    public String getBotUsername() {
        return "Universal_academy_uz_bot";
    }

    @Override
    public String getBotToken() {
        return "6506176728:AAGexRxvWejcVUYqcaNCc331vUdBlG8nB4U";
    }

    @Override
    public void clearWebhook() {

    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return null;
    }

    @Override
    public void onUpdateReceived(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();

        if (update.hasMessage()) {
            Message message = update.getMessage();
            User from = message.getFrom();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            String chatId = String.valueOf(message.getChatId());
            BotConfig.language.putIfAbsent(chatId, "uz");
            //putIfAbsent - Null bulsa qushmeydi , qo'shilgan ma'lumotni qayta qo'shmaydi
            System.out.println(from);
            String text = message.getText();
            System.out.println(text);

            switch (text) {
                case "Asosiy bo'lim" -> startBtns(sendMessage, chatId);
                case "Основная часть" -> startBtns(sendMessage, chatId);
                case "/start" -> startBtns(sendMessage, chatId);
                case "Rus Tili" -> {
                    BotConfig.language.replace(chatId, "ru");
                    startBtns(sendMessage, chatId);
                }
                case "Узбекский язык" -> {
                    BotConfig.language.replace(chatId, "uz");
                    startBtns(sendMessage, chatId);
                }
                default ->
                        sendMessage.setText(BotConfig.language.get(chatId).equals("uz") ? "Mavjud emas" : "Не найдено");
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                System.out.println("start buttondagi executeda xatolik");
            }

        } else if (update.hasCallbackQuery()) {
            String data = callbackQuery.getData();
            SendMessage sendMessage = new SendMessage();
            Message message = callbackQuery.getMessage();
            String chatId = String.valueOf(message.getChatId());
            sendMessage.setChatId(chatId);
            sendMessage.setText("Universal academy");
            switch (data) {
                case "biz haqimizda" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\Universal_academy.jpg");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Bizda sifatli va samarali ta'lim oling\nbizning o'quv markazimizda ko'p yillik tajribaga ega o'qituvchilar dars berishadi\nBizning kurslarimizda o'qib katta natijalarga erishing" : """
                            Получите качественное и эффективное образование вместе с нами
                            в нашем учебном центре преподают преподаватели с многолетним опытом работы
                            Получите отличные результаты, обучаясь на наших курсах""", file);

                }
                case "kurslar" -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(BotConfig.language.get(chatId).equals("uz") ? "Web dasturlash" : "Веб-программирование");
                    button.setCallbackData("dasturlash");

                    InlineKeyboardButton button1 = new InlineKeyboardButton();
                    button1.setText(BotConfig.language.get(chatId).equals("uz") ? "Grafik dizayn" : "Графический дизайн");
                    button1.setCallbackData("grafik");

                    InlineKeyboardButton button2 = new InlineKeyboardButton();
                    button2.setText(BotConfig.language.get(chatId).equals("uz") ? "Arxitektura va modelling" : "Архитектура и дизайн");
                    button2.setCallbackData("arxitektura");

                    InlineKeyboardButton button3 = new InlineKeyboardButton();
                    button3.setText(BotConfig.language.get(chatId).equals("uz") ? "Web dizayn" : "Bеб-дизайн");
                    button3.setCallbackData("dizayn");

                    InlineKeyboardButton button4 = new InlineKeyboardButton();
                    button4.setText(BotConfig.language.get(chatId).equals("uz") ? "Smm Mobilografika" : "Смм Мобилография");
                    button4.setCallbackData("smm");

                    InlineKeyboardButton button5 = new InlineKeyboardButton();
                    button5.setText(BotConfig.language.get(chatId).equals("uz") ? "Kompyuter savodxonligi" : "Компьютерная грамотность");
                    button5.setCallbackData("savodxonlik");

                    InlineKeyboardButton button6 = new InlineKeyboardButton();
                    button6.setText("It kids");
                    button6.setCallbackData("kids");
                    //

                    //rows
                    List<InlineKeyboardButton> row = new ArrayList<>();
                    List<InlineKeyboardButton> row1 = new ArrayList<>();
                    List<InlineKeyboardButton> row2 = new ArrayList<>();
                    List<InlineKeyboardButton> row3 = new ArrayList<>();
                    row.add(button);
                    row.add(button1);
                    row1.add(button2);
                    row2.add(button3);
                    row2.add(button4);
                    row3.add(button5);
                    row3.add(button6);

                    //

                    // row collection
                    List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>();
                    rowCollection.add(row);
                    rowCollection.add(row1);
                    rowCollection.add(row2);
                    rowCollection.add(row3);
                    //

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    inlineKeyboardMarkup.setKeyboard(rowCollection);

                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                }
                case "bog'lanish" -> {
                    sendMsg(chatId, BotConfig.language.get(chatId).equals("uz") ? "Murojat uchun" : "Для справки");
                    connectBtns(sendMessage, chatId);
                }
                case "dasturlash" -> {
                    sendMessage.setChatId(chatId);

                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\web programming.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Web dasturlash kursimiz 2ga bo'linadi" : "Наш курс веб-программирования разделен на 2", file);
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(BotConfig.language.get(chatId).equals("uz") ? "Full stack dasturlash" : "Full stack программирование");
                    button.setCallbackData("full");

                    InlineKeyboardButton button1 = new InlineKeyboardButton();
                    button1.setText("Frontend");
                    button1.setCallbackData("frontend");

                    //

                    //rows
                    List<InlineKeyboardButton> row = new ArrayList<>();
                    row.add(button);
                    row.add(button1);

                    //

                    // row collection
                    List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>();
                    rowCollection.add(row);
                    //

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    inlineKeyboardMarkup.setKeyboard(rowCollection);

                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                }
                case "grafik" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\graphic design.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Siz Grafik dizayn kursimizda Adobe Photoshop, Adobe Illustrator, ColorDraw, Figma, Canva kabi dasturlarda ishlashni o'rganasiz, bizning kursimiz 6oy davom etadi" : "«На нашем курсе графического дизайна вы научитесь работать с такими программами, как Adobe Photoshop, Adobe Illustrator, ColorDraw, Figma, Canva, наш курс длится 6 месяцев»", file);
                }
                case "arxitektura" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\architecture.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Siz Arxitektura va modeling kursimizda AutoCAD, 3DStudio MAX, lumion va Blender dasturlarida ishlashni o'rganasiz. bu kursimiz 10oy davom etadi" : "На нашем курсе «Архитектура и моделирование» вы научитесь работать в AutoCAD, 3DStudio MAX, Lumion и Blender. этот курс длится 10 месяцев", file);
                }
                case "dizayn" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\web design.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Web dizayn kursida siz UI & UXni o'rganamiz, BU kursimiz 2oy davom etadi" : "На курсе веб-дизайна вы изучите UI и UX, ЭТОТ курс длится 2 месяца.", file);
                }
                case "smm" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\smm.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Smm Mobilografika kursimizda telefonda montaj, instagram, telegram yurgizish, reklamalarni o'rgatamiz. bu kursimiz 2oy davom etadi" : "На нашем курсе Smm Mobilography мы обучаем редактированию телефона, Instagram, Telegram и рекламе. этот курс длится 2 месяца", file);
                }
                case "savodxonlik" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\computer savodxonligi.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Kompyuter savodxonligida siz Kompyuterda ishlashni va office dasturlarini o'rganasiz, bu kursimiz 2oy davom etadi" : "По компьютерной грамотности вы научитесь работать на компьютере и пользоваться офисными программами, курс длится 2 месяца.", file);
                }
                case "kids" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\kids.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "I.T. kids kursimiz yosh bolalar uchun mo'ljallangan. Bu kursimiz 7oy davom etadi" : "ЭТО. Наш детский курс предназначен для детей младшего возраста. Этот курс длится 7 месяцев", file);
                }
                case "full" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\full stack.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Full stack dasturlash kursimizda siz backend, database va frontendni o'rganasiz, backendda java, node.js, python dasturlash tillaridan birini tanlab o'qishingiz mumkin, bu kursimiz 10oy davom etadi" : "На нашем полнофункциональном курсе программирования вы изучите бэкенд, базу данных и фронтенд, вы можете выбрать один из языков программирования Java, Node.js, python в бэкенде, этот курс длится 10 месяцев.", file);
                }
                case "frontend" -> {
                    File file = new File("C:\\Users\\Jahongir\\Downloads\\Telegram Desktop\\universal_academy\\universal_academy\\src\\main\\java\\photos\\front end.png");
                    sendPhoto(chatId, BotConfig.language.get(chatId).equals("uz") ? "Frontend kursida siz saytlarning tashqi ko'rinishini qilishni o'rganasiz, bu kursimiz 6oy davom etadi" : "На курсе Frontend вы научитесь создавать веб-сайты, этот курс длится 6 месяцев.", file);
                }
                default ->
                        sendMsg(chatId, BotConfig.language.get(chatId).equals("uz") ? "bunday bo'lim yo'q" : "нет такого раздела");
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                System.out.println("execute xato");
            }
        }

    }


    public void sendMsg(String chatId, String text) {
        try {
            execute(SendMessage.builder()
                    .text(text)
                    .chatId(chatId)
                    .build());
        } catch (TelegramApiException e) {
            System.err.println("xabar bormadi");
        }
    }

    public void button(String chatId, String text) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        keyboardButtons.add(BotConfig.language.get(chatId).equals("uz") ? "Asosiy bo'lim" : "Основная часть");
        keyboardButtons.add(BotConfig.language.get(chatId).equals("uz") ? "Rus Tili" : "Узбекский язык");
        rowList.add(keyboardButtons);
        replyKeyboardMarkup.setKeyboard(rowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
        try {
            execute(
                    SendMessage.builder()
                            .text(text)
                            .chatId(chatId)
                            .replyMarkup(replyKeyboardMarkup)
                            .build());
        } catch (TelegramApiException e) {
            sendMsg(chatId, BotConfig.language.get(chatId).equals("uz") ? "button yo'q" : "No Button");
        }
    }

    public void sendPhoto(String chatId, String caption, File photo) {
        try {
            execute(SendPhoto.builder()
                    .chatId(chatId)
                    .caption(caption)
                    .photo(new InputFile(photo))
                    .build()
            );
        } catch (TelegramApiException e) {
            System.err.println("not sendPhoto");
        }
    }

    public void connectBtns(SendMessage sendMessage, String chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        sendMessage.setText(BotConfig.language.get(chatId).equals("uz") ? "▪️ Qandaydir savollaringiz bulsa...\n" : "▪️Если у вас есть вопросы...");
        sendMessage.setChatId(chatId);
        button1.setText(BotConfig.language.get(chatId).equals("uz") ? "Direktor" : "Директор");
        button1.setCallbackData("director");
        button1.setUrl("t.me/+998990763246");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText(BotConfig.language.get(chatId).equals("uz") ? "Administrator" : "Администратор");
        button2.setCallbackData("admin");
        button2.setUrl("t.me/+998883876919");

        List<InlineKeyboardButton> row = new LinkedList<>();
        row.add(button1);
        row.add(button2);

        List<List<InlineKeyboardButton>> rowCollection = new LinkedList<>();
        rowCollection.add(row);

        //keyboard
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowCollection);
        //end keyboard

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }

    public void startBtns(SendMessage sendMessage, String chatId) {
        sendMessage.setText(BotConfig.language.get(chatId).equals("uz") ? "Universal Academy zamonaviy kasblar kurslari botiga xush kelibsiz! \n" : "Добро пожаловать в бот курса современных профессий Universal Academy!\n");
        button(chatId, BotConfig.language.get(chatId).equals("uz") ? "Bo'limni tanlang" : "Выберите категорию");
        sendMessage.setText(BotConfig.language.get(chatId).equals("uz") ? "Bu bot maxsus Universal Academy markazi talabalari uchun yaratilgan.\n" : "Этот бот был создан специально для студентов Universal Academy.\n" +
                "\n" +
                (BotConfig.language.get(chatId).equals("uz") ? "▪️ Bosh sahifaga - botning asosiy sahifasiga qaytish va imkoniyatlari haqida batafsil ma'lumot olish.\n" : "▪️Главная — Вернитесь на главную страницу бота и получите подробную информацию о его возможностях.") +
                "\n" +
                (BotConfig.language.get(chatId).equals("uz") ? "▪️Русский язык - tilini o'zbek tilidan rus tiliga o'tkazish." : "▪️O'zbek tili - - перевод языка с узбекского на русский."));
        //end message
        //buttons
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(BotConfig.language.get(chatId).equals("uz") ? "Biz haqimizda" : "O нас");
        button1.setCallbackData("biz haqimizda");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText(BotConfig.language.get(chatId).equals("uz") ? "Kurslar" : "Курсы");
        button2.setCallbackData("kurslar");
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText(BotConfig.language.get(chatId).equals("uz") ? "Bizning sayt" : "Наш сайт");
        button3.setCallbackData("bizning sayt");
        button3.setUrl("https://UniversalAcademy.uz/");
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText(BotConfig.language.get(chatId).equals("uz") ? "Biz bilan bog'lanish" : "Связаться с нами");
        button4.setCallbackData("bog'lanish");

        //end buttons

        // buttons ->   1-biz haqimizda, 2-Kurslar, 3-Bizning sayt, 4-biz bilan bog'lanish,

        //row
        List<InlineKeyboardButton> row = new LinkedList<>();
        List<InlineKeyboardButton> row2 = new LinkedList<>();
        List<InlineKeyboardButton> row3 = new LinkedList<>();
        List<InlineKeyboardButton> row4 = new LinkedList<>();
        row.add(button1);
        row.add(button2);
        row2.add(button3);
        row2.add(button4);

        //end row

        //row collection
        List<List<InlineKeyboardButton>> rowCollection = new LinkedList<>();
        rowCollection.add(row);
        rowCollection.add(row2);
        rowCollection.add(row3);
        rowCollection.add(row4);
        //row end collection

        //keyboard
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowCollection);
        //end keyboard

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

    }
}
