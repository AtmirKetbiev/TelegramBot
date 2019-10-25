import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class GetInlineKeyboard {

    static SendMessage setInline(long chatId) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> keyboardFirstRow = new ArrayList<>();

        keyboardFirstRow.add(new InlineKeyboardButton("Полное расписание").setUrl("https://www.spbgasu.ru/Studentam/Raspisanie_zanyatiy/"));
        keyboardFirstRow.add(new InlineKeyboardButton("Moodle").setUrl("https://moodle.spbgasu.ru/"));
        keyboardFirstRow.add(new InlineKeyboardButton().setText("Fi4a").setCallbackData("CallFi4a"));
        keyboard.add(keyboardFirstRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return new SendMessage().setChatId(chatId).setText("Перейти к:").setReplyMarkup(inlineKeyboardMarkup);
    }
}
