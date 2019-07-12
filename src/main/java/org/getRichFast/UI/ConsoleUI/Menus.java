package org.getRichFast.UI.ConsoleUI;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.ConnectToUI;
import org.getRichFast.Model.Entity.PerformingStocks;
import org.getRichFast.Model.InterfaceConnection.InterfaceConnectorToDatabase;
import org.getRichFast.Model.InterfaceConnection.ModelToUIConnection;
import org.getRichFast.Model.ProcessDecisions;
import org.getRichFast.UI.UIReceiver;

public class Menus {

  private String menuChoice;
  private String quandlApiKey;
  private ProcessDecisions processDecisions = new InterfaceConnectorToDatabase();
  private UIReceiver uiReceiver = new ConsoleOutputReceiver();


  public void startMenu() {
    menu();
  }

  private void menu() {
    try {
      InputStream input = new FileInputStream("src/main/resources/config.properties");
      Properties prop = new Properties();
      prop.load(input);
      this.quandlApiKey = prop.getProperty("api.Key");
    } catch (IOException e) {
      e.printStackTrace();
    }

    String symbol;
    Boolean abort = false;
    while (!abort) {
      System.out.println("Quandl Downloader and Parser");
      menuChoice = InputFunctions.scan("1: Start download and parsing \n2: Search in the data \ne: exit (Please enter your choice)");
      switch (menuChoice) {
        case "1":
          switch (InputFunctions.scan("What do you want to download? 1: All data from a stock market 2: Single stock")) {
            case "1":
              symbol = InputFunctions.scan("Please enter the symbol for the stock market.");
              if (InputFunctions.scan("Start downloading Quandl data: " + symbol + " with api-code: " + quandlApiKey + "? (y/n)").equals("y")) {
                processDecisions.downloadQuandlWholeStockMarket(symbol, quandlApiKey);
              } else {
                System.out.println("abort");
              }
              break;
            case "2":
              symbol = InputFunctions.scan("Please enter the symbol for the stock.");
              if (InputFunctions.scan("Start downloading Quandl data: " + symbol + " with api-code: " + quandlApiKey + "? (y/n)").equals("y")) {
                processDecisions.downloadQuandlSingleStock(symbol, quandlApiKey);
              } else {
                System.out.println("abort");
              }
              break;
            default:
              System.out.println("Please enter a valid choice.");
          }
          break;
        case "2":
          searchMenu();
          break;
        case "e":
          System.exit(0);
          break;
        default:
          System.out.println("Please enter a valid choice.");
      }
    }
  }

  private void searchMenu() {
    String date = null;
    String date2 = null;
    DateEnum dateEnum = DateEnum.NULL;
    SymbolEnum symbolEnum = SymbolEnum.NULL;
    String symbol;
    menuChoice = InputFunctions.scan("What do you want to search for? \n1: Date \n2: Symbol \n3: Value \n4: Best performing stocks");
    switch (menuChoice) {
      case "1":
        switch (InputFunctions.scan("What type of date do you want to search for? 1: Exact date 2: Interval of dates 3: Everything before date 4: Everything after date")) {
          case "1":
            date = InputFunctions.getInputDateString();
            dateEnum = DateEnum.EXACT;
            break;
          case "2":
            date = InputFunctions.getInputDateString();
            date2 = InputFunctions.getInputDateString();
            dateEnum = DateEnum.INTERVAL;
            break;
          case "3":
            date = InputFunctions.getInputDateString();
            dateEnum = DateEnum.BEFORE;
            break;
          case "4":
            date = InputFunctions.getInputDateString();
            dateEnum = DateEnum.AFTER;
            break;
          default:
            System.out.println("Please enter a valid choice.");
        }
        if (InputFunctions.scan("Do you want to search for a symbol? (y/n)").equals("y")) {
          symbol = InputFunctions.scan("Please enter the symbol you want to search for.");
          symbolEnum = SymbolEnum.ATTACHED;
          searchForValueMenu(dateEnum, symbolEnum, ColumnNameEnum.ALL, date, date2, symbol);
        } else {
          searchForValueMenu(dateEnum, symbolEnum, ColumnNameEnum.ALL, date, date2, null);
        }
        break;
      case "2":
        symbol = InputFunctions.scan("Please enter the symbol you want to search for.");
        symbolEnum = SymbolEnum.SINGLE;
        searchForValueMenu(dateEnum, symbolEnum, ColumnNameEnum.ALL, null, null, symbol);
        break;
      case "3":
        searchForValueMenu(dateEnum, symbolEnum, ColumnNameEnum.ALL, null, null, null);
        break;
      case "4":
        stockPerformanceMenu();
        break;
      default:
        System.out.println("Please enter a valid choice.");
    }
  }

