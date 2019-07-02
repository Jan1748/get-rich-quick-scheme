package org.getRichFast.Data.Database;


public class DatabaseRequestBuilder {

  public static String requestBuild(ValueEnum valueEnum, ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date2, String symbol) {
    String symbolCode = null;
    String dateCode = null;
    if (date != null) {
      dateCode = getStringExactDatePostgresql(date, date2, dateEnum);
    }
    if (symbol != null) {
      symbolCode = getSymbolCondition(symbol);
    }
    switch (valueEnum) {
      case MAX:
        return getHighestValues(dateCode, columNameEnum, symbolCode);
      case MIN:
        return getLowestValues(dateCode, columNameEnum, symbolCode);
    }
    return null;
  }


  private static String getStringExactDatePostgresql(String date, String date2, DateEnum dateEnum) {
    String sqlCode;
    switch (dateEnum) {
      case EXACT:
        sqlCode = "WHERE \"Date\" = ' " + date + "'";
        return sqlCode;
      case INTERVALL:
        sqlCode = "WHERE \"Date\" > '" + date + "' AND \"Date\" < '" + date2 + "'";
        return sqlCode;
      case BEFORE:
        sqlCode = "WHERE \"Date\" < '" + date + "'";
        return sqlCode;
      case AFTER:
        sqlCode = "WHERE \"Date\" > '" + date + "'";
        return sqlCode;
    }
    return null;
  }

  private static String getSymbolCondition(String symbol) {
    String symbolCode = "AND \"Symbol\" = '" + symbol + "'";
    return symbolCode;
  }

  private static String getHighestValues(String dateCondition, ColumNameEnum columNameEnum, String symbol) {
    String code = null;
    code = "SELECT MAX (\"" + columNameEnum.toString() + "\") FROM stockbuild " + dateCondition + " " + symbol + ";";
    return code;
  }

  private static String getLowestValues(String dateCondition, ColumNameEnum columNameEnum, String symbol) {
    String code = null;
    code = "SELECT MIN (\"" + columNameEnum.toString() + "\") FROM stockbuild " + dateCondition + " " + symbol + ";";
    return code;
  }
}


