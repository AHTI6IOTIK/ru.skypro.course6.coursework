package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.scheduler.dialog.DialogInterface;
import pro.sky.telegrambot.scheduler.dto.DialogDto;
import pro.sky.telegrambot.scheduler.exception.IntervalDateIncorrectException;

import java.util.Map;

@Service
public class BotService {
    private final TelegramBot telegramBot;
    private final Map<String, DialogInterface> supportedDialogs;

    public BotService(
        TelegramBot bot,
        Map<String, DialogInterface> supportedDialogs
    ) {
        this.telegramBot = bot;
        this.supportedDialogs = supportedDialogs;
    }

    public void process(Update update) {
        try {
            for (DialogInterface dialog : supportedDialogs.values()) {
                Message incomeMessage = update.message();
                DialogDto dto = new DialogDto(incomeMessage.chat().id(), incomeMessage.text());
                if (dialog.isSupport(dto) && dialog.process(dto)) {
                    sendResponse(dto.getChatId(), dialog.getMessage());
                    return;
                }
            }
        } catch (IntervalDateIncorrectException exception) {
            sendResponse(update.message().chat().id(), exception.getMessage());
        }

    }

    public SendResponse sendResponse(Long chatId, String message) {
        SendMessage preparedMessage = new SendMessage(chatId, message);
        return telegramBot.execute(preparedMessage);
    }
}
