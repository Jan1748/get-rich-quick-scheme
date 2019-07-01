package org.getRichFast.Model.Searching;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.UI.ConsoleUI.InputFunctions;

public class SymbolSearcher {

  public ResultSet searchForSymbol() {
    return getSymbolPostgresql(InputFunctions.scan("What symbol do you want to search for?"));
  }

  private ResultSet getSymbolPostgresql(String symbol) {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    PreparedStatement sqlCode;
    ResultSet resultSet = null;

    try {
      sqlCode = databaseConnection.connect().prepareStatement("SELECT * FROM stockbuild WHERE \"Symbol\" = ?;");
      sqlCode.setString(1, symbol);
      resultSet = sqlCode.executeQuery();
      return resultSet;
    } catch (SQLException e) {
      System.out.println("The symbol " + symbol + " is not in the database");
    }

    return resultSet;
  }

  public String symbolCondition(){
    return getSymbolCondition(InputFunctions.scan("With which symbol do you want to search?"));
  }

  private String getSymbolCondition(String symbol){
    String symbolCode = "AND \"Symbol\" = '" + symbol + "'";
    return symbolCode;
  }
}
