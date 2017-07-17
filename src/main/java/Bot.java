import Entity.Book;
import dbService.DBException;
import dbService.DBService;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Eshu on 14.07.2017.
 */
public class Bot extends TelegramLongPollingBot {
    //todo put your chatId here to upload books to the telegram server
    private long chatId = 0;
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Кто то что то написал боту");
    }

    @Override
    //todo put bot username here
    public String getBotUsername() {
        return "";
    }

    @Override
    //todo put bot token here
    public String getBotToken() {
        return "";
    }


    void addAllBooks(String directoryWithBooksPath) throws IOException, DBException {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        File[] fList;
        File F = new File(directoryWithBooksPath);
        FileInputStream inputStream;

        fList = F.listFiles();

        if(fList==null){
         return;
        }
        for (File aFList : fList) {
            if (aFList.isFile()) {
                try {
                    inputStream = new FileInputStream(aFList);
                } catch (FileNotFoundException e){
                    throw new RuntimeException(e);
                }
                try {

                    bookArrayList.add(new Book(aFList.getName().substring(0,aFList.getName()
                            .indexOf(".")),this.sendDocument(new SendDocument().setChatId(chatId)
                    .setNewDocument(aFList.getName().substring(0,aFList.getName().indexOf(".")),inputStream))
                    .getDocument().getFileId()));
                } catch (TelegramApiException ignored){
            }
            inputStream.close();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        DBService db = new DBService();
        db.addBooks(bookArrayList);

    }
}
