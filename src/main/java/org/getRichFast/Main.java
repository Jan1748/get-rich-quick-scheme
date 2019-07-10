package org.getRichFast;

import java.util.ArrayList;
import java.util.Properties;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.ChartEnum;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Algorithms.Average;
import org.getRichFast.Model.Algorithms.StockPerformanceCalculater;
import org.getRichFast.Model.Entity.PerformingStocks;
import org.getRichFast.Model.InterfaceConnection.InterfaceConnectorToDatabase;
import org.getRichFast.UI.ConsoleUI.Menus;

public class Main {
  public static void main(String[] args) {
    //InterfaceConnectorToDatabase interfaceConnectorToDatabase = new InterfaceConnectorToDatabase();
    //interfaceConnectorToDatabase.generateAllChartsFromStock("FSE", "VAuKhbFRLKYeucyzd868", DateEnum.BEFORE, ColumnNameEnum.ALL,"2015-01-01", null);
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Average.median(databaseConnection.getQueriedDataset(ValueEnum.ALL, SymbolEnum.NULL, DateEnum.BEFORE, ColumnNameEnum.ALL, "2015-01-01", null, "FSE/EON_X"), ColumnNameEnum.OPEN);
    Average.arethmeticMean(databaseConnection.getQueriedDataset(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.BEFORE, ColumnNameEnum.ALL, "2019-01-01", null, "FSE/EON_X"), ColumnNameEnum.OPEN);
//    DatabaseConnection databaseConnection = new DatabaseConnection();
//    Average.median(databaseConnection.getQueriedDataset(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.BEFORE, ColumnNameEnum.ALL, "2015-01-01", null, "FSE/EON_X"), ColumnNameEnum.OPEN);
//    Average.arethmeticMean(databaseConnection.getQueriedDataset(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.BEFORE, ColumnNameEnum.ALL, "2015-01-01", null, "FSE/EON_X"), ColumnNameEnum.OPEN);

    StockPerformanceCalculater stockPerformanceCalculater = new StockPerformanceCalculater();
    ArrayList<PerformingStocks> bestPerformingPercent = stockPerformanceCalculater.getBestPerformingStocksPercent("FSE", "4nAVrexhFHXrX1TuYNsF", 5);
    for (int x = 0; x < bestPerformingPercent.size(); x++){
      System.out.println(bestPerformingPercent.get(x).toString());
    }

    Menus menus = new Menus();
    menus.startMenu();
  }
}
