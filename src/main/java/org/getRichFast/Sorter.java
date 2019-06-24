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
    for(int i = 0; i < stockFounds.size(); i++){
      StockBuild stock = stockFounds.get(i);
      switch (choice){
        case "Open":if (compare(stock.getOpen()));break;
        case "Low":stock.getLow();break;
        case "High":stock.getHigh();break;
        case "Close":stock.getClose();break;
      }
    }
  }
  private Boolean compare(BigDecimal input){

  }

}
