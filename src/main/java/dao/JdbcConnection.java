package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnection {
    private static final Logger log = LoggerFactory.getLogger(JdbcConnection.class);

    Connection connection;
    Statement statement;

    private Connection connect() {
        String url = "jdbc:mysql://localhost/yaraplay";
        String userName = "yaraplay";
        String password = "yara";
        log.info("connect(): conectando a la base de datos");
        try {
            String unicode = "?useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
            return DriverManager.getConnection(url + unicode, userName, password);
        } catch (SQLException e) {
            log.error("connect(): Error al conectar a la base de datos");
            e.printStackTrace();
            throw new YaraPlayException(e);
        }
    }

    public List<String[]> executeQuery(String query) {
        connection = connect();
        ArrayList<String[]> result = new ArrayList<>();
        assert connection != null;
        try {
            statement = connection.createStatement();
            log.info("Query realizado a la DB: {}", query);
            ResultSet resultSet = statement.executeQuery(query);
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                int i = 1;
                String[] row = new String[columnCount];
                while (i <= columnCount) {
                    row[i - 1] = resultSet.getString(i++);
                }
                result.add(row);
            }
        } catch (SQLException e) {
            log.error("executeQuery(): Error al ejecutar la query: {}", query);
            e.printStackTrace();
            close();
            throw new YaraPlayException(e);
        }
        close();
        return result;
    }

    public boolean executeUpdate(String query) {
        connection = connect();
        assert connection != null;
        try {
            statement = connection.createStatement();
            log.info("executeUpdate(): Query realizado a la DB: {}", query);
            return statement.executeUpdate(query) > 0;
        } catch (SQLException e) {
            log.error("executeUpdate(): Error al ejecutar la query: {}", query);
            e.printStackTrace();
            throw new YaraPlayException(e);
        }
    }

    private void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class YaraPlayException extends RuntimeException {
        public YaraPlayException(SQLException e) {
            super(e.getMessage());
            log.warn("YaraPlayException(): {}", e.getMessage());
        }
    }
}
