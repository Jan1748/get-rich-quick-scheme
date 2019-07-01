package org.getRichFast.UI.ConsoleUI;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Menus {

  private String menuChoice;
  private String quandlApiKey;

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

              } else {
                System.out.println("abort");
              }
              //TODO: add a method that downloads all data from a stock market
              break;
            case "2":
              symbol = InputFunctions.scan("Please enter the symbol for the stock.");
              if (InputFunctions.scan("Start downloading Quandl data: " + symbol + " with api-code: " + quandlApiKey + "? (y/n)").equals("y")) {

              } else {
                System.out.println("abort");
              }
              //TODO: add a method that downloads a single share price
              break;
          }
          symbol = InputFunctions.scan("Please enter the symbol.");
          if (InputFunctions.scan("Start downloading Quandl data: " + symbol + " with api-code: " + quandlApiKey + "? (y/n)").equals("y")) {

          } else {
            System.out.println("abort");
          }
          break;
        case "2":
          searchMenu();
          break;
        case "e":
          System.exit(0);
          break;
        default:
          System.out.println("Please enter a valid choice");
      }
    }
  }

  private void searchMenu() {
    String date = null;
    String date2 = null;
    String symbol;
    menuChoice = InputFunctions.scan("What do you want to search for? \n1: Date \n2: Symbol \n3: Value");
    switch (menuChoice) {
      case "1":
        switch (InputFunctions.scan("What type of date do you want to search for? 1: Exact date 2: Interval of dates 3: Everything before date 4: Everything after date")) {
          case "1":
            date = InputFunctions.getInputDateString();
            break;
          case "2":
            date = InputFunctions.getInputDateString();
            date2 = InputFunctions.getInputDateString();
            break;
          case "3":
            date = InputFunctions.getInputDateString();
            break;
          case "4":
            date = InputFunctions.getInputDateString();
            break;
          default:
            System.out.println("Please enter a valid choice");
        }
        if (InputFunctions.scan("Do you want to search for a symbol? (y/n)").equals("y")) {
          symbol = InputFunctions.scan("Please enter the symbol you want to search for.");
          searchForValueMenu(date, date2, symbol);
        } else {
          searchForValueMenu(date, date2, null);
        }
        break;
      case "2":
        symbol = InputFunctions.scan("Please enter the symbol you want to search for.");
        searchForValueMenu(null, null, symbol);
        break;
      case "3":
        searchForValueMenu(null, null, null);
      default:
        System.out.println("Please enter a valid choice");
    }
  }

  private void searchForValueMenu(String date, String date2, String symbol) {
    switch (InputFunctions.scan("What do you want to search? \n1: Highest value \n2: Lowest value")) {
      case "1":
        //TODO: add search for highest value
      case "2":
        //TODO: add search for lowest value
    }
  }
}
