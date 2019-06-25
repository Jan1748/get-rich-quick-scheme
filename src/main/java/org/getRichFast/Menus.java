package org.getRichFast;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menus {

  private ArrayList<StockBuild> stocks;
  private ArrayList<StockBuild> searchedStocks;
  private Scanner scanner = new Scanner(System.in);
  private String menuChoice;

  public void startMenu(String quandlApiKey, String quandlCode) {
    try {
      menu(quandlApiKey, quandlCode);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  private void searchMenu() {
    searchedStocks = null;
    StockSearcher stockSearcher = new StockSearcher(stocks);

    System.out.println("What do you want to search for? 1: Date 2: ");
    menuChoice = scanner.nextLine();
    switch (menuChoice) {
      case "1":
        searchedStocks = stockSearcher.searchForDate();
        break;
      case "2":
        //TODO: add search function for value
        if(searchedStocks != null){

        }
        break;
    }
  }

  private void menu(String quandlApiKey, String quandlCode) throws IOException, ParseException {
    Boolean abort = false;
    while (!abort) {
      String menuChoice;
      DataShifter data = new DataShifter();
      System.out.println("Quandl Downloader and Parser");
      System.out.println(
          "1: Start download and parsing 2: Search in the data E: exit (Please enter your choice)");
      menuChoice = scanner.nextLine();
      switch (menuChoice) {
        case "1":
          System.out.println("Enter your api-code:");
          //quandlApiKey = scanner.nextLine();
          System.out.println("Enter the Quandl-code:");
          //quandlCode = scanner.nextLine();
          System.out.println(
              "Start downloading Quandl data: " + quandlApiKey//apiCode
                  + "with api-code: " + quandlCode// apiCode
                  + "? (y/n)");
          if (scanner.nextLine().equals("y")) {
            stocks = data.getAndParseData(quandlApiKey, quandlCode);
          } else {
            System.out.println("abort");
          }
          break;

        case "2":
          if(stocks != null){
            searchMenu();
          }
          else {
            System.out.println("You have to download data before searching");
          }
          break;

        case "E":
          System.exit(0);
          break;
      }
    }
  }

}

