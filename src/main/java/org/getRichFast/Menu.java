package org.getRichFast;

//https://search.maven.org/search?q=g:org.junit.jupiter%20AND%20v:5.4.2

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Menu {

  private StockBuild[] stocks;

  public void startMenu(String quandlApiKey, String quandlCode) {
    try {
      menu(quandlApiKey, quandlCode);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  private void menu(String quandlApiKey, String quandlCode) throws IOException, ParseException {
    String menuChoice;
    Scanner scanner = new Scanner(System.in);
    DataShifter data = new DataShifter();
    System.out.println("Quandl Downloader and Parser");
    System.out.println("1: Start download and parsing (Please enter your choice)");
    menuChoice = scanner.nextLine();
    System.out.println(menuChoice);
    if (menuChoice.equals("1")) {
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
      }
    } else {
      System.out.println("This is not a option");
    }
    StockSercher stockSercher = new StockSercher(stocks);
    stockSercher.serchInStocks();
  }

}
