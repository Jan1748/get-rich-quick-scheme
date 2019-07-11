package org.getRichFast.Model.Algorithms;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;
import org.getRichFast.Model.Entity.PerformingStocks;
import org.getRichFast.Model.Entity.StockBuild;

public class StockPerformanceCalculater {

  private DataReceiver dataReceiver = new DatabaseConnection();

//  public ArrayList<PerformingStocks> getBestPerformingStocksPercent(String stockCode, String quandlApiKey, int numberOfDivisions) {
//    System.out.println("Start getting performance from stock");
//    ArrayList<PerformingStocks> performingStocks = getPerformanceFromStock(stockCode, quandlApiKey, numberOfDivisions);
//    return sortPerformingStocksPercent(performingStocks);
//  }
//
//  public ArrayList<PerformingStocks> getBestPerformingStocksAbsolute(String stockCode, String quandlApiKey, int numberOfDivisions) {
//    System.out.println("Start getting performance from stock");
//    ArrayList<PerformingStocks> performingStocks = getPerformanceFromStock(stockCode, quandlApiKey, numberOfDivisions);
//    return sortPerformingStocksAbsolute(performingStocks);
//  }



  public ArrayList<PerformingStocks> getPerformanceFromStock(String stockCode, String quandlApiKey, int numberOfDivisions, DateEnum dateEnum, String date, String date2) {
    System.out.println("Start calculating stock performance.");
    ArrayList<String> quandlCodesForStock = getQuandlCodesForStocks(stockCode, quandlApiKey);
    ArrayList<PerformingStocks> averageInterval = new ArrayList<>();
    double performancePercent;
    double performanceAbsolute;

    for (int x = 1; x < quandlCodesForStock.size(); x++) {
      BigDecimal[] averagePerInterval = getAveragePerInterval(quandlCodesForStock.get(x), numberOfDivisions, dateEnum, date, date2);
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
    if (averagePerInterval[averagePerInterval.length - 1] == null | averagePerInterval[0] == null) {
      performancePercent = null;
    } else {
      performancePercent = (averagePerInterval[0].divide(averagePerInterval[averagePerInterval.length - 1], 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1)).multiply(BigDecimal.valueOf(100)));
    }
    return performancePercent;
  }

  private BigDecimal getPerformanceAbsolute(BigDecimal[] averagePerInterval) {
    BigDecimal performanceAbsolute = null;
    if (averagePerInterval[averagePerInterval.length - 1] == null | averagePerInterval[0] == null) {
      performanceAbsolute = null;
    } else {
      performanceAbsolute = averagePerInterval[0].subtract(averagePerInterval[averagePerInterval.length - 1]);
    }
    return performanceAbsolute;
  }


  private BigDecimal[] getAveragePerInterval(String quandlCode, int numberOfDivisions, DateEnum dateEnum, String date, String date2) {
    ArrayList<StockBuild> stock = dataReceiver.getQueriedDataset(ValueEnum.ALL, SymbolEnum.SINGLE, dateEnum, ColumnNameEnum.ALL, date, date2, quandlCode);
    if (stock == null) {
      return null;
    }
    String[][] intervalStartEndDate = getIntervalStartEndDate(numberOfDivisions, stock);
    BigDecimal[] average = new BigDecimal[intervalStartEndDate.length];

    for (int x = 0; x < intervalStartEndDate.length; x++) {
      if (intervalStartEndDate[x][1] == null | intervalStartEndDate[x][0] == null) {
        average[x] = null;
      } else {
        average[x] = Average.median(dataReceiver
                .getQueriedDataset(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.INTERVAL, ColumnNameEnum.ALL, intervalStartEndDate[x][1], intervalStartEndDate[x][0], quandlCode),
            ColumnNameEnum.OPEN);
      }
    }
    return average;
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

  private String[][] getIntervalStartEndDate(int numberOfDivisions, ArrayList<StockBuild> stockBuild) {
    String[][] intervalStartEndDate = new String[numberOfDivisions][2];
    int blend = stockBuild.size() % numberOfDivisions;
    int numberOfIntervals = stockBuild.size() / numberOfDivisions;
    int y = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    for (int x = 0; x < stockBuild.size() - blend; x += numberOfIntervals) {
      Date date;
      if (y == 0) {
        date = stockBuild.get(x).getDate().getTime();
        intervalStartEndDate[y][0] = simpleDateFormat.format(date);
        date = stockBuild.get(x + numberOfIntervals).getDate().getTime();
        intervalStartEndDate[y][1] = simpleDateFormat.format(date);
      } else {
        date = stockBuild.get(x).getDate().getTime();
        intervalStartEndDate[y][0] = simpleDateFormat.format(date);
        if (x + numberOfIntervals >= stockBuild.size()) {
          date = stockBuild.get(stockBuild.size() - 1).getDate().getTime();
          intervalStartEndDate[y][1] = simpleDateFormat.format(date);
        } else {
          date = stockBuild.get(x + numberOfIntervals).getDate().getTime();
          intervalStartEndDate[y][1] = simpleDateFormat.format(date);
        }
      }
      y += 1;
    }
    return intervalStartEndDate;
  }
}
