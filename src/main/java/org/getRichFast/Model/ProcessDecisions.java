package org.getRichFast.Model;

import java.util.ArrayList;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;

public interface ProcessDecisions {
  public void searchForValue(ValueEnum valueEnum, DateEnum dateEnum, String date, String date2, String symbol);


  public void downloadQuandlWholeStockMarket(ArrayList<String> symbols, String apiKey);
  public void downloadQuandlSingleStock(String symbol, String apiKey);
}
