package org.getRichFast.Model.Algorithms;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

  public ArrayList<PerformingStocks> getBestPerformingStocksPercent(String stockCode, String quandlApiKey, int numberOfDivisions){
    ArrayList<PerformingStocks> performingStocks = getPerformanceFromStock(stockCode, quandlApiKey, numberOfDivisions);
    return sortPerformingStocksPercent(performingStocks);
  }

  private ArrayList<PerformingStocks> sortPerformingStocksPercent(ArrayList<PerformingStocks> performingStocks){
    PerformingStocks temporary;
    int counterNaN = 0;
    for (int i = 1; i < performingStocks.size(); i++){
      for (int j = 0; j < performingStocks.size() - 1; j++){
        if (performingStocks.get(j).getPerformancePercent() == Double.NaN | performingStocks.get(j).getPerformanceAbsolute() == Double.NaN){
          temporary = new PerformingStocks(performingStocks.get(j).getStockCode(), performingStocks.get(j).getPerformancePercent(), performingStocks.get(j).getPerformanceAbsolute());
          performingStocks.set(j, performingStocks.get(performingStocks.size() - (1 + counterNaN)));
          performingStocks.set(performingStocks.size() - (1 + counterNaN), temporary);
          counterNaN += 1;
          j -= 1;
        }
        if (performingStocks.get(j).getPerformancePercent() > performingStocks.get(j + 1).getPerformancePercent()){
          temporary = new PerformingStocks(performingStocks.get(j).getStockCode(), performingStocks.get(j).getPerformancePercent(), performingStocks.get(j).getPerformanceAbsolute());
          performingStocks.set(j, performingStocks.get(j + 1));
          performingStocks.set(j + 1, temporary);
        }
      }
    }
    return performingStocks;
  }

  private ArrayList getPerformanceFromStock(String stockCode, String quandlApiKey, int numberOfDivisions) {
    ArrayList<String> quandlCodesForStock = getQuandlCodesForStocks(stockCode, quandlApiKey);
    ArrayList<PerformingStocks> averageInterval = new ArrayList<>();
    double performancePercent;
    double performanceAbsolute;

    for (int x = 1; x < quandlCodesForStock.size(); x++) {
      BigDecimal[] averagePerInterval = getAveragePerInterval(quandlCodesForStock.get(x), numberOfDivisions);
      if (getPerformancePercent(averagePerInterval) == null){
        performancePercent = Double.NaN;
      }
      else {
        performancePercent = getPerformancePercent(averagePerInterval).doubleValue();
      }
      if (getPerformanceAbsolute(averagePerInterval) == null){
        performanceAbsolute = Double.NaN;
      }
      else {
        performanceAbsolute = getPerformanceAbsolute(averagePerInterval).doubleValue();
      }
      averageInterval.add(new PerformingStocks(quandlCodesForStock.get(x), performancePercent, performanceAbsolute));
    }
    return averageInterval;
  }

  private BigDecimal getPerformancePercent(BigDecimal[] averagePerInterval){
    BigDecimal performancePercent = null;
    if (averagePerInterval[averagePerInterval.length - 1] == null | averagePerInterval[0] == null){
      performancePercent = null;
    }
    else{
      performancePercent = (averagePerInterval[0].divide(averagePerInterval[averagePerInterval.length - 1], 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1)).multiply(BigDecimal.valueOf(100)));
    }
    return performancePercent;
  }

  private BigDecimal getPerformanceAbsolute(BigDecimal[] averagePerInterval){
    BigDecimal performanceAbsolute = null;
    if (averagePerInterval[averagePerInterval.length - 1] == null | averagePerInterval[0] == null){
      performanceAbsolute = null;
    }
    else{
      performanceAbsolute = averagePerInterval[0].subtract(averagePerInterval[averagePerInterval.length - 1]);
    }
    return performanceAbsolute;
  }


  private BigDecimal[] getAveragePerInterval(String quandlCode, int numberOfDivisions) {
    ArrayList<StockBuild> stock = dataReceiver.getQueriedDataset(ValueEnum.ALL, SymbolEnum.SINGLE, DateEnum.NULL, ColumnNameEnum.ALL, null, null, quandlCode);
    if (stock == null) {
      return null;
    }
    String[][] intervalStartEndDate = getIntervalStartEndDate(numberOfDivisions, stock);
    BigDecimal[] average = new BigDecimal[intervalStartEndDate.length];

    for (int x = 0; x < intervalStartEndDate.length; x++) {
      System.out.println(intervalStartEndDate[x][1] + " | " + intervalStartEndDate[x][0]);
      if (intervalStartEndDate[x][1] == null | intervalStartEndDate[x][0] == null){
        average[x] = null;
      }
      else {
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
        System.out.println(date);
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
