package org.getRichFast.Searching;

import java.sql.ResultSet;
import org.getRichFast.UI.InputFunctions;

public class DateSearcher {

  public String searchForDate() {
    String message = "What type of date do you want to search for? 1: Exact date 2: Interval of dates 3: Everything before date 4: Everything after date";

    switch (InputFunctions.scan(message)) {
      case "1":
        return getStringExactDatePostgresql(InputFunctions.getInputDateString());

      case "2":
        return getStringIntervalDatePostgresql(InputFunctions.getInputDateString(), InputFunctions.getInputDateString());
      case "3":
        return getStringBeforeDate(InputFunctions.getInputDateString());
      case "4":
        return getStringAfterDate(InputFunctions.getInputDateString());
      default:
        System.out.println("Please enter a valid choice");
    }
    return null;
  }

  private String getStringExactDatePostgresql(String date) {
    String sqlCode;
    sqlCode = "WHERE \"Date\" = ' " + date + "'";
    return sqlCode;
  }

  private String getStringIntervalDatePostgresql(String date1, String date2) {
    String sqlCode;
    sqlCode = "WHERE \"Date\" > '" + date1 + "' AND \"Date\" < '" + date2 + "'";
    return sqlCode;
  }

  private String getStringBeforeDate(String date) {
    String sqlCode;
    sqlCode = "WHERE \"Date\" < '" + date + "'";
    return sqlCode;
  }

  private String getStringAfterDate(String date) {
    String sqlCode;
    sqlCode = "WHERE \"Date\" > '" + date + "'";
    return sqlCode;
  }
}
