package org.getRichFast.Searching;

import java.math.BigDecimal;
import java.util.ArrayList;
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

  private void checkIfNull(BigDecimal value, String highOrLow, String dataType) {
    if (value == null) {
      System.out.println("Error no Value available");
    } else {
      System.out.println("This is the " + highOrLow + " " + dataType + ": " + value);
    }
  }


  public ArrayList<StockBuild> searchForSymbol() {
    String choice = InputFunctions.scan("Type in the searched Symbol: ");
    return SearchMethods.searchSymbol(stocks, choice);
  }


  public StockBuild searchForValue(ArrayList<StockBuild> stocksFound) {
    Sorter sorter = new Sorter();
    StockBuild stock;
    sorter.searchHighestAndLowest(stocksFound);
    String highLowChoice = InputFunctions.scan("What do you want to search for? 1: Lowest value 2: Highest value");
    String[] valueNames = new String[]{"Open", "High", "Low", "Close", "Lowest", "Highest"};

    switch (highLowChoice) {
      case "1":
        String choice = InputFunctions.scan("For which data do you want the lowest value? 1: " + valueNames[0] + "2: " + valueNames[1] + "3: " + valueNames[2] + "4: " + valueNames[3]);
        switch (choice) {
          case "1":
            stock = sorter.getCurrentLowestOpen();
            checkIfNull(stock.getOpen(), valueNames[4], valueNames[0]);
            return stock;
          case "2":
            stock = sorter.getCurrentLowestHigh();
            checkIfNull(stock.getHigh(), valueNames[4], valueNames[1]);
            return stock;
          case "3":
            stock = sorter.getCurrentLowestLow();
            checkIfNull(stock.getLow(), valueNames[4], valueNames[2]);
            return stock;
          case "4":
            stock = sorter.getCurrentLowestClose();
            checkIfNull(stock.getClose(), valueNames[4], valueNames[3]);
            return stock;
        }
        break;
      case "2":
        choice = InputFunctions.scan("For which data do you want the highest value? 1: Open 2: High 3: Low 4: Close");
        switch (choice) {
          case "1":
            stock = sorter.getCurrentHighestOpen();
            checkIfNull(stock.getOpen(), valueNames[5], valueNames[0]);
            return stock;
          case "2":
            stock = sorter.getCurrentHighestHigh();
            checkIfNull(stock.getHigh(), valueNames[5], valueNames[1]);
            return stock;
          case "3":
            stock = sorter.getCurrentHighestLow();
            checkIfNull(stock.getLow(), valueNames[5], valueNames[2]);
            return stock;
          case "4":
            stock = sorter.getCurrentHighestClose();
            checkIfNull(stock.getClose(), valueNames[5], valueNames[3]);
            return stock;
        }
    }
    return null;
  }
}
