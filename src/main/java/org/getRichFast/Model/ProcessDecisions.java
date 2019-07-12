package org.getRichFast.Model;

import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;

public interface ProcessDecisions {

  void searchForValue(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol);

  void downloadQuandlWholeStockMarket(String symbol, String apiKey);

  void downloadQuandlSingleStock(String symbol, String apiKey);

  void createLineChart(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol, String symbol2);

  void createCandleStickChart(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol);

  void generateAllChartsFromStock(String stockName, String apiKey, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2);

  void visulizeTwoCharts(String stockName, String apiKey, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2);

  void createHistogram(SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol, int accuracy);

  void getSortedPerformingStocksPercent(String stockCode, String quandlApiKey, int numberOfDivisions, DateEnum dateEnum, String date, String date2, SymbolEnum symbolEnum);

  void getSortedPerformingStockAbsolute(String stockCode, String quandlApiKey, int numberOfDivisions, DateEnum dateEnum, String date, String date2, SymbolEnum symbolEnum);
}
