package org.getRichFast.Searching;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.getRichFast.Entity.StockBuild;

public class Sorter {

  private ArrayList<StockBuild> stockFounds;
  private StockBuild currentLowestOpen;
  private StockBuild currentLowestLow;
  private StockBuild currentLowestHigh;
  private StockBuild currentLowestClose;
  private StockBuild currentHighestOpen;
  private StockBuild currentHighestLow;
  private StockBuild currentHighestHigh;
  private StockBuild currentHighestClose;
  private StockBuild oldStock;

  public void serarchHighestAndLowest(ArrayList<StockBuild> stockFounds) {
    currentLowestOpen = stockFounds.get(0);
    currentLowestLow = stockFounds.get(0);
    currentLowestHigh = stockFounds.get(0);
    currentLowestClose = stockFounds.get(0);
    currentHighestOpen = stockFounds.get(0);
    currentHighestLow = stockFounds.get(0);
    currentHighestHigh = stockFounds.get(0);
    currentHighestClose = stockFounds.get(0);
    oldStock = stockFounds.get(0);

    for (int i = 0; i < stockFounds.size(); i++) {
      StockBuild stock = stockFounds.get(i);
      System.out.println("Row nr: " + i);
        if (compare("low", stock.getOpen(), currentLowestOpen.getOpen())) {
          currentLowestOpen = stock;
        }
        if (compare("high", stock.getOpen(), currentHighestOpen.getOpen())) {
          currentHighestOpen = stock;
        }
        if (compare("low", stock.getHigh(), currentLowestHigh.getHigh())) {
          currentLowestHigh = stock;
        }
        if (compare("high", stock.getHigh(), currentHighestHigh.getHigh())) {
          currentHighestHigh = stock;
        }
        if (compare("low", stock.getLow(), currentLowestLow.getLow())) {
          currentLowestLow = stock;
        }
        if (compare("high", stock.getLow(), currentHighestLow.getLow())) {
          currentHighestLow = stock;
        }
        if (compare("low", stock.getClose(), currentLowestClose.getClose())) {
          currentLowestClose = stock;
        }
        if (compare("high", stock.getClose(), currentHighestClose.getClose())) {
          currentHighestClose = stock;
        }
        oldStock = stock;
      }
  }

  public Boolean compare(String choice, BigDecimal input, BigDecimal currentExtremum) {
    int counter = 0;
    //System.out.println("Input: " + input);
    try{
    switch (choice) {
      case "low":
        if (input != null && input.compareTo(currentExtremum) < 0) {
          return true;
        } else {
          return false;
        }
      case "high":
        if (input != null && currentExtremum.compareTo(input) < 0) {
          return true;
        } else {
          return false;
        }
    }
    }catch (Exception e){
      counter++;
      System.out.println("Error " + input);
    }
    return false;
  }

  public void setStockFounds(ArrayList<StockBuild> stockFounds) {
    this.stockFounds = stockFounds;
  }

  public StockBuild getCurrentLowestOpen() {
    return currentLowestOpen;
  }

  public StockBuild getCurrentLowestLow() {
    return currentLowestLow;
  }

  public StockBuild getCurrentLowestHigh() {
    return currentLowestHigh;
  }

  public StockBuild getCurrentLowestClose() {
    return currentLowestClose;
  }

  public StockBuild getCurrentHighestOpen() {
    return currentHighestOpen;
  }

  public StockBuild getCurrentHighestLow() {
    return currentHighestLow;
  }

  public StockBuild getCurrentHighestHigh() {
    return currentHighestHigh;
  }

  public StockBuild getCurrentHighestClose() {
    return currentHighestClose;
  }
}
