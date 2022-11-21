package pro.sky.telegrambot.scheduler.dialog;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.scheduler.dto.DialogDto;

@Component
@Order(-1)
public class UnsupportedDialog implements DialogInterface{
    @Override
    public boolean isSupport(DialogDto dialogDto) {
        return true;
    }

    @Override
    public boolean process(DialogDto dialogDto) {
        return true;
    }

    @Override
    public String getMessage() {
        return "I didn't understand anything";
    }
}
