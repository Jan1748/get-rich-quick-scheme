package org.getRichFast.Model.Searching;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.UI.ConsoleUI.InputFunctions;

public class SymbolSearcher {

  public String symbolCondition(){
    return getSymbolCondition(InputFunctions.scan("With which symbol do you want to search?"));
  }

  private String getSymbolCondition(String symbol){
    String symbolCode = "AND \"Symbol\" = '" + symbol + "'";
    return symbolCode;
  }
}
