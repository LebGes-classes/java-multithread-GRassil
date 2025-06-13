import task_trecker.Office;
import telegramBot.TimerBot;

public class Main {
    public static void main(String[] args) {
        // Офис
        Office office = new Office();
        office.work();

        // Тг бот
        try {
            TimerBot bot = new TimerBot();
            bot.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}