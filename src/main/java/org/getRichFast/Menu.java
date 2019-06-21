package org.getRichFast;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

  public static void main(String[] args) throws IOException {
    menu(args);
  }

  private static void menu(String[] args) throws IOException {
    String menuChoice;
    Scanner scanner = new Scanner(System.in);
    DataShifter data = new DataShifter();
    System.out.println("Quandl Downloader and Parser");
    System.out.println("1: Start download and parsing (Please enter your choice)");
    menuChoice = scanner.nextLine();
    System.out.println(menuChoice);
    if(menuChoice.equals("1")){
      System.out.println("Enter your api-code:");
      String apiCode = scanner.nextLine();
      System.out.println("Enter the Quandl-code:");
      String quandlCode = scanner.nextLine();
      System.out.println("Start downloading Quandl data: " + apiCode + "with api-code: " + apiCode + "? (y/n)");
      if(scanner.nextLine().equals("y")){
        data.getAndParseData(args);
      }
    }
    else {
      System.out.println("This is not a option");
    }
  }

}
