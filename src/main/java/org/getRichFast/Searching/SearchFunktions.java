package org.getRichFast.Searching;

import java.util.Calendar;

//FIXME: ich kaufe ein C
public class SearchFunktions {

  public static Boolean calendarInterval(Calendar input, Calendar start, Calendar end) {
    return input.after(start) && input.before(end);
  }

  public static Boolean exactCalendarDate(Calendar input, Calendar search) {
    return input.equals(search);
  }

  public static Boolean beforeCalendar(Calendar input, Calendar search) {
    return input.before(search);
  }

  public static Boolean afterCalendar(Calendar input, Calendar search) {
    return input.after(search);
  }

  public static Boolean symbolCheck(String searchedSymbol, String inputSymbol) {
    return searchedSymbol.equals(inputSymbol);
  }
}
