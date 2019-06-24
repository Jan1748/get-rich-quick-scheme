package org.getRichFast;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class InputFunctions {

  public Calendar getInputCalendar() {
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

  public BigDecimal getInputValue() {
    return new BigDecimal(scanner());
  }
  public String inputString(){
    return scanner();
  }

  private String scanner() {
    Scanner sc = new Scanner(System.in);
    return sc.next();
  }
}
