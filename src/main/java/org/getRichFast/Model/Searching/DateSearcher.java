package org.getRichFast.Model.Searching;

import org.getRichFast.UI.ConsoleUI.InputFunctions;

public class DateSearcher {

  public String getStringExactDatePostgresql(String date) {
    String sqlCode;
    sqlCode = "WHERE \"Date\" = ' " + date + "'";
    return sqlCode;
  }

  public String getStringIntervalDatePostgresql(String date1, String date2) {
    String sqlCode;
    sqlCode = "WHERE \"Date\" > '" + date1 + "' AND \"Date\" < '" + date2 + "'";
    return sqlCode;
  }

  public String getStringBeforeDate(String date) {
    String sqlCode;
    sqlCode = "WHERE \"Date\" < '" + date + "'";
    return sqlCode;
  }

  public String getStringAfterDate(String date) {
    String sqlCode;
    sqlCode = "WHERE \"Date\" > '" + date + "'";
    return sqlCode;
  }
}
