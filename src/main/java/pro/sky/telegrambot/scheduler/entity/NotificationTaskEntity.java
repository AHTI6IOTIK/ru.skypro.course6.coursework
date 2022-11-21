package pro.sky.telegrambot.scheduler.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification_task")
public class NotificationTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="notification_task_id_seq")
    @SequenceGenerator(name="notification_task_id_seq", sequenceName="notification_task_id_seq", allocationSize=1)
    private Long id;
    private Long chatId;
    private LocalDateTime notifyDatetime;
    private String notifyMessage;

    public Long getChatId() {
        return chatId;
    }

    public NotificationTaskEntity setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public LocalDateTime getNotifyDatetime() {
        return notifyDatetime;
    }

    public NotificationTaskEntity setNotifyDatetime(LocalDateTime notifyDateTime) {
        this.notifyDatetime = notifyDateTime;
        return this;
    }

    public String getNotifyMessage() {
        return notifyMessage;
    }

    public NotificationTaskEntity setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationTaskEntity)) return false;
        NotificationTaskEntity entity = (NotificationTaskEntity) o;
        return Objects.equals(chatId, entity.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }

    @Override
    public String toString() {
        return "NotificationTaskEntity{" +
            "chatId=" + chatId +
            ", notifyDateTime=" + notifyDatetime +
            ", notifyMessage='" + notifyMessage + '\'' +
            '}';
    }

    public Long getId() {
        return id;
    }

    public NotificationTaskEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
