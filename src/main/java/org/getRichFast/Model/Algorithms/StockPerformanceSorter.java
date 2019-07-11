package org.getRichFast.Model.Algorithms;

import java.util.ArrayList;
import org.getRichFast.Model.Entity.PerformingStocks;

public class StockPerformanceSorter {

  public static ArrayList<PerformingStocks> sortPerformingStocksPercent(ArrayList<PerformingStocks> performingStocks) {
    System.out.println("Start sorting");
    PerformingStocks temporary;
    for (int i = 1; i < performingStocks.size(); i++) {
      for (int j = 0; j < performingStocks.size() - i; j++) {
        if (Double.isNaN(performingStocks.get(j).getPerformancePercent()) | Double.isNaN(performingStocks.get(j).getPerformancePercent())) {
          temporary = new PerformingStocks(performingStocks.get(j).getStockCode(), performingStocks.get(j).getPerformancePercent(), performingStocks.get(j).getPerformanceAbsolute());
          performingStocks.remove(j);
          performingStocks.add(temporary);
        } else {
          if (performingStocks.get(j).getPerformancePercent() < performingStocks.get(j + 1).getPerformancePercent()) {
            temporary = new PerformingStocks(performingStocks.get(j).getStockCode(), performingStocks.get(j).getPerformancePercent(), performingStocks.get(j).getPerformanceAbsolute());
            performingStocks.set(j, performingStocks.get(j + 1));
            performingStocks.set(j + 1, temporary);
          }
        }
      }
    }
    System.out.println("Finished sorting");
    return performingStocks;
  }

  public static ArrayList<PerformingStocks> sortPerformingStocksAbsolute(ArrayList<PerformingStocks> performingStocks) {
    System.out.println("Start sorting");
    PerformingStocks temporary;
    for (int i = 1; i < performingStocks.size(); i++) {
      for (int j = 0; j < performingStocks.size() - i; j++) {
        if (Double.isNaN(performingStocks.get(j).getPerformanceAbsolute()) | Double.isNaN(performingStocks.get(j).getPerformanceAbsolute())) {
          temporary = new PerformingStocks(performingStocks.get(j).getStockCode(), performingStocks.get(j).getPerformancePercent(), performingStocks.get(j).getPerformanceAbsolute());
          performingStocks.remove(j);
          performingStocks.add(temporary);
        } else {
          if (performingStocks.get(j).getPerformanceAbsolute() < performingStocks.get(j + 1).getPerformanceAbsolute()) {
            temporary = new PerformingStocks(performingStocks.get(j).getStockCode(), performingStocks.get(j).getPerformancePercent(), performingStocks.get(j).getPerformanceAbsolute());
            performingStocks.set(j, performingStocks.get(j + 1));
            performingStocks.set(j + 1, temporary);
          }
        }
      }
    }
    System.out.println("Finished sorting");
    return performingStocks;
  }

}
