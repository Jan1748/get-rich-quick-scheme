package org.getRichFast.Model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.getRichFast.Data.Database.ModelToDatabaseConnection;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;
import org.getRichFast.Model.Entity.DataShifter;
import org.getRichFast.Model.Entity.StockBuild;

public class RequestEditor {

  private ModelToDatabaseConnection databaseConnection = new ModelToDatabaseConnection();

  public void downloadQuandlWholeStockMarket(String symbol, String apiKey) {
    ArrayList<String> symbols = null;
    try {
      symbols = QuandlCodeFinder.getQuandlCodes(symbol, apiKey);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < symbols.size(); i++) {
      ArrayList<StockBuild> stocks = null;
      try {
        stocks = DataShifter.getAndParseData(apiKey, symbols.get(i));
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
      if (stocks != null) {
        databaseConnection.insertDataToDatabase(stocks);
      }
    }
  }

  public void downloadQuandlSingleStock(String symbol, String apiKey) {
    ArrayList<StockBuild> stocks = null;
    try {
      stocks = DataShifter.getAndParseData(apiKey, symbol);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    databaseConnection.insertDataToDatabase(stocks);
  }
}
