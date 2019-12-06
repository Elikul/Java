package Lesson_6.server;

import java.sql.*;

//класс авторизации
public class AuthService {
    private static Connection connection;
    private static Statement stmt;


    // метод для подключения к БД
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");  // строка подключения
            stmt = connection.createStatement(); // установка подключения
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // метод для запроса пользователя из БД, если искомый пользоватль есть
    // то вернется ник если его нету то вернется null
    public static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("select nickname from users " +
                "where login = '%s' and password = '%s'", login, pass);

        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // метод для отключения от БД
    public static void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
