package org.getRichFast.Model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.DatabaseInserter;
import org.getRichFast.Data.Database.DateEnum;
import org.getRichFast.Data.Database.ValueEnum;
import org.getRichFast.Model.Entity.DataShifter;
import org.getRichFast.Model.Entity.StockBuild;

public class RequestEditor implements ProcessDecisions {

  private DatabaseConnection databaseConnection = new DatabaseConnection();

  @Override
  public void searchForValue(ValueEnum valueEnum, DateEnum dateEnum, String date, String date2, String symbol) {

  }

  @Override
  public void downloadQuandlWholeStockMarket(ArrayList<String> symbols, String apiKey) {
    for (int i = 0; i < symbols.size(); i++) {
      ArrayList<StockBuild> stocks = null;
      try {
        stocks = DataShifter.getAndParseData(apiKey, symbols.get(i));
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParseException e) {
        System.out.println("No Valuable Stock");
      }
      if (stocks != null) {
        databaseConnection.insertDataToDatabase(stocks);
      }
    }
  }

  @Override
  public void downloadQuandlSingleStock(String symbol, String apiKey) {
    ArrayList<StockBuild> stocks = null;
    try {
      stocks = DataShifter.getAndParseData(apiKey, symbol);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      System.out.println("No Valuable Stock");
    }
    if (stocks != null) {
      databaseConnection.insertDataToDatabase(stocks);
    }
  }
}
