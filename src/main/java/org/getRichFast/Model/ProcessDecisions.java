package org.getRichFast.Model;

import java.util.ArrayList;
import org.getRichFast.Data.Database.DateEnum;

public interface ProcessDecisions {
  public void searchForHighestValue(DateEnum dateEnum, String date, String date2, String symbol);
  public void searchForLowestValue(DateEnum dateEnum, String date, String date2, String symbol);

  public void downloadQuandlWholeStockMarket(ArrayList<String> symbols, String apiKey);
  public void downloadQuandlSingleStock(String symbol, String apiKey);
}
