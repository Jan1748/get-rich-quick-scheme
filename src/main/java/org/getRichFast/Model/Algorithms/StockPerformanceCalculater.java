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
import org.getRichFast.Model.Entity.StockBuild;

public class StockPerformanceCalculater {

  private DataReceiver dataReceiver = new DatabaseConnection();

  public ArrayList<String> getTenBestPerforming

  private ArrayList getPerformanceFromStock(String stockCode, String quandlApiKey, int numberOfDivisions) {
    ArrayList<String> quandlCodesForStock = getQuandlCodesForStocks(stockCode, quandlApiKey);
    ArrayList<String[]> averageInterval = new ArrayList<>();
    String performancePercentString = "";
    String performanceAbsoluteString = "";

    for (int x = 1; x < quandlCodesForStock.size(); x++) {
      BigDecimal[] averagePerInterval = getAveragePerInterval(quandlCodesForStock.get(x), numberOfDivisions);
      String[] performance = new String[3];
      performancePercentString = getPerformancePercentString(averagePerInterval);
      performanceAbsoluteString = gerPerformanceAbsoluteString(averagePerInterval);

      performance[0] = quandlCodesForStock.get(x);
      performance[1] = performancePercentString;
      performance[2] = performanceAbsoluteString;

      averageInterval.add(performance);
    }
    return averageInterval;
  }

  private String getPerformancePercentString(BigDecimal[] averagePerInterval){
    String performancePercentString = "";
    if (averagePerInterval[averagePerInterval.length - 1] == null | averagePerInterval[0] == null){
      performancePercentString = "n/a";
    }
    else{
      BigDecimal performancePercent = (averagePerInterval[0].divide(averagePerInterval[averagePerInterval.length - 1], 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1)).multiply(BigDecimal.valueOf(100)));
      System.out.println(performancePercent);
      performancePercentString = performancePercent.toString();
    }
    return performancePercentString;
  }

  private String gerPerformanceAbsoluteString(BigDecimal[] averagePerInterval){
    String performanceAbsoluteString = "";
    if (averagePerInterval[averagePerInterval.length - 1] == null | averagePerInterval[0] == null){
      performanceAbsoluteString = "n/a";
    }
    else{
      BigDecimal performanceAbsolute = averagePerInterval[0].subtract(averagePerInterval[averagePerInterval.length - 1]);
      performanceAbsoluteString = performanceAbsolute.toString();
    }
    return performanceAbsoluteString;
  }


  private BigDecimal[] getAveragePerInterval(String quandlCode, int numberOfDivisions) {
    ArrayList<StockBuild> stock = dataReceiver.getQueriedDataset(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.BEFORE, ColumnNameEnum.ALL, "2010-01-01", null, quandlCode);
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
