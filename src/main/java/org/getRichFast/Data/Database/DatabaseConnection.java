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
  public JDBCCategoryDataset getQueriedDataset(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    String request = DatabaseRequestBuilder.requestBuild(valueEnum, symbolEnum, dateEnum, columnNameEnum,date, date2, symbol);
    try {
      JDBCCategoryDataset jdbcCategoryDataset = new JDBCCategoryDataset(connection, request);
      jdbcCategoryDataset.executeQuery(connection, request);
      return jdbcCategoryDataset;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getValue(ValueEnum valueEnum,SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    return DatabaseRequestBuilder.requestBuild(valueEnum, symbolEnum, dateEnum,columnNameEnum, date, date2, symbol);
  }
}