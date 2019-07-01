package org.getRichFast.UI.ConsoleUI;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOutput {

  public void outputDatabaseData(ResultSet result) {
    System.out.printf("%-12.12s %-12.12s %-8.8s %-8.8s %-8.8s %-8.8s%n", "Symbol", "Date", "Open", "High", "Low", "Close");
    try {
      while (result.next()) {
        System.out.printf("%-12.12s %-12.12s %-8.8s %-8.8s %-8.8s %-8.8s%n", result.getString("date"), result.getString("symbol"), result.getString("open"), result.getString("high"),
            result.getString("low"), result.getString("close"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //TODO: Add a method that outputs an array with ResultSet

  public void outputDatabaseDataArray(ResultSet[] resultSets){
    String[] value = new String[]{"Open", "High", "Low", "Close"};
    for(int x = 0; x < 4; x++){
      try {
        resultSets[x].next();
        System.out.println(value[x] + ": " + Double.toString(resultSets[x].getDouble("MAX")));
      } catch (SQLException e) {
        try {
          System.out.println(value[x] + ": " + Double.toString(resultSets[x].getDouble("MIN")));
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
