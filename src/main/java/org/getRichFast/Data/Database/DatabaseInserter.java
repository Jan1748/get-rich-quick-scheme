package org.getRichFast.Data.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.getRichFast.Data.Entity.StockBuild;
import org.postgresql.util.PSQLException;

public class DatabaseInserter {
  public static void insertDataToDatabase(ArrayList<StockBuild> stocks, Connection connection) {
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

}
