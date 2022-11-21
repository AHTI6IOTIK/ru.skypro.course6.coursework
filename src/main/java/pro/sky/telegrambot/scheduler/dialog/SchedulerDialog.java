package pro.sky.telegrambot.scheduler.dialog;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.scheduler.dto.DialogDto;
import pro.sky.telegrambot.scheduler.entity.NotificationTaskEntity;
import pro.sky.telegrambot.scheduler.exception.IntervalDateIncorrectException;
import pro.sky.telegrambot.scheduler.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SchedulerDialog implements DialogInterface {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private static final Pattern REGEX_PATTERN = Pattern.compile("([0-9\\.\\:\\s]{16})\\s([\\w|\\W+]+)");
    private static Matcher matcher;

    private final NotificationTaskRepository repository;

    public SchedulerDialog(NotificationTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isSupport(DialogDto dialogDto) {
        matcherInitial(dialogDto);
        return matcher.matches();
    }

    private void matcherInitial(DialogDto dialogDto) {
        matcher = REGEX_PATTERN.matcher(dialogDto.getMessage());
    }

    @Override
    public boolean process(DialogDto dialogDto) {
        if (Objects.isNull(matcher)) {
            matcherInitial(dialogDto);
        }

        NotificationTaskEntity entity = getEntity(dialogDto);
        repository.save(entity);

        return true;
    }

    private NotificationTaskEntity getEntity(DialogDto dialogDto) {
        LocalDateTime dateTime = LocalDateTime.parse(matcher.group(1), DATE_FORMATTER);
        checkDateTimeInterval(dateTime);
        return (new NotificationTaskEntity())
            .setChatId(dialogDto.getChatId())
            .setNotifyDatetime(dateTime)
            .setNotifyMessage(matcher.group(2))
        ;
    }

    private void checkDateTimeInterval(LocalDateTime dateTime) {
        int compareResult = dateTime.compareTo(LocalDateTime.now());
        if (compareResult < 0) {
            throw new IntervalDateIncorrectException();
        }
    }

    @Override
    public String getMessage() {
        return "Okay, I'll remind you";
    }
}
