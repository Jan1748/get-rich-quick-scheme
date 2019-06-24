package org.getRichFast;

import java.util.Calendar;

public class SearchFunktions {
  public Boolean calendarInterval(Calendar input, Calendar start, Calendar end){
    if(input.after(start) && input.before(end)){
      return true;
    }
    return false;
  }
  public Boolean exactCalendarDate(Calendar input, Calendar search){
    if(input.equals(search)){
      return true;
    }
    return false;
  }

}
