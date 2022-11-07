package pro.sky.telegrambot.scheduler.dialog;

import pro.sky.telegrambot.scheduler.dto.DialogDto;

public interface DialogInterface {
    boolean isSupport(DialogDto dialogDto);

    boolean process(DialogDto dialogDto);

    String getMessage();
}
