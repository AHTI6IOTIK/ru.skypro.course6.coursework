package pro.sky.telegrambot.scheduler.dto;

import java.util.Objects;

public class DialogDto {
    private final Long chatId;
    private final String message;

    public DialogDto(Long chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DialogDto)) return false;
        DialogDto dialogDto = (DialogDto) o;
        return Objects.equals(chatId, dialogDto.chatId) && Objects.equals(message, dialogDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, message);
    }
}
