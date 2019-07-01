package org.getRichFast.Data.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import org.getRichFast.Data.DataReceiver;
import org.postgresql.util.PSQLException;
import org.getRichFast.Data.Entity.StockBuild;

public class DatabaseConnection implements DataReceiver {
  private String user;
  private String password;
  private String databaseName;


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
    System.out.println("User: " + user);
    try {
      return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      System.out.println("Failed to connect to Database");
      //e.printStackTrace();
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

  @Override
  public void initialize() {
    try {
      InputStream input = new FileInputStream("src/main/resources/config.properties");
      Properties prop =new Properties();
      prop.load(input);
      setUser(prop.getProperty("db.user"));
      setDatabaseName(prop.getProperty("db.name"));
      setPassword(prop.getProperty("db.password"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    connect();
  }

  @Override
  public void getMaximalDateValue() {

  }

  @Override
  public void getMaximalDateSymbolValue() {

  }

  @Override
  public void getMaximalSymbolValue() {

  }

  @Override
  public void getMaximalValue() {

  }

  @Override
  public void getMinimalDateValue() {

  }

  @Override
  public void getMinimalDateSymbolValue() {

  }

  @Override
  public void getMinimalSymbolValue() {

  }

  @Override
  public void getMinimalValue() {

  }
}
