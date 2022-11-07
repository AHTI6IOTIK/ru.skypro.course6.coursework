package pro.sky.telegrambot.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.scheduler.entity.NotificationTaskEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTaskEntity, Long> {
    List<NotificationTaskEntity> findByNotifyDatetimeLessThanEqual(LocalDateTime dateTime);
}
