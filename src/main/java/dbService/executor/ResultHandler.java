package dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eshu on 14.07.2017.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}