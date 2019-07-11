package org.getRichFast.Model.Charts;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Entity.StockBuild;
import org.getRichFast.Model.InterfaceConnection.InterfaceConnectorToDatabase;

public class HistogramValues {

  public static  ArrayList<Integer[]> findValuesForHistogram(SymbolEnum symbolEnum, DateEnum dateEnum, String date, String date2, String symbol, int accuracy) {
    ArrayList<Integer[]> histogramChambers = new ArrayList<>(); //Index 0 = Open; Index 1 = High; Index 2 = Low; Index 3 = Close
    ArrayList<Double[]> intervals = new ArrayList<>();
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Double[] max = databaseConnection.search(ValueEnum.MAX, symbolEnum, dateEnum, ColumnNameEnum.ALL, date, date2, symbol);//Index 0 = Open; Index 1 = High; Index 2 = Low; Index 3 = Close
    Double[] min = databaseConnection.search(ValueEnum.MIN, symbolEnum, dateEnum, ColumnNameEnum.ALL, date, date2, symbol);//Index 0 = Open; Index 1 = High; Index 2 = Low; Index 3 = Close
    ArrayList<StockBuild> stocks = databaseConnection.getQueriedDataset(ValueEnum.ALL, symbolEnum, dateEnum, ColumnNameEnum.ALL, date, date2, symbol);
    for (int i = 0; i < 4; i++) {
      histogramChambers.add(new Integer[accuracy]);
      intervals.add(new Double[accuracy]);
      Double intervalDistance = (max[i] - min[i]) / (accuracy-1);
      for (int x = 0; x < accuracy; x++) {
        histogramChambers.get(i)[x] = 0;
        intervals.get(i)[x] = min[i] + (intervalDistance * x);
      }
    }
    for (int i = 0; i < stocks.size(); i++) {
      BigDecimal value = stocks.get(i).getOpen();
      int checking = check(0, value, intervals, accuracy);
      if (value != null && checking != -1) {
        histogramChambers.get(0)[checking]++;
      }
      value = stocks.get(i).getHigh();
      checking = check(1, value, intervals, accuracy);
      if (value != null && checking != -1) {
        histogramChambers.get(1)[checking]++;
      }
      value = stocks.get(i).getLow();
      checking = check(2, value, intervals, accuracy);
      if (value != null && checking != -1) {
        histogramChambers.get(2)[checking]++;
      }
      value = stocks.get(i).getClose();
      checking = check(3, value, intervals, accuracy);
      if (value != null && checking != -1) {
        histogramChambers.get(3)[checking]++;
      }
    }
    return histogramChambers;
  }

  private static Integer check(int Round, BigDecimal val, ArrayList<Double[]> intervals, int accuracy) {
    if (val != null) {
      Double value = val.doubleValue();
      for (int i = 1; i < accuracy; i++) {
        if (intervals.get(Round)[i-1] <= value && intervals.get(Round)[i] >= value) {
          return (i);
        }
      }
    }
    return -1;
  }

}