  private void searchForValueMenu(DateEnum dateEnum, SymbolEnum symbolEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    String message = "test";
    if (dateEnum != DateEnum.EXACT) {
      message = "What do you want to search? \n1: Highest value \n2: Lowest value \n3: Create Chart";
    } else {
      message = "What do you want to search? \n1: Highest value \n2: Lowest value";
    }

    switch (InputFunctions.scan(message)) {
      case "1":
        processDecisions.searchForValue(ValueEnum.MAX, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);
        break;
      case "2":
        processDecisions.searchForValue(ValueEnum.MIN, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);
        break;
      case "3":
        chartMenu(dateEnum, symbolEnum, columnNameEnum, date, date2, symbol);
        break;
      default:
        System.out.println("Please enter a valid choice.");
    }
  }

  private void chartMenu(DateEnum dateEnum, SymbolEnum symbolEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    switch (InputFunctions.scan("Choose your preferred chart \n1: LineChart \n2: CandleStickChart \n3: AllStocksToChart \n4: Create All Stocks as a Chart \n5: Histogram")) {
      case "1":
        processDecisions.createLineChart(ValueEnum.ALL, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol, null);
        break;
      case "2":
        processDecisions.createCandleStickChart(ValueEnum.ALL, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);
        break;
      case "3":
        processDecisions.visulizeTwoCharts(symbol, quandlApiKey, dateEnum, ColumnNameEnum.ALL, date, date2);
        break;
      case "4":
        processDecisions.generateAllChartsFromStock(symbol, quandlApiKey, dateEnum, ColumnNameEnum.ALL, date, date2);
        break;
      case "5":
        processDecisions.createHistogram(SymbolEnum.ATTACHED, dateEnum, ColumnNameEnum.OPEN, date, date2, symbol, 20);
        break;
      default:
        System.out.println("Please enter a valid choice.");
        break;
    }
  }

  private void stockPerformanceMenu() {
    DateEnum dateEnum = DateEnum.NULL;
    String date = null;
    String date2 = null;
    ArrayList<PerformingStocks> performingStocks = null;
    String stock = InputFunctions.scan("Please enter the stock from witch you want the performance.");
    int numberOfDivisions = InputFunctions.getInputNumberOfDivisions();

    switch (InputFunctions.scan("What type of date do you want to search for? 1: Interval of dates 2: Everything before date 3: Everything after date 4: All dates")) {
      case "1":
        date = InputFunctions.getInputDateString();
        date2 = InputFunctions.getInputDateString();
        dateEnum = DateEnum.INTERVAL;

        switch (InputFunctions.scan("For which value should the performance be sorted. 1: Percentage profit 2: Absolute profit")) {
          case "1":
            processDecisions.getSortedPerformingStocksPercent(stock, quandlApiKey, numberOfDivisions, dateEnum, date, date2);
            break;
          case "2":
            processDecisions.getSortedPerformingStockAbsolute(stock, quandlApiKey, numberOfDivisions, dateEnum, date, date2);
            break;
        }
        break;
      case "2":
        date = InputFunctions.getInputDateString();
        dateEnum = DateEnum.BEFORE;

        switch (InputFunctions.scan("For which value should the performance be sorted. 1: Percentage profit 2: Absolute profit")) {
          case "1":
            processDecisions.getSortedPerformingStocksPercent(stock, quandlApiKey, numberOfDivisions, dateEnum, date, date2);
            break;
          case "2":
            processDecisions.getSortedPerformingStockAbsolute(stock, quandlApiKey, numberOfDivisions, dateEnum, date, date2);
            ;
            break;
        }
        break;
      case "3":
        date = InputFunctions.getInputDateString();
        dateEnum = DateEnum.AFTER;

        switch (InputFunctions.scan("For which value should the performance be sorted. 1: Percentage profit 2: Absolute profit")) {
          case "1":
            processDecisions.getSortedPerformingStocksPercent(stock, quandlApiKey, numberOfDivisions, dateEnum, date, date2);
            break;
          case "2":
            processDecisions.getSortedPerformingStockAbsolute(stock, quandlApiKey, numberOfDivisions, dateEnum, date, date2);
            ;
            break;
        }
        break;
      case "4":
        switch (InputFunctions.scan("For which value should the performance be sorted. 1: Percentage profit 2: Absolute profit")) {
          case "1":
            processDecisions.getSortedPerformingStocksPercent(stock, quandlApiKey, numberOfDivisions, dateEnum, date, date2);
            break;
          case "2":
            processDecisions.getSortedPerformingStockAbsolute(stock, quandlApiKey, numberOfDivisions, dateEnum, date, date2);
            ;
            break;
        }
        break;
      default:
        System.out.println("Please enter a valid choice.");
    }
  }
}
