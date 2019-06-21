package org.getRichFast;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LineParser {

  //FIXME: think about using Calendar instead of Date
  public Calendar parseToCalendar(String dateString) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = dateFormat.parse(dateString);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    //Calendar calendar = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    return cal;
  }

  public BigDecimal parseToBigDecimal(String number) {
    if (number.equals("")) {
      return new BigDecimal(0);
    } else {
      return new BigDecimal(number);
    }
  }
}
