package org.getRichFast;

import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Algorithms.Average;
import org.getRichFast.Model.Algorithms.StockPerformanceCalculater;
import org.getRichFast.UI.ConsoleUI.Menus;

public class Main {
  public static void main(String[] args) {
//    DatabaseConnection databaseConnection = new DatabaseConnection();
//    Average.median(databaseConnection.getQueriedDataset(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.BEFORE, ColumnNameEnum.ALL, "2015-01-01", null, "FSE/EON_X"), ColumnNameEnum.OPEN);
//    Average.arethmeticMean(databaseConnection.getQueriedDataset(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.BEFORE, ColumnNameEnum.ALL, "2015-01-01", null, "FSE/EON_X"), ColumnNameEnum.OPEN);

    StockPerformanceCalculater stockPerformanceCalculater = new StockPerformanceCalculater();
    stockPerformanceCalculater.getBestPerformingStock("FSE", "4nAVrexhFHXrX1TuYNsF", 5);

    Menus menus = new Menus();
    menus.startMenu();
  }
}
