package org.getRichFast;

import java.util.Calendar;

public class SearchFunktions {

  public Boolean calendarInterval(Calendar input, Calendar start, Calendar end) {
    if (input.after(start) && input.before(end)) {
      return true;
    }
    return false;
  }

  public Boolean exactCalendarDate(Calendar input, Calendar search) {
    if (input.equals(search)) {
      return true;
    }
    return false;
  }

  public Boolean beforeCalendar(Calendar input, Calendar search) {
    if (input.before(search)) {
      return true;
    }
    return false;
  }

  public Boolean afterCalendar(Calendar input, Calendar search) {
    if (input.after(search)) {
      return true;
    }
    return false;
  }

  public Boolean symbolCheck(String searchedSymbol, String inputSymbol) {
    if(searchedSymbol.equals(inputSymbol)){
      return true;
    }
    return false;
  }
}
