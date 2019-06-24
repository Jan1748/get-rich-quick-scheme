package org.getRichFast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class StockSercher {

  private StockBuild[] stocks;

  public StockSercher(StockBuild[] stocks) {
    this.stocks = stocks;
  }

  public void serchInStocks() {
    System.out.println("What would you serch? Date or Open or High or Low or Close.");
    //TODO: Scanner abfrage
    String serch = "Date";
    switch (serch) {
      case "Date":
        serchForDate();
        break;

    }
  }

  private void serchForDate() {
    ArrayList<StockBuild> foundStocks = new ArrayList<>();
    InputFunktions inputFunktions = new InputFunktions();
    Calendar start = inputFunktions.getInputCalendar();
    Calendar end = inputFunktions.getInputCalendar();
    SearchFunktions searchFunktions = new SearchFunktions();
    for (int i = 0; i < stocks.length-1; i++) {
      StockBuild stock = stocks[i];
      if(searchFunktions.calendarInterval(stock.getDate(), start, end)){
        foundStocks.add(stock);
        System.out.println("Stock nr " + i + " wurde geaddet" + stock.toString());
      }
    }
  }
}
