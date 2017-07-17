package dbService.dao;

import Entity.Book;
import dbService.executor.Executor;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Eshu on 14.07.2017.
 */
public class BooksDao {
    private Executor executor;

    public BooksDao(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void addBook(Book book) throws SQLException {
        executor.execUpdate("INSERT INTO BOOKS (NAME, FILE) VALUES ('"+book.getName()+"','"+book.getFile()+"')");
    }
}
