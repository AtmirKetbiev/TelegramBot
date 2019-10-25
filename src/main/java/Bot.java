import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "1006020808:AAG0kktOU5v7Fl_2oF8lpBljTnpm8CVz5Tg";   //Токен бота
    private static final String BOT_USERNAME = "TestAtmBot";                                    //Имя бота
    private static long chat_id;
    String[] Week = {"Пн", "Вт","Ср","Чт","Пт"};
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();                        //Создание пользовательской клавиатуры
    InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
    public Bot(DefaultBotOptions botOptions) {
        super(botOptions);
    }

    @Override
    public void onUpdateReceived(Update update) {

        chat_id = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            sendMessage.setText(getMessage(text));
            execute(sendMessage);
                if (!text.equals("/start")) {
                    if(update.hasMessage()) {
                    execute(GetInlineKeyboard.setInline(chat_id));
                    } else if(update.hasCallbackQuery()){
                        try {
                            execute(new SendMessage().setText(
                                    update.getCallbackQuery().getData())
                                    .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
}

    public String getMessage(String msg) throws TelegramApiException {

        switch (msg) {
            case ("/start"):
                return "Hello\nEnter '/Menu' to display the menu";
            case ("/Menu"):
                ArrayList<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow keyboardFirstRow = new KeyboardRow();

                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);
                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardFirstRow.add("Пн");
                keyboardFirstRow.add("Вт");
                keyboardFirstRow.add("Ср");
                keyboardFirstRow.add("Чт");
                keyboardFirstRow.add("Пт");
                keyboard.add(keyboardFirstRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "Вы можете выбрать день недели";
            case ("Пн"):
                return Timetable.MONDAY.toString();
            case ("Вт"):
                return Timetable.TUESDAY.toString();
            case ("Ср"):
                return Timetable.WEDNESDAY.toString();
            case ("Чт"):
                return Timetable.THURSDAY.toString();
            case ("Пт"):
                return Timetable.FRIDAY.toString();
            default:
                return msg;
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
