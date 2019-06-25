package org.getRichFast.Searching;

import static org.getRichFast.UI.InputFunctions.getInputCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import org.getRichFast.Entity.StockBuild;
import org.getRichFast.UI.InputFunctions;

public class StockSearcher {

  //FIXME: maybe separate UI- code from Model- from Data-Code

  private ArrayList<StockBuild> stocks;
  private Scanner scanner = new Scanner(System.in);

  public StockSearcher(ArrayList<StockBuild> stocks) {
    this.stocks = stocks;
  }

  //TODO: Search for symbol

  public ArrayList<StockBuild> searchForDate() {
    ArrayList<StockBuild> stockFounds = new ArrayList<>();
    SearchFunktions searchFunktions = new SearchFunktions();
    int counter = 0;

    System.out.println("What type of date do you want to search for? 1: Exact date 2: Interval of dates 3: Everything before date 4: Everything after date");
    String choice = scanner.nextLine();
    Calendar firstInput = InputFunctions.getInputCalendar();

    //FIXME extract methods/classes for case 1-4 to reduce class length
    switch (choice) {
      case "1":
        return SearchMethods.dateSearch(stocks, "exact");
      case "2":
        return SearchMethods.dateSearch(stocks, "interval");
      case "3":
        return SearchMethods.dateSearch(stocks, "before");
      case "4":
        return SearchMethods.dateSearch(stocks, "after");
    }
    return null;
  }

  private void checkIfNull(StockBuild stockBuild) {
    if (stockBuild.getOpen() == null) {
      System.out.println("Error no Value available");
    } else {
      System.out.println("This is the lowest open: " + stockBuild.getOpen());
    }
  }

  public StockBuild searchForValue(ArrayList<StockBuild> stockFounds) {
    Sorter sorter = new Sorter();
    sorter.serarchHighestAndLowest(stockFounds);
    SearchFunktions searchFunktions = new SearchFunktions();

    System.out.println("What do you want to search for? 1: Lowest value 2: Highest value 3: Symbol");
    String highLowChoice = scanner.nextLine();

    switch (highLowChoice) {
      case "1":
        System.out.println("For which data do you want the lowest value? 1: Open 2: High 3: Low 4: Close");
        String choice = scanner.nextLine();
        switch (choice) {
          case "1":
            return sorter.getCurrentLowestOpen();
          case "2":
            return sorter.getCurrentLowestHigh();
          case "3":
            return sorter.getCurrentLowestLow();
          case "4":
            return sorter.getCurrentLowestClose();

        }
        break;
      case "2":
        System.out.println("For which data do you want the highest value? 1: Open 2: High 3: Low 4: Close");
        choice = scanner.nextLine();
        switch (choice) {
          case "1":
            return sorter.getCurrentHighestOpen();
          case "2":
            return sorter.getCurrentHighestHigh();
          case "3":
            return sorter.getCurrentHighestLow();
          case "4":
            return sorter.getCurrentHighestClose();
        }
      case "3":
        System.out.println("Wich Symbol do you want to search for?");
        choice = scanner.nextLine();
        //searchFunktions.symbolCheck(choice, )
    }
    return null;
  }
}
