package telegramBot;

import java.util.concurrent.atomic.AtomicInteger;

public class TimerTask implements Runnable {
    private final long chatId;
    private final TimerBot bot;
    private final AtomicInteger secondsLeft;

    public TimerTask(long chatId, int seconds, TimerBot bot) {
        this.chatId = chatId;
        this.bot = bot;
        this.secondsLeft = new AtomicInteger(seconds);
    }

    @Override
    public void run() {
        bot.sendMessage(chatId, "⏰ Таймер завершен! Прошло " + secondsLeft.get() + " секунд");
    }
}
