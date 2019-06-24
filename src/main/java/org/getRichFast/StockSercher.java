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

  public ArrayList<StockBuild> serchInStocks() {
    System.out.println("What would you serch? Date or Open or High or Low or Close.");
    //TODO: Scanner abfrage
    String serch = "Date";
    switch (serch) {
      case "Date":
        return serchForDate();


    }
    return null;
  }

  private ArrayList<StockBuild> serchForDate(String choice) {
    choice = "exact";
    ArrayList<StockBuild> stockFounds = new ArrayList<>();
    InputFunktions inputFunktions = new InputFunktions();
    SearchFunktions searchFunktions = new SearchFunktions();
    int counter = 0;
    switch (choice) {
      case "exact":
        for (int i = 0; i < stocks.length; i++) {
          StockBuild stock = stocks[i];
          if (searchFunktions.exactCalendarDate(stock.getDate(), inputFunktions.getInputCalendar())) {
            counter++;
            stockFounds.add(stock);
            System.out
                .println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;
      case "intervall":
        for (int i = 0; i < stocks.length; i++) {
          StockBuild stock = stocks[i];
          if (searchFunktions.calendarInterval(stock.getDate(), inputFunktions.getInputCalendar(), inputFunktions.getInputCalendar())) {
            counter++;
            stockFounds.add(stock);
            System.out
                .println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;
    }
    return null;
  }
}
