package org.getRichFast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Sorter {
  private ArrayList<StockBuild> stockFounds;
  private BigDecimal lowest;

  public Sorter(ArrayList<StockBuild> stockFounds){
    this.stockFounds = stockFounds;
  }
  public void serarchHighestAndLowest(String choice){
    BigDecimal currentLowestOpen = stockFounds.get(0).getOpen();
    BigDecimal currentLowestLow = stockFounds.get(0).getLow();
    BigDecimal currentLowestHigh = stockFounds.get(0).getHigh();
    BigDecimal currentLowestClose = stockFounds.get(0).getClose();

    BigDecimal currentHighestOpen = stockFounds.get(0).getOpen();
    BigDecimal currentHighestLow = stockFounds.get(0).getLow();
    BigDecimal currentHighestHigh = stockFounds.get(0).getHigh();
    BigDecimal currentHighestClose = stockFounds.get(0).getClose();

    for(int i = 0; i < stockFounds.size(); i++){
      StockBuild stock = stockFounds.get(i);
      switch (choice){
        case "Open":
          currentLowestOpen = compare("low", stock.getOpen(), currentLowestOpen);
          currentHighestOpen = compare("high", stock.getOpen(), currentHighestOpen);
          break;
        case "Low":
          currentLowestLow = compare("low", stock.getLow(), currentLowestLow);
          currentHighestOpen = compare("high", stock.getLow(), currentHighestLow);
          break;
        case "High":
          currentLowestHigh = compare("low", stock.getHigh(), currentLowestHigh);
          currentHighestOpen = compare("high", stock.getHigh(), currentHighestHigh);
          break;
        case "Close":
          currentLowestClose = compare("low", stock.getClose(), currentLowestClose);
          currentHighestOpen = compare("high", stock.getClose(), currentHighestClose);
          break;
      }
    }
  }
  private BigDecimal compare(String choice, BigDecimal input, BigDecimal currentExtrem){
    switch (choice) {
      case "low":
        if (input.compareTo(currentExtrem) < 0) {
          currentExtrem = input;
        } else {
          return currentExtrem;
        }
      case "high":
        if (input.compareTo(currentExtrem) > 0){
          currentExtrem = input;
        }
        else return currentExtrem;
    }
  return currentExtrem;
  }

}
