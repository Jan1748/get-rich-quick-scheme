package org.getRichFast.Model.Algorithms;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;
import org.getRichFast.Model.Entity.PerformingStocks;

public class StockPerformanceCalculater {

  private DataReceiver dataReceiver = new DatabaseConnection();
  private AveragePerIntervalCreator averagePerIntervalCreator = new AveragePerIntervalCreator();

  public ArrayList<PerformingStocks> getPerformanceFromStock(String stockCode, String quandlApiKey, int numberOfDivisions, DateEnum dateEnum, String date, String date2, SymbolEnum symbolEnum) {
    System.out.println("Start calculating stock performance.");
    ArrayList<String> quandlCodesForStock = getQuandlCodesForStocks(stockCode, quandlApiKey);
    ArrayList<PerformingStocks> averageInterval = new ArrayList<>();
    double performancePercent;
    double performanceAbsolute;

    for (int x = 1; x < quandlCodesForStock.size(); x++) {
      BigDecimal[] averagePerInterval = averagePerIntervalCreator.getAveragePerInterval(quandlCodesForStock.get(x), numberOfDivisions, dateEnum, date, date2, symbolEnum);
      if (getPerformancePercent(averagePerInterval) == null) {
        performancePercent = Double.NaN;
      } else {
        performancePercent = getPerformancePercent(averagePerInterval).doubleValue();
      }
      if (getPerformanceAbsolute(averagePerInterval) == null) {
        performanceAbsolute = Double.NaN;
      } else {
        performanceAbsolute = getPerformanceAbsolute(averagePerInterval).doubleValue();
      }
      averageInterval.add(new PerformingStocks(quandlCodesForStock.get(x), performancePercent, performanceAbsolute));
    }
    System.out.println("Finished calculating stock performance.");
    return averageInterval;
  }

  private BigDecimal getPerformancePercent(BigDecimal[] averagePerInterval) {
    BigDecimal performancePercent = null;
    if (averagePerInterval == null) {
      performancePercent = null;
    } else {
      if (averagePerInterval[averagePerInterval.length - 1] != null && averagePerInterval[0] != null) {
        System.out.println(averagePerInterval[0] + " | " + averagePerInterval[averagePerInterval.length - 1]);
        performancePercent = (averagePerInterval[0].divide(averagePerInterval[averagePerInterval.length - 1], 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1))
            .multiply(BigDecimal.valueOf(100)));
      }
    }
    return performancePercent;
  }

  private BigDecimal getPerformanceAbsolute(BigDecimal[] averagePerInterval) {
    BigDecimal performanceAbsolute = null;
    if (averagePerInterval == null) {
      performanceAbsolute = null;
      System.out.println("test");
    } else {
      if (averagePerInterval[averagePerInterval.length - 1] != null && averagePerInterval[0] != null) {
        performanceAbsolute = averagePerInterval[0].subtract(averagePerInterval[averagePerInterval.length - 1]);
      }
    }
    return performanceAbsolute;
  }

  private ArrayList<String> getQuandlCodesForStocks(String stockCode, String quandlApiKey) {
    ArrayList<String> quandlCodesForStock = null;
    try {
      quandlCodesForStock = QuandlCodeFinder.getQuandlCodes(stockCode, quandlApiKey);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return quandlCodesForStock;
  }


}
