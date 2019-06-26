package org.getRichFast.Searching;

import java.util.ArrayList;
import java.util.Scanner;
import org.getRichFast.Entity.StockBuild;

public class StockSearcher {

  //FIXME: maybe separate UI- code from Model- from Data-Code

  private ArrayList<StockBuild> stocks;
  private Scanner scanner = new Scanner(System.in);

  public StockSearcher(ArrayList<StockBuild> stocks) {
    this.stocks = stocks;
  }

  public ArrayList<StockBuild> searchForDate() {
    ArrayList<StockBuild> stocksFound;

    System.out.println("What type of date do you want to search for? 1: Exact date 2: Interval of dates 3: Everything before date 4: Everything after date");
    String choice = scanner.nextLine();

    switch (choice) {
      case "1":
        stocksFound = SearchMethods.dateSearch(stocks, "exact");
        System.out.println(searchForValue(stocksFound));
        return stocksFound;
      case "2":
        stocksFound = SearchMethods.dateSearch(stocks, "interval");
        System.out.println(searchForValue(stocksFound));
        return stocksFound;
      case "3":
        stocksFound = SearchMethods.dateSearch(stocks, "before");
        System.out.println(searchForValue(stocksFound));
        return stocksFound;
      case "4":
        stocksFound = SearchMethods.dateSearch(stocks, "after");
        System.out.println(searchForValue(stocksFound));
        return stocksFound;
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


  public ArrayList<StockBuild> searchForSymbol(String symbol) {
    System.out.println("Type in the searched Symbol: ");
    String choice = scanner.nextLine();
    return SearchMethods.searchSymbol(stocks, symbol);
  }


  public StockBuild searchForValue(ArrayList<StockBuild> stocksFound) {
    Sorter sorter = new Sorter();
    sorter.searchHighestAndLowest(stocksFound);
    SearchFunktions searchFunktions = new SearchFunktions();

    System.out.println("What do you want to search for? 1: Lowest value 2: Highest value");
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
    }
    return null;
  }
}
