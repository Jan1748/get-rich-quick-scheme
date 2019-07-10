package org.getRichFast.Model.Algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Model.Entity.StockBuild;

public class Average {

  public static BigDecimal arethmeticMean(ArrayList<StockBuild> stocks, ColumnNameEnum columnNameEnum) {
    BigDecimal result = new BigDecimal(0);
    for (int i = 0; i < stocks.size(); i++) {
      StockBuild stockBuild = stocks.get(i);
      BigDecimal add = check(stockBuild, columnNameEnum);
      if (add != null) {
        result = result.add(add);
      }
    }
    if (stocks.size() >= 1) {
      result = result.divide(new BigDecimal(stocks.size()), RoundingMode.DOWN);
      System.out.println("Average " + result);
      return result;
    }
    return null;
  }

  public static BigDecimal median(ArrayList<StockBuild> stocks, ColumnNameEnum columnNameEnum) {
    ArrayList<BigDecimal> results = new ArrayList<>();
    for (int i = 0; i < stocks.size(); i++) {
      StockBuild stockBuild = stocks.get(i);
      BigDecimal add = check(stockBuild, columnNameEnum);
      if (add != null) {
        results.add(add);
      }
    }
    Collections.sort(results);
    Collections.reverse(results);
    if (results.size() >= 1) {
      BigDecimal result = results.get((results.size() / 2));
      System.out.println("Result = " + result);
      return result;
    }
    return null;
  }
  private static BigDecimal check(StockBuild stockBuild, ColumnNameEnum columnNameEnum){
    if (columnNameEnum == ColumnNameEnum.OPEN && stockBuild.getOpen() != null) {
      return stockBuild.getOpen();
    }
    if (columnNameEnum == ColumnNameEnum.HIGH && stockBuild.getHigh() != null) {
      return stockBuild.getOpen();
    }
    if (columnNameEnum == ColumnNameEnum.LOW && stockBuild.getLow() != null) {
      return stockBuild.getOpen();
    }
    if (columnNameEnum == ColumnNameEnum.CLOSE && stockBuild.getClose() != null) {
       return stockBuild.getOpen();
    }
    return null;
  }

  public static void geometricMean(ArrayList<StockBuild> stocks, ColumnNameEnum columnNameEnum) {

  }



}
