package org.getRichFast.Data.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Model.Entity.StockBuild;

public class DatabaseConnection implements DataReceiver {

  private String user;
  private String password;
  private String databaseName;
  private Connection connection;

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
      Properties prop = new Properties();
      prop.load(input);
      setUser(prop.getProperty("db.user"));
      setDatabaseName(prop.getProperty("db.name"));
      setPassword(prop.getProperty("db.password"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.connection = connect();
  }

  @Override
  public void insertDataToDatabase(ArrayList<StockBuild> stocks) {
    DatabaseInserter.insertDataToDatabase(stocks, connection);
  }

  @Override
  public void getMaximalDateValue(ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date1) {
    DatabaseRequestBuilder.requestBuild(ValueEnum.MAX, columNameEnum, dateEnum, date, date1, null);
  }

  @Override
  public void getMaximalDateSymbolValue(ColumNameEnum columNameEnum, DateEnum dateEnum, String date,String date1, String symbol) {
    DatabaseRequestBuilder.requestBuild(ValueEnum.MAX, columNameEnum, dateEnum, date, date1, symbol);

  }

  @Override
  public void getMaximalSymbolValue(ColumNameEnum columNameEnum, String symbol) {
    DatabaseRequestBuilder.requestBuild(ValueEnum.MAX, columNameEnum, DateEnum.NULL, null, null, symbol);

  }

  @Override
  public void getMaximalValue(ColumNameEnum columNameEnum) {
    DatabaseRequestBuilder.requestBuild(ValueEnum.MAX, columNameEnum, DateEnum.NULL, null, null, null);
  }

  @Override
  public void getMinimalDateValue(ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date1) {
    DatabaseRequestBuilder.requestBuild(ValueEnum.MIN, columNameEnum, DateEnum.NULL, date, date1, null);

  }

  @Override
  public void getMinimalDateSymbolValue(ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date1, String symbol) {
    DatabaseRequestBuilder.requestBuild(ValueEnum.MIN, columNameEnum, DateEnum.NULL, date, date1, symbol);

  }

  @Override
  public void getMinimalSymbolValue(ColumNameEnum columNameEnum,String symbol) {
    DatabaseRequestBuilder.requestBuild(ValueEnum.MIN, columNameEnum, DateEnum.NULL, null, null, symbol);
  }

  @Override
  public void getMinimalValue(ColumNameEnum columNameEnum) {
    DatabaseRequestBuilder.requestBuild(ValueEnum.MIN, columNameEnum, DateEnum.NULL, null, null, null);
  }

}