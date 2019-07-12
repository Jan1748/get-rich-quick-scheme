package org.getRichFast.Model.Algorithms;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;

public class CrossCoroliationFinder {

  private DatabaseConnection dataReceiver = new DatabaseConnection();
  private AveragePerIntervalCreator averagePerIntervalCreator = new AveragePerIntervalCreator();

  public ArrayList getCrossCoroliation(String stockCode, String quandlApiKey, SymbolEnum symbolEnum, DateEnum dateEnum, String date, String date2, BigDecimal tolerance) {
    ArrayList<String> quandlCodesForStock = getQuandlCodesForStocks(stockCode, quandlApiKey);
    ArrayList<String[]> crossCoroliationFound = new ArrayList<>();

    for (int x = 1; x < quandlCodesForStock.size(); x++) {
      System.out.println(quandlCodesForStock.get(x));
      ArrayList<String[]> result = findCrossCoroliationForStock(quandlCodesForStock, quandlCodesForStock.get(x), 2, dateEnum, date, date2, symbolEnum, tolerance);
      for (int i = 0; i < result.size(); i++) {
        crossCoroliationFound.add(result.get(i));
      }
    }
    return crossCoroliationFound;
  }


  private ArrayList findCrossCoroliationForStock(ArrayList<String> quandlCodesForStock, String quandlCode, int numberOfDivisions, DateEnum dateEnum, String date, String date2, SymbolEnum symbolEnum, BigDecimal tolerancePercent) {
    BigDecimal[] averageMainStock = averagePerIntervalCreator.getAveragePerInterval(quandlCode, numberOfDivisions, dateEnum, date, date2, symbolEnum);
    BigDecimal averageFirstIntervalMainStock = averageMainStock[0];
    BigDecimal averageLastIntervalMainStock = averageMainStock[averageMainStock.length - 1];

    ArrayList<String[]> crossCoroliationFound = new ArrayList<>();

    for (int i = 1; i < quandlCodesForStock.size(); i++) {
      if (quandlCode != quandlCodesForStock.get(i)) {
        System.out.println("Compare " + quandlCode + " with " + quandlCodesForStock.get(i));
        BigDecimal[] averageIntervalComparedStock = averagePerIntervalCreator.getAveragePerInterval(quandlCodesForStock.get(i), numberOfDivisions, dateEnum, date, date2, symbolEnum);
        BigDecimal averageFirstIntervalComparedStock = averageIntervalComparedStock[0];
        BigDecimal averageLastIntervalComparedStock = averageIntervalComparedStock[averageIntervalComparedStock.length - 1];
        System.out.println(averageFirstIntervalMainStock + " | " + averageFirstIntervalComparedStock);
        BigDecimal firstDifference = averageFirstIntervalMainStock.subtract(averageFirstIntervalComparedStock).abs();
        BigDecimal lastDifference = averageLastIntervalMainStock.subtract(averageLastIntervalComparedStock).abs();
        System.out.println(averageLastIntervalMainStock + " | " + averageLastIntervalComparedStock);

        if (lastDifference != BigDecimal.ZERO) {
          if (firstDifference.divide(lastDifference, 2, RoundingMode.HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)).compareTo(tolerancePercent) <= 0) {
            crossCoroliationFound.add(new String[]{quandlCodesForStock.get(i), quandlCode});
            System.out.println("Added " + quandlCode + " and " + quandlCodesForStock.get(i));
          }
        }
      }
      else {
        break;
      }
    }
    return crossCoroliationFound;
  }

  private ArrayList<String> getQuandlCodesForStocks(String stockCode, String quandlApiKey) {
    ArrayList<String> quandlCodesForStock = null;
    try {
      quandlCodesForStock = QuandlCodeFinder.getQuandlCodes(stockCode, quandlApiKey);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return quandlCodesForStock;
  }

}
