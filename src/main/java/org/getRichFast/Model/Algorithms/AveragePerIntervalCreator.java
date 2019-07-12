package org.getRichFast.Model.Algorithms;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Entity.StockBuild;

public class AveragePerIntervalCreator {

  private DataReceiver dataReceiver = new DatabaseConnection();

  public BigDecimal[] getAveragePerInterval(String quandlCode, int numberOfDivisions, DateEnum dateEnum, String date, String date2, SymbolEnum symbolEnum) {
    ArrayList<StockBuild> stock = dataReceiver.getQueriedDataset(ValueEnum.ALL, symbolEnum, dateEnum, ColumnNameEnum.ALL, date, date2, quandlCode);
    if (stock == null) {
      System.out.println("test");
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
