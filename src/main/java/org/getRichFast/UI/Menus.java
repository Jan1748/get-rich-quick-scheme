package org.getRichFast.UI;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import org.getRichFast.Database.DatabaseConnection;
import org.getRichFast.Entity.DataShifter;
import org.getRichFast.Entity.StockBuild;
import org.getRichFast.Searching.DateSearcher;
import org.getRichFast.Searching.SymbolSearcher;
import org.getRichFast.Searching.ValueSearcher;

public class Menus {

  private ArrayList<StockBuild> stocks;
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
    ResultSet[] searchedValues = null;
    DateSearcher dateSearcher = new DateSearcher();
    ValueSearcher valueSearcher = new ValueSearcher();
    SymbolSearcher symbolSearcher = new SymbolSearcher();
    DatabaseOutput databaseOutput = new DatabaseOutput();
    ResultSet searchedSymbols;
    menuChoice = scan("What do you want to search for? \n1: Date \n2: Symbol");
    switch (menuChoice) {
      case "1":
        searchedValues = valueSearcher.searchForValue(dateSearcher.searchForDate());
        if (searchedValues != null){
          //databaseOutput.outputDatabaseDataArray(searchedValues);
        }
        break;
      case "2":
        searchedSymbols = symbolSearcher.searchForSymbol();
        if (searchedSymbols != null) {
          databaseOutput.outputDatabaseData(searchedSymbols);
        }
        break;
      default:
        System.out.println("Please enter a valid choice");
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
      menuChoice = scan("1: Start download and parsing \n2: Search in the data \n3: Output all Data from Database to your Console \ne: exit (Please enter your choice)");
      switch (menuChoice) {
        case "1":
          //quandlApiKey = scan("Enter your api-code:");
          quandlCode = scan("Enter the Quandl-code:");
          quandlApiKey = "VAuKhbFRLKYeucyzd868";
          //quandlCode = "FSE/EON_X";

          if (scan("Start downloading Quandl data: " + quandlCode + "with api-code: " + quandlApiKey + "? (y/n)").equals("y")) {
            stocks = data.getAndParseData(quandlApiKey, quandlCode);
            DatabaseConnection database = new DatabaseConnection();
            database.insertDataToDatabase(stocks);
          } else {
            System.out.println("abort");
          }
          break;

        case "2":
          searchMenu();
          break;
        case "3":
          DatabaseConnection database = new DatabaseConnection();
          database.outputAllDataFromDatabase();
          break;
        case "e":
          System.exit(0);
          break;
        default:
          System.out.println("Please enter a valid choice");
      }
    }
  }
}