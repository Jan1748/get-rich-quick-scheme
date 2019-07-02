package org.getRichFast.Model;

import java.util.ArrayList;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;

public interface ProcessDecisions {

  public void searchForValue(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, String date, String date2, String symbol);

  public void downloadQuandlWholeStockMarket(String symbol, String apiKey);

  public void downloadQuandlSingleStock(String symbol, String apiKey);
}
