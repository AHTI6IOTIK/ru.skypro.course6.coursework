package pro.sky.telegrambot.scheduler.exception;

public class IntervalDateIncorrectException extends RuntimeException {
    public IntervalDateIncorrectException() {
        super("You cannot specify the past date");
    }
}
