package org.getRichFast.UI;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import org.getRichFast.Database.DatabaseConnection;
import org.getRichFast.Downloading.QuandlCodeFinder;
import org.getRichFast.Entity.DataShifter;
import org.getRichFast.Entity.StockBuild;
import org.getRichFast.Searching.SymbolSearcher;
import org.getRichFast.Searching.ValueSearcher;

public class Menus {

  private ArrayList<StockBuild> stocks;
  private String menuChoice;
  private String quandlApiKey;


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
      ResultSet searchedStocks = null;
      ValueSearcher valueSearcher = new ValueSearcher();
      SymbolSearcher symbolSearcher = new SymbolSearcher();
      menuChoice = scan("What do you want to search for? \n1: Date \n2: Symbol");
      switch (menuChoice) {
        case "1":
          searchedStocks = valueSearcher.searchForDate();
          break;
        case "2":
          searchedStocks = symbolSearcher.searchForSymbol();
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
          //String quandlApiKeyNew = scan("Enter your new api-code or press 'o' to use the old one:");
          //if(!quandlApiKeyNew.equals("o")) {
          //  quandlApiKey = quandlApiKeyNew;
          //}
          //quandlCode = scan("Enter the Quandl-code:");
          quandlApiKey = "VAuKhbFRLKYeucyzd868";
          quandlCode = "FSE/ON_X";

          if (scan("Start downloading Quandl data: " + quandlCode + " with api-code: " + quandlApiKey + "? (y/n)").equals("y")) {
            QuandlCodeFinder quandlCodeFinder = new QuandlCodeFinder();
            ArrayList<String> quandlCodes = null;
            try {
              quandlCodes = quandlCodeFinder.getQuandlCodes("FSE", "VAuKhbFRLKYeucyzd868");
            } catch (IOException e) {
              e.printStackTrace();
            }
            String quandlCodee;
            for(int i = 0; i < quandlCodes.size(); i++) {
              quandlCodee = "FSE/" + quandlCodes.get(i);
              System.out.println("Stock nr. " + i + ": " + quandlCodee);
              stocks = data.getAndParseData(quandlApiKey, quandlCodee);
              DatabaseConnection database = new DatabaseConnection();
              database.insertDataToDatabase(stocks);
            }
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
        case "E":
        case"e":
          System.exit(0);
          break;
        default:
          System.out.println("Please enter a valid choice");
      }
    }
  }
}