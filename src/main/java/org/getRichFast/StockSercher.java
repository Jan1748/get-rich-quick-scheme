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
        return serchForDate("intervall");


    }
    return null;
  }

  private ArrayList<StockBuild> serchForDate(String choice) {
    ArrayList<StockBuild> stockFounds = new ArrayList<>();
    InputFunktions inputFunktions = new InputFunktions();
    Calendar firstInput = inputFunktions.getInputCalendar();
    SearchFunktions searchFunktions = new SearchFunktions();
    int counter = 0;
    switch (choice) {
      case "exact":
        for (int i = 0; i < stocks.length; i++) {
          StockBuild stock = stocks[i];
          if (searchFunktions.exactCalendarDate(stock.getDate(), firstInput)) {
            counter++;
            stockFounds.add(stock);
            System.out
                .println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;
      case "intervall":
        Calendar end = inputFunktions.getInputCalendar();
        for (int i = 0; i < stocks.length; i++) {
          StockBuild stock = stocks[i];
          if (searchFunktions.calendarInterval(stock.getDate(), firstInput, end)) {
            counter++;
            stockFounds.add(stock);
            System.out
                .println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;
      case "before":
        for (int i = 0; i < stocks.length; i++) {
          StockBuild stock = stocks[i];
          if (searchFunktions.beforeCalendar(stock.getDate(), firstInput)) {
            counter++;
            stockFounds.add(stock);
            System.out
                .println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;
      case "after":
        for (int i = 0; i < stocks.length; i++) {
          StockBuild stock = stocks[i];
          if (searchFunktions.afterCalendar(stock.getDate(), firstInput)) {
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
