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

public class Histogramm {

  public static void createHistogramm(SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    ArrayList<Integer[]> histogrammChambers = new ArrayList<>(); //Index 0 = Open; Index 1 = High; Index 2 = Low; Index 3 = Close
    ArrayList<Double[]> intervalls = new ArrayList<>();
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Double[] max = databaseConnection.search(ValueEnum.MAX, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);//Index 0 = Open; Index 1 = High; Index 2 = Low; Index 3 = Close
    Double[] min = databaseConnection.search(ValueEnum.MIN, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);//Index 0 = Open; Index 1 = High; Index 2 = Low; Index 3 = Close
    ArrayList<StockBuild> stocks = databaseConnection.getQueriedDataset(ValueEnum.ALL, symbolEnum, dateEnum, ColumnNameEnum.ALL, date, date2, symbol);
    for (int i = 0; i < 4; i++) {
      histogrammChambers.add(new Integer[15]);
      intervalls.add(new Double[15]);
      Double intervalDistance = (max[i] - min[i]) / 14;
      for (int x = 0; x < 15; x++){
        intervalls.get(i)[x] = min[i] + (intervalDistance * x);
      }
    }
    //for (int i = 0; i < stocks.size(); i++){
    //  check(i, stocks.get())
    //}

  }
  private static Integer check(int Round, BigDecimal value){
    return null;
  }

}
