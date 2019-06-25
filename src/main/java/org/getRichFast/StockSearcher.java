package org.getRichFast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class StockSearcher {

  private ArrayList<StockBuild> stocks;
  private Scanner scanner = new Scanner(System.in);

  public StockSearcher(ArrayList<StockBuild> stocks) {
    this.stocks = stocks;
  }

  //TODO: Search for symbol

  public ArrayList<StockBuild> searchForDate() {
    ArrayList<StockBuild> stockFounds = new ArrayList<>();
    InputFunctions inputFunctions = new InputFunctions();
    SearchFunktions searchFunktions = new SearchFunktions();
    int counter = 0;

    System.out.println("What type of date do you want to search for? 1: Exact date 2: Interval of dates 3: Everything before date 4: Everything after date");
    String choice = scanner.nextLine();
    Calendar firstInput = inputFunctions.getInputCalendar();

    switch (choice) {
      case "1":
        for (int i = 0; i < stocks.size(); i++) {
          StockBuild stock = stocks.get(i);
          if (searchFunktions.exactCalendarDate(stock.getDate(), firstInput)) {
            counter++;
            stockFounds.add(stock);
            System.out.println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;

      case "2":
        Calendar end = inputFunctions.getInputCalendar();
        for (int i = 0; i < stocks.size(); i++) {
          StockBuild stock = stocks.get(i);
          if (searchFunktions.calendarInterval(stock.getDate(), firstInput, end)) {
            counter++;
            stockFounds.add(stock);
            System.out.println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;

      case "3":
        for (int i = 0; i < stocks.size(); i++) {
          StockBuild stock = stocks.get(i);
          if (searchFunktions.beforeCalendar(stock.getDate(), firstInput)) {
            counter++;
            stockFounds.add(stock);
            System.out.println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;

      case "4":
        for (int i = 0; i < stocks.size(); i++) {
          StockBuild stock = stocks.get(i);
          if (searchFunktions.afterCalendar(stock.getDate(), firstInput)) {
            counter++;
            stockFounds.add(stock);
            System.out.println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
          }
        }
        System.out.println(counter + " results were found");
        return stockFounds;
    }
    return null;
  }

  public StockBuild searchForValue(ArrayList<StockBuild> stockFounds) {
    Sorter sorter = new Sorter();

    System.out.println("What do you want to search for? 1: Highest value 2: Lowest value 3: Symbol");
    String highLowChoice = scanner.nextLine();

    switch (highLowChoice) {
      case "1":
        System.out.println("For which data do you want the highest value? 1: Open 2: High 3: Low 4: Close");
        String choice = scanner.nextLine();
        switch (choice){
          case "1":
            return sorter.getCurrentLowestOpen();
        }


    }
    return null;
  }
}
