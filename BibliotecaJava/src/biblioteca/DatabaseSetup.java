package biblioteca;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void createTables() {
        Connection connection = SQLiteConnector.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                // Tabela para livros
                statement.execute("CREATE TABLE IF NOT EXISTS Livros (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "titulo TEXT NOT NULL," +
                        "autor TEXT NOT NULL," +
                        "isbn TEXT NOT NULL)");

                // Tabela para usuários
                statement.execute("CREATE TABLE IF NOT EXISTS Usuarios (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT NOT NULL)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
