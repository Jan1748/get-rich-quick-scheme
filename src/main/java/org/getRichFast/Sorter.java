package org.getRichFast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Sorter {

  private ArrayList<StockBuild> stockFounds;

  public void serarchHighestAndLowest(ArrayList<StockBuild> stockFounds, String choice) {
    BigDecimal currentLowestOpen = stockFounds.get(0).getOpen();
    BigDecimal currentLowestLow = stockFounds.get(0).getLow();
    BigDecimal currentLowestHigh = stockFounds.get(0).getHigh();
    BigDecimal currentLowestClose = stockFounds.get(0).getClose();

    BigDecimal currentHighestOpen = stockFounds.get(0).getOpen();
    BigDecimal currentHighestLow = stockFounds.get(0).getLow();
    BigDecimal currentHighestHigh = stockFounds.get(0).getHigh();
    BigDecimal currentHighestClose = stockFounds.get(0).getClose();

    for (int i = 0; i < stockFounds.size(); i++) {
      StockBuild stock = stockFounds.get(i);
      switch (choice) {
        case "Open":
          currentLowestOpen = compare("low", stock.getOpen(), currentLowestOpen);
          currentHighestOpen = compare("high", stock.getOpen(), currentHighestOpen);
          break;
        case "Low":
          currentLowestLow = compare("low", stock.getLow(), currentLowestLow);
          currentHighestLow = compare("high", stock.getLow(), currentHighestLow);
          break;
        case "High":
          currentLowestHigh = compare("low", stock.getHigh(), currentLowestHigh);
          currentHighestHigh = compare("high", stock.getHigh(), currentHighestHigh);
          break;
        case "Close":
          currentLowestClose = compare("low", stock.getClose(), currentLowestClose);
          currentHighestClose = compare("high", stock.getClose(), currentHighestClose);
          break;
      }
    }
  }

  public BigDecimal compare(String choice, BigDecimal input, BigDecimal currentExtremum) {
    switch (choice) {
      case "low":
        if (input.compareTo(currentExtremum) < 0) {
          currentExtremum = input;
        } else {
          return currentExtremum;
        }
      case "high":
        if (input.compareTo(currentExtremum) > 0) {
          currentExtremum = input;
        } else {
          return currentExtremum;
        }
    }
    return currentExtremum;
  }

  public void setStockFounds(ArrayList<StockBuild> stockFounds) {
    this.stockFounds = stockFounds;
  }
}
