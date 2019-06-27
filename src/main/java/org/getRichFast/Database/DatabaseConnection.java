package org.getRichFast.Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import org.getRichFast.Entity.StockBuild;

public class DatabaseConnection {

  private void connect() {
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Postgresql", "postgres", "Rockstar2015!")) {

      System.out.println("Java JDBC PostgreSQL Example");

      System.out.println("Connected to PostgreSQL database!");
      Statement statement = connection.createStatement();

      String sql = "INSERT INTO stockbuild " + "VALUES ('2019-06-27', 'FSE/EON_X', 89.5, 90.4, 89.5, 91.0)";
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

  public void insertDataToDatabase(ArrayList<StockBuild> stocks) {
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Postgresql", "postgres", "Rockstar2015!")) {
      System.out.println("Connected to PostgreSQL database!");
      Statement statement = connection.createStatement();
      for(int i = 0; i < stocks.size(); i++) {
        StockBuild stock = stocks.get(i);
        java.sql.Date sqlDate = new java.sql.Date(stock.getDate().getTimeInMillis());
        PreparedStatement sql = connection.prepareStatement("INSERT INTO stockbuild VALUES (?,?,?,?,?,?)");
        sql.setString(1, stock.getSymbol());
        sql.setDate(2, sqlDate);
        sql.setBigDecimal(3, stock.getOpen());
        sql.setBigDecimal(4, stock.getHigh());
        sql.setBigDecimal(5, stock.getLow());
        sql.setBigDecimal(6, stock.getClose());
        sql.executeUpdate();
        sql.close();
      }
    } catch (SQLException e) {
      System.out.println("Connection failure.");
      e.printStackTrace();
    }
  }
}
