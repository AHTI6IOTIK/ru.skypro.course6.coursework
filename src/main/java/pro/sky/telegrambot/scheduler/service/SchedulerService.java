package pro.sky.telegrambot.scheduler.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.scheduler.dialog.SchedulerDialog;
import pro.sky.telegrambot.scheduler.entity.NotificationTaskEntity;
import pro.sky.telegrambot.scheduler.repository.NotificationTaskRepository;
import pro.sky.telegrambot.service.BotService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulerService {
    private final NotificationTaskRepository repository;
    private final BotService botService;

    public SchedulerService(
        NotificationTaskRepository repository,
        BotService botService
    ) {
        this.repository = repository;
        this.botService = botService;
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void run() {
        List<NotificationTaskEntity> tasks = repository
            .findByNotifyDatetimeLessThanEqual(LocalDateTime.now());

        tasks.forEach(task -> {
            botService.sendResponse(
                task.getChatId(),
                String.format("Start event: '%s'", task.getNotifyMessage())
            );
            repository.delete(task);
        });
    }
}
