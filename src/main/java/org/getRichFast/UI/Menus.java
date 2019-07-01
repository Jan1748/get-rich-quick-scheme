package org.getRichFast.UI;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import org.getRichFast.Database.DatabaseConnection;
import org.getRichFast.Downloading.QuandlCodeFinder;
import org.getRichFast.Entity.DataShifter;
import org.getRichFast.Entity.StockBuild;
import org.getRichFast.Properties.PropertiesCreate;
import org.getRichFast.Searching.DateSearcher;
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
    try {
      InputStream input = new FileInputStream("src/main/resources/config.properties");
      Properties prop =new Properties();
      prop.load(input);
      this.quandlApiKey = prop.getProperty("api.Key");
    } catch (IOException e) {
      e.printStackTrace();
    }
    Boolean abort = false;
    while (!abort) {
      String menuChoice;
      String quandlCode;
      DataShifter data = new DataShifter();
      System.out.println("Quandl Downloader and Parser");
      menuChoice = scan("1: Start download and parsing \n2: Search in the data \n3: Output all Data from Database to your Console \n4: Set all Properties to Default\ne: exit (Please enter your choice)");
      switch (menuChoice) {
        case "1":
          quandlCode = InputFunctions.scan("Enter the Quandle Code.");
          if (scan("Start downloading Quandl data: " + quandlCode + " with api-code: " + quandlApiKey + "? (y/n)").equals("y")) {
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
        case "4":
          PropertiesCreate.createProperties();
        case "5":
          QuandlCodeFinder quandlCodeFinder = new QuandlCodeFinder();
          ArrayList<String> quandlCodes = null;
          try {
            quandlCodes = quandlCodeFinder.getQuandlCodes("FSE", "VAuKhbFRLKYeucyzd868");
          } catch (IOException e) {
            e.printStackTrace();
          }
          String quandlCodee;
          for(int i = 1; i < quandlCodes.size(); i++) {
            quandlCodee = "FSE/" + quandlCodes.get(i);
            System.out.println("Stock nr. " + i + ": " + quandlCodee);
            stocks = data.getAndParseData(quandlApiKey, quandlCodee);
            DatabaseConnection databases = new DatabaseConnection();
            databases.insertDataToDatabase(stocks);
          }
        case "e":
          System.exit(0);
          break;
        default:
          System.out.println("Please enter a valid choice");
      }
    }
  }
}