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
import java.util.Calendar;
import org.getRichFast.Entity.StockBuild;
import org.postgresql.util.PSQLException;

public class DatabaseConnection {
  private String user = "postgres";
  private String password = "Rockstar2015!";
  private String databaseName = "postgres";

  public void outputAllDataFromDatabase() {
    Connection connection = connect();
    if (connection != null) {
      Statement statement = null;
      try {
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM public.stockbuild");
        System.out.printf("%-12.12s %-12.12s %-8.8s %-8.8s %-8.8s %-8.8s%n", "Symbol", "Date", "Open", "High", "Low", "Close");
        while (resultSet.next()) {
          System.out
              .printf("%-12.12s %-12.12s %-8.8s %-8.8s %-8.8s %-8.8s%n", resultSet.getString("date"), resultSet.getString("symbol"), resultSet.getString("open"), resultSet.getString("high"),
                  resultSet.getString("low"), resultSet.getString("close"));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void insertDataToDatabase(ArrayList<StockBuild> stocks) {
    Connection connection = connect();
    if (connection != null) {
      System.out.println("Connected to PostgreSQL database!");
      try {
        Statement statement = connection.createStatement();
        int counter = 0;
        int succesfullCounter = 0;
        for (int i = 0; i < stocks.size(); i++) {
          StockBuild stock = stocks.get(i);
          java.sql.Date sqlDate = new java.sql.Date(stock.getDate().getTimeInMillis());
          PreparedStatement sql = null;
          sql = connection.prepareStatement("INSERT INTO stockbuild VALUES (?,?,?,?,?,?)");
          sql.setString(1, stock.getSymbol());
          sql.setDate(2, sqlDate);
          sql.setBigDecimal(3, stock.getOpen());
          sql.setBigDecimal(4, stock.getHigh());
          sql.setBigDecimal(5, stock.getLow());
          sql.setBigDecimal(6, stock.getClose());
          try {
            sql.executeUpdate();
            succesfullCounter++;
          } catch (PSQLException e) {
            counter++;
          }
          sql.close();
        }
        if(counter != 0) {
          System.out.println("\nDuplicated Data! " + counter + " Database entry's are already inside the Database.\n" +succesfullCounter + " New entry's were added\nTotal entry's: " + stocks.size() +"\n");
        }else {
          System.out.println("\nAll " + stocks.size() +" entry's were successfully added\n");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public Connection connect() {
    String url = "jdbc:postgresql://localhost:5432/" + databaseName;
    try {
      return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      System.out.println("Failed to connect to Database");
      e.printStackTrace();
    }
    return null;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }
}
