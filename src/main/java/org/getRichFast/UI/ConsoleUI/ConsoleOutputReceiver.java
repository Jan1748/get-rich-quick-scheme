package org.getRichFast.UI.ConsoleUI;

import java.util.ArrayList;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Model.Algorithms.StockPerformanceCalculater;
import org.getRichFast.Model.Entity.PerformingStocks;
import org.getRichFast.UI.UIReceiver;

public class ConsoleOutputReceiver implements UIReceiver {

  @Override
  public void outputStringToConsole(String output) {
    System.out.println(output);
  }

  @Override
  public void valueConsoleOutput(Double[] values) {
    String[] names = new String[]{"Open", "High", "Low", "Close"};
    for (int i = 0; i < 4; i++) {
      System.out.println(names[i] + ": " + values[i]);
    }
    System.out.println(" ");
  }
  @Override
  public void outputPerformingFromStocks(ArrayList<PerformingStocks> sortedPerformingFromStocks) {
    for (int x = 0; x < sortedPerformingFromStocks.size(); x++) {
      System.out.println("Stock: " + sortedPerformingFromStocks.get(x).getStockCode() + "Percent profit: " + sortedPerformingFromStocks.get(x).getPerformancePercent() + "Performance profit: " + sortedPerformingFromStocks.get(x).getPerformanceAbsolute());
    }
  }
}
