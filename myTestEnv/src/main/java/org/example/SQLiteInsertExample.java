package org.example;

import java.sql.*;

public class SQLiteInsertExample {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:mydatabase.db";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT NOT NULL);";

        String insertSQL = "INSERT INTO users(name, email) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // 1. Создаём таблицу, если нет
            stmt.execute(createTableSQL);

            // 2. Подготавливаем запрос
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, "Иван");
                pstmt.setString(2, "ivan@example.com");
                pstmt.executeUpdate();
                System.out.println("Данные успешно записаны.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
