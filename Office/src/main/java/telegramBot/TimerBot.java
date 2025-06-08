package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimerBot extends TelegramLongPollingBot {

    private final ScheduledExecutorService executor =
            Executors.newScheduledThreadPool(10); // Пул потоков для таймеров

    @Override
    public String getBotUsername() {
        return "multithhreadbot";
    }

    @Override
    public String getBotToken() {
        return "8103857667:AAEHd4GiNSyhSH42HxJCmKzFGiM3OVIu2rU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Обработка команды /timer
            if (messageText.startsWith("/timer")) {
                handleTimerCommand(chatId, messageText);
            } else {
                sendMessage(chatId, "Используйте команду /timer N, где N - секунды");
            }
        }
    }

    private void handleTimerCommand(long chatId, String command) {
        Pattern pattern = Pattern.compile("/timer\\s+(\\d+)");
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            int seconds = Integer.parseInt(matcher.group(1));

            if (seconds > 0) {
                startTimer(chatId, seconds);
                sendMessage(chatId, "Таймер запущен на " + seconds + " секунд!");
            } else {
                sendMessage(chatId, "Введите положительное число секунд");
            }
        } else {
            sendMessage(chatId, "Неверный формат. Используйте: /timer 300");
        }
    }

    private void startTimer(long chatId, int seconds) {
        TimerTask timerTask = new TimerTask(chatId, seconds, this);
        executor.schedule(timerTask, seconds, TimeUnit.SECONDS);
    }

    protected void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.err.println("Ошибка отправки сообщения: " + e.getMessage());
        }
    }

    public void start() throws TelegramApiException {
        TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
        bot.registerBot(new TimerBot());
    }
}