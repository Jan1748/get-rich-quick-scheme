package org.getRichFast.Model.Searching;

import org.getRichFast.UI.ConsoleUI.InputFunctions;

public class DateSearcher {

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
