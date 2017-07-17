import dbService.DBException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;

/**
 * Created by Eshu on 14.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            Bot bot           = new Bot();
            telegramBotsApi.registerBot(bot);
            //todo put path to directory with books what you want to upload
            bot.addAllBooks("");

        } catch (TelegramApiRequestException e) {
            throw new RuntimeException(e);

        } catch (DBException | IOException e) {
            e.printStackTrace();
        }
    }}
