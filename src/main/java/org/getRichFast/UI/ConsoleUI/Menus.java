package org.getRichFast.UI.ConsoleUI;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.InterfaceConnection.InterfaceConnectorToDatabase;
import org.getRichFast.Model.ProcessDecisions;

public class Menus {

  private String menuChoice;
  private String quandlApiKey;
  private ProcessDecisions processDecisions = new InterfaceConnectorToDatabase();

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
    menuChoice = InputFunctions.scan("What do you want to search for? \n1: Date \n2: Symbol \n3: Value");
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
          searchForValueMenu(dateEnum, symbolEnum, date, date2, symbol);
        } else {
          searchForValueMenu(dateEnum, symbolEnum, date, date2, null);
        }
        break;
      case "2":
        symbol = InputFunctions.scan("Please enter the symbol you want to search for.");
        symbolEnum = SymbolEnum.SINGLE;
        searchForValueMenu(dateEnum, symbolEnum, null, null, symbol);
        break;
      case "3":
        searchForValueMenu(dateEnum, symbolEnum, null, null, null);
        break;
      default:
        System.out.println("Please enter a valid choice.");
    }
  }

  private void searchForValueMenu(DateEnum dateEnum, SymbolEnum symbolEnum, String date, String date2, String symbol) {
    switch (InputFunctions.scan("What do you want to search? \n1: Highest value \n2: Lowest value")) {
      case "1":
        processDecisions.searchForValue(ValueEnum.MAX, symbolEnum, dateEnum, date, date2, symbol);
        break;
      case "2":
        processDecisions.searchForValue(ValueEnum.MIN, symbolEnum, dateEnum, date, date2, symbol);
        break;
      default:
        System.out.println("Please enter a valid choice.");
    }
  }
}
