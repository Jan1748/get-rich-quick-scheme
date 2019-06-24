package org.getRichFast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class StockSearcher {

  private StockBuild[] stocks;

  public StockSearcher(StockBuild[] stocks) {
    this.stocks = stocks;
  }

  public ArrayList<StockBuild> searchForDate() {
    Scanner scanner = new Scanner(System.in);
    ArrayList<StockBuild> stockFounds = new ArrayList<>();
    InputFunctions inputFunctions = new InputFunctions();
    SearchFunktions searchFunktions = new SearchFunktions();
    int counter = 0;
    System.out.println("What type of date do you want to search for? 1: Exact date 2: Interval of dates 3: Everything before date 4: Everything after date");
    String choice = scanner.nextLine();
    Calendar firstInput = inputFunctions.getInputCalendar();
    switch (choice) {
      case "1":
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
      case "2":
        Calendar end = inputFunctions.getInputCalendar();
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
      case "3":
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
      case "4":
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
