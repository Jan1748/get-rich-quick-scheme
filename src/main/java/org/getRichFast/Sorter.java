package org.getRichFast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Sorter {

  private ArrayList<StockBuild> stockFounds;
  private BigDecimal currentLowestOpen;
  private BigDecimal currentLowestLow;
  private BigDecimal currentLowestHigh;
  private BigDecimal currentLowestClose;
  private BigDecimal currentHighestOpen;
  private BigDecimal currentHighestLow;
  private BigDecimal currentHighestHigh;
  private BigDecimal currentHighestClose;

  public void serarchHighestAndLowest(ArrayList<StockBuild> stockFounds, String choice, String higherLowerChoice) {
    currentLowestOpen = stockFounds.get(0).getOpen();
    currentLowestLow = stockFounds.get(0).getLow();
    currentLowestHigh = stockFounds.get(0).getHigh();
    currentLowestClose = stockFounds.get(0).getClose();

    currentHighestOpen = stockFounds.get(0).getOpen();
    currentHighestLow = stockFounds.get(0).getLow();
    currentHighestHigh = stockFounds.get(0).getHigh();
    currentHighestClose = stockFounds.get(0).getClose();

    for (int i = 0; i < stockFounds.size(); i++) {
      StockBuild stock = stockFounds.get(i);
      switch (choice) {
        case "1":
          currentLowestOpen = compare("low", stock.getOpen(), currentLowestOpen);
          currentHighestOpen = compare("high", stock.getOpen(), currentHighestOpen);
          break;
        case "2":
          currentLowestHigh = compare("low", stock.getHigh(), currentLowestHigh);
          currentHighestHigh = compare("high", stock.getHigh(), currentHighestHigh);
          break;
        case "3":
          currentLowestLow = compare("low", stock.getLow(), currentLowestLow);
          currentHighestLow = compare("high", stock.getLow(), currentHighestLow);
          break;
        case "4":
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

  public BigDecimal getCurrentLowestOpen() {
    return currentLowestOpen;
  }

  public BigDecimal getCurrentLowestLow() {
    return currentLowestLow;
  }

  public BigDecimal getCurrentLowestHigh() {
    return currentLowestHigh;
  }

  public BigDecimal getCurrentLowestClose() {
    return currentLowestClose;
  }

  public BigDecimal getCurrentHighestOpen() {
    return currentHighestOpen;
  }

  public BigDecimal getCurrentHighestLow() {
    return currentHighestLow;
  }

  public BigDecimal getCurrentHighestHigh() {
    return currentHighestHigh;
  }

  public BigDecimal getCurrentHighestClose() {
    return currentHighestClose;
  }
}
