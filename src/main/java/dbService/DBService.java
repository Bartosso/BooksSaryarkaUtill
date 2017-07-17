package dbService;

import Entity.Book;
import dbService.dao.BooksDao;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Eshu on 14.07.2017.
 */
public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getH2Connection();
    }


    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:~/test";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBooks(ArrayList<Book> bookArrayList) throws DBException {
        try{
            connection.setAutoCommit(false);
            BooksDao booksDao = new BooksDao(connection);
            for(Book book : bookArrayList){
            booksDao.addBook(book);
            }
            connection.commit();
        } catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ignore){
            }
            throw new DBException(e);
        } finally {
            try{
            connection.setAutoCommit(true);
        }
        catch (SQLException ignore){
        }
    }

}
}
