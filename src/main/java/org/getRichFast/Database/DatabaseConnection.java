package org.getRichFast.Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

  public void connect() {
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Postgresql", "postgres", "Rockstar2015!")) {

      System.out.println("Java JDBC PostgreSQL Example");
      // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
      // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
//          Class.forName("org.postgresql.Driver");

      System.out.println("Connected to PostgreSQL database!");
      Statement statement = connection.createStatement();

      String sql = "INSERT INTO stockbuild " + "VALUES (6, '2019-06-27', 'FSE/EON_X', 89.5, 90.4, 89.5, 91.0)";
      statement.executeUpdate(sql);

      System.out.printf("%-10.10s  %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s%n", "datum", "symbol", "open", "high", "low", "close");
      ResultSet resultSet = statement.executeQuery("SELECT * FROM public.stockbuild");
      while (resultSet.next()) {
        System.out
            .printf("%-10.10s  %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s%n", resultSet.getString("datum"), resultSet.getString("symbol"), resultSet.getString("open"), resultSet.getString("high"),
                resultSet.getString("low"), resultSet.getString("close"));
      }

    } catch (SQLException e) {
      System.out.println("Connection failure.");
      e.printStackTrace();
    }
  }
}
