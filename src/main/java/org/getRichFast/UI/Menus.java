package org.getRichFast.UI;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.getRichFast.Database.DatabaseConnection;
import org.getRichFast.Entity.StockBuild;
import org.getRichFast.Searching.StockSearcher;

public class Menus {

  private ArrayList<StockBuild> stocks;
  private ArrayList<StockBuild> searchedStocks;
  private String menuChoice;

  public void startMenu() {
    try {
      menu();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  private String scan(String message) {
    Scanner sc = new Scanner(System.in);
    System.out.println(message);
    return sc.next();
  }

  private void searchMenu() {
    searchedStocks = null;
    StockSearcher stockSearcher = new StockSearcher(stocks);
    menuChoice = scan("What do you want to search for? 1: Date 2: Symbol");
    switch (menuChoice) {
      case "1":
        searchedStocks = stockSearcher.searchForDate();
        break;
      case "2":
        if (searchedStocks != null) {
          stockSearcher.searchForSymbol();
        } else {
          stockSearcher.searchForSymbol();
        }
        break;
    }
  }

  private void menu() throws IOException, ParseException {
    Boolean abort = false;
    while (!abort) {
      String menuChoice;
      String quandlApiKey;
      String quandlCode;
      DataShifter data = new DataShifter();
      System.out.println("Quandl Downloader and Parser");
      menuChoice = scan("1: Start download and parsing 2: Search in the data E: exit (Please enter your choice)");
      switch (menuChoice) {
        case "1":
          //quandlApiKey = scan("Enter your api-code:");
          //quandlCode = scan("Enter the Quandl-code:");
          quandlApiKey = "VAuKhbFRLKYeucyzd868";
          quandlCode = "FSE/EON_X";

          if (scan("Start downloading Quandl data: " + quandlCode + "with api-code: " + quandlApiKey + "? (y/n)").equals("y")) {
            stocks = data.getAndParseData(quandlApiKey, quandlCode);
            DatabaseConnection database = new DatabaseConnection();
            database.insertDataToDatabase(stocks);

            //FIXME: Menu for outputing all data
            database.outputAllDataFromDatabase();

          } else {
            System.out.println("abort");
          }
          break;

        case "2":
          if (stocks != null) {
            searchMenu();
          } else {
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