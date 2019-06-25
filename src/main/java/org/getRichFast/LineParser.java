package org.getRichFast;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LineParser {

  public Calendar parseToCalendar(String dateString) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = dateFormat.parse(dateString);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  public BigDecimal parseToBigDecimal(String number) {
    if (number.equals("")) {
      return null;
    } else {
      return new BigDecimal(number);
    }
  }
}
