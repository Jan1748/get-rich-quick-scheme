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
      if (columnNameEnum == ColumnNameEnum.OPEN && stockBuild.getOpen() != null) {
        BigDecimal add = stockBuild.getOpen();
        result = result.add(add);
      }
      if (columnNameEnum == ColumnNameEnum.HIGH && stockBuild.getHigh() != null) {
        BigDecimal add = stockBuild.getOpen();
        result = result.add(add);
      }
      if (columnNameEnum == ColumnNameEnum.LOW && stockBuild.getLow() != null) {
        BigDecimal add = stockBuild.getOpen();
        result = result.add(add);
      }
      if (columnNameEnum == ColumnNameEnum.CLOSE && stockBuild.getClose() != null) {
        BigDecimal add = stockBuild.getOpen();
        result = result.add(add);
      }
      //System.out.println("Result " + result);
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
      if (columnNameEnum == ColumnNameEnum.OPEN && stockBuild.getOpen() != null) {
        BigDecimal add = stockBuild.getOpen();
        results.add(add);
      }
      if (columnNameEnum == ColumnNameEnum.HIGH && stockBuild.getHigh() != null) {
        BigDecimal add = stockBuild.getOpen();
        results.add(add);
      }
      if (columnNameEnum == ColumnNameEnum.LOW && stockBuild.getLow() != null) {
        BigDecimal add = stockBuild.getOpen();
        results.add(add);
      }
      if (columnNameEnum == ColumnNameEnum.CLOSE && stockBuild.getClose() != null) {
        BigDecimal add = stockBuild.getOpen();
        results.add(add);
      }
    }
    Collections.sort(results);
    Collections.reverse(results);
    if (results.size() >= 1) {
      BigDecimal result = results.get((stocks.size() / 2));
      System.out.println("Result = " + result);
      return result;
    }
    return null;
  }

  public static void geometricMean(ArrayList<StockBuild> stocks, ColumnNameEnum columnNameEnum) {

  }


}
