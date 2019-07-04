package org.getRichFast.Data.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Entity.StockBuild;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class DatabaseConnection implements DataReceiver {

  private String user;
  private String password;
  private String databaseName;
  private Connection connection;
  private DatabaseToModelConnection databaseToModel = new DatabaseToModel();

  public DatabaseConnection(){
    initialize();
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

  public Connection connect() {
    String url = "jdbc:postgresql://localhost:5432/" + databaseName;
    try {
      return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      System.out.println("Failed to connect to Database");
      //e.printStackTrace();
    }
    return null;
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

  public void insertDataToDatabase(ArrayList<StockBuild> stocks) {
    DatabaseInserter.insertDataToDatabase(stocks, connection);
  }

  @Override
  public void search(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum,ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    String request = DatabaseRequestBuilder.requestBuild(valueEnum, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);
    try {
      Double[] datas = QueryData.getQueriedData(request, this.connection);
      databaseToModel.outputValues(datas);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ArrayList<StockBuild> getQueriedDataset(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date1, String date2, String symbol) {
    String request = DatabaseRequestBuilder.requestBuild(valueEnum, symbolEnum, dateEnum, columnNameEnum,date1, date2, symbol);
      Connection connection = connect();
      ArrayList<StockBuild> stocks = new ArrayList<>();
      if(connection != null){
        Statement statement = null;
        try {
          statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(request);
          Date date;
          while (resultSet.next()) {
            Calendar cal = Calendar.getInstance();
            date = convertDate(resultSet.getDate("Date"));
            cal.setTime(date);
            StockBuild stockBuild = new StockBuild(resultSet.getString("symbol"), cal);
            stockBuild.setOpen(resultSet.getBigDecimal("open"));
            stockBuild.setHigh(resultSet.getBigDecimal("high"));
            stockBuild.setLow(resultSet.getBigDecimal("low"));
            stockBuild.setClose(resultSet.getBigDecimal("close"));
            stocks.add(stockBuild);
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
     }
      return stocks;
    }

  public String getValue(ValueEnum valueEnum,SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    return DatabaseRequestBuilder.requestBuild(valueEnum, symbolEnum, dateEnum,columnNameEnum, date, date2, symbol);
  }
  private java.util.Date convertDate(java.sql.Date sqldate){
    return new java.util.Date(sqldate.getTime());
  }

}