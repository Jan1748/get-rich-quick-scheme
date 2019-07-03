package org.getRichFast.Model;

import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;

public interface ProcessDecisions {

  void searchForValue(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, String date, String date2, String symbol);

  void downloadQuandlWholeStockMarket(String symbol, String apiKey);

  void downloadQuandlSingleStock(String symbol, String apiKey);
}
