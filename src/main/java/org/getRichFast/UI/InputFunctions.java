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
        System.out.println("Now enter the date with this pattern." + "yyyy-MM-dd");
        date = dateFormat.parse(scanner());
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  public static BigDecimal getInputValue() {
    return new BigDecimal(scanner());
  }
  public static String inputString(){
    return scanner();
  }

  private static String scanner() {
    Scanner sc = new Scanner(System.in);
    return sc.next();
  }
}
