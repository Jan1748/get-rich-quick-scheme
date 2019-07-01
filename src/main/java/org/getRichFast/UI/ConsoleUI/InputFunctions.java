package org.getRichFast.UI.ConsoleUI;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class InputFunctions {

  public static String scan(String message){
    Scanner sc = new Scanner(System.in);
    System.out.println(message);
    return sc.next();
  }

  public static String getInputDateString(){
    System.out.println("Please enter the date with this pattern: " + "yyyy-MM-dd");
    Scanner scanner = new Scanner(System.in);
    String date = scanner.nextLine();
    return date;
  }

}
