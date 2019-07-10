package org.getRichFast.Model.Algorithms;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.crypto.Data;
import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;
import org.getRichFast.Model.Entity.StockBuild;
import org.getRichFast.Model.InterfaceConnection.InterfaceConnectorToDatabase;

public class StockPerformanceCalculater {

  private InterfaceConnectorToDatabase interfaceConnectorToDatabase = new InterfaceConnectorToDatabase();
  private DataReceiver dataReceiver = new DatabaseConnection();;

  public ArrayList getBestPerformingStock(String stockCode, String quandlApiKey, int numberOfDivisions) {
    ArrayList<String> quandlCodesForStock = getQuandlCodesForStocks(stockCode, quandlApiKey);
    ArrayList<BigDecimal> averageInterval = null;

    for (int x = 1; x < quandlCodesForStock.size(); x++) {

      String[][] intervalStartEndDate = getIntervalStartEndDate(numberOfDivisions,
          dataReceiver.getQueriedDataset(ValueEnum.ALL, SymbolEnum.SINGLE, DateEnum.NULL, ColumnNameEnum.ALL, null, null, quandlCodesForStock.get(x)));
      for (int xx = 0; xx < intervalStartEndDate.length; xx++) {
        averageInterval.add(Average.median(dataReceiver
                .getQueriedDataset(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.INTERVAL, ColumnNameEnum.ALL, intervalStartEndDate[xx][1], intervalStartEndDate[xx][0], quandlCodesForStock.get(x)),
            ColumnNameEnum.OPEN));
      }
    }
    System.out.println(averageInterval);
    return averageInterval;
  }

  private ArrayList<String> getQuandlCodesForStocks(String stockCode, String quandlApiKey){
    ArrayList<String> quandlCodesForStock = null;
    try {
      quandlCodesForStock = QuandlCodeFinder.getQuandlCodes(stockCode, quandlApiKey);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return quandlCodesForStock;
  }

  private String[][] getIntervalStartEndDate(int numberOfDivisions, ArrayList<StockBuild> stockBuild){
    String[][] intervalStartEndDate = new String[numberOfDivisions][2];
    int blend = stockBuild.size() % numberOfDivisions;
    int numberOfIntervals = stockBuild.size() / numberOfDivisions;
    int y = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    for (int x = 0; x < stockBuild.size() - blend; x += numberOfIntervals){
      Date date;
      if (y == 0){
        date = stockBuild.get(x).getDate().getTime();
        intervalStartEndDate[y][0] = simpleDateFormat.format(date);
        date = stockBuild.get(x + numberOfIntervals).getDate().getTime();
        intervalStartEndDate[y][1] = simpleDateFormat.format(date);
      }
      else {
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
