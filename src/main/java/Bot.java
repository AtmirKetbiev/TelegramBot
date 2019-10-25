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

    private static final String BOT_TOKEN = "1006020808:AAG0kktOU5v7Fl_2oF8lpBljTnpm8CVz5Tg";   //����� ����
    private static final String BOT_USERNAME = "TestAtmBot";                                    //��� ����
    private static long chat_id;
    String[] Week = {"��", "��","��","��","��"};
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();                        //�������� ���������������� ����������
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
                keyboardFirstRow.add("��");
                keyboardFirstRow.add("��");
                keyboardFirstRow.add("��");
                keyboardFirstRow.add("��");
                keyboardFirstRow.add("��");
                keyboard.add(keyboardFirstRow);
                replyKeyboardMarkup.setKeyboard(keyboard);
                return "�� ������ ������� ���� ������";
            case ("��"):
                return Timetable.MONDAY.toString();
            case ("��"):
                return Timetable.TUESDAY.toString();
            case ("��"):
                return Timetable.WEDNESDAY.toString();
            case ("��"):
                return Timetable.THURSDAY.toString();
            case ("��"):
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
