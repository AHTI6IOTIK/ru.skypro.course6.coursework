package pro.sky.telegrambot.scheduler.dialog;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.scheduler.dto.DialogDto;

@Component
public class StartDialog implements DialogInterface {
    @Override
    public boolean isSupport(DialogDto dialogDto) {
        return dialogDto.getMessage().equals("/start");
    }

    @Override
    public boolean process(DialogDto dialogDto) {
        return true;
    }

    @Override
    public String getMessage() {
        return "Hello friend";
    }
}
