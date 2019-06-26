package org.getRichFast.UI;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class InputFunctions {

  public static Calendar getInputCalendar() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    while (date == null) {
      try {
        date = dateFormat.parse(scan("Now enter the date with this pattern." + "yyyy-MM-dd"));
      } catch (ParseException e) {
        System.out.println("Please enter a valid date");
      }
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  public static BigDecimal getInputValue() {
    return new BigDecimal(scan(""));
  }

  public static String scan(String message){
    Scanner sc = new Scanner(System.in);
    System.out.println(message);
    return sc.next();
  }

}
