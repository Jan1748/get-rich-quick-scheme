package org.getRichFast.Searching;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.getRichFast.Database.DatabaseConnection;

public class SymbolSearcher {

  DatabaseConnection databaseConnection = new DatabaseConnection();
  Scanner scanner = new Scanner(System.in);

  public ResultSet searchForSymbol() {
    String symbol;
    String sqlCode;
    ResultSet result;
    Statement statement;
    try {
      statement = databaseConnection.connect().createStatement();
      System.out.println("What symbol do you want to search for?");
      symbol = scanner.nextLine();
      sqlCode = "SELECT * FROM stockbuild WHERE \"Symbol\" = " + symbol + ";";
      result = statement.executeQuery(sqlCode);
      System.out.printf("%-12.12s %-12.12s %-8.8s %-8.8s %-8.8s %-8.8s%n", "Symbol", "Date", "Open", "High", "Low", "Close");
      while (result.next()) {
        System.out
            .printf("%-12.12s %-12.12s %-8.8s %-8.8s %-8.8s %-8.8s%n", result.getString("date"), result.getString("symbol"), result.getString("open"), result.getString("high"),
                result.getString("low"), result.getString("close"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
