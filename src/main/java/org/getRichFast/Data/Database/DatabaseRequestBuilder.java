package org.getRichFast.Data.Database;


import org.getRichFast.Data.Database.Enum.ColumNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;

public class DatabaseRequestBuilder {

  public static String requestBuild(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, String date, String date2, String symbol) {
    String symbolCode = "";
    String dateCode = "";
    if (date != null) {
      dateCode = getStringExactDatePostgresql(date, date2, dateEnum);
    }
    if (symbol != null) {
      symbolCode = getSymbolCondition(symbol, symbolEnum);
    }
    switch (valueEnum) {
      case MAX:
        return getHighestValues(dateCode, symbolCode);
      case MIN:
        return getLowestValues(dateCode, symbolCode);
    }
    return null;
  }

  private static String getStringExactDatePostgresql(String date, String date2, DateEnum dateEnum) {
    String sqlCode;
    switch (dateEnum) {
      case EXACT:
        sqlCode = "WHERE \"Date\" = ' " + date + "'";
        return sqlCode;
      case INTERVAL:
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

  private static String getSymbolCondition(String symbol, SymbolEnum symbolEnum) {
    String symbolCode = "";
    if (symbolEnum == SymbolEnum.ATTACHED) {
      if (symbol != null) {
        symbolCode = "AND \"Symbol\" = '" + symbol + "'";
      }
    }
    if (symbolEnum == SymbolEnum.SINGLE) {
      if (symbol != null) {
        symbolCode = "WHERE \"Symbol\" = '" + symbol + "';";
      }
    }
    return symbolCode;
  }

  private static String columnNameString(ColumNameEnum columNameEnum) {
    switch (columNameEnum) {
      case CLOSE:
        return "Close";
      case LOW:
        return "Low";
      case OPEN:
        return "Open";
      case HIGH:
        return "High";
    }
    return null;
  }

  private static String getHighestValues(String dateCondition, String symbol) {
    String code = "";
    for (ColumNameEnum columnName : ColumNameEnum.values()) {
      code += "SELECT MAX (\"" + columnNameString(columnName) + "\") FROM stockbuild" + dateCondition + " " + symbol + ";";
    }
    return code;
  }

  private static String getLowestValues(String dateCondition, String symbol) {
    String code = "";
    for (ColumNameEnum columnName : ColumNameEnum.values()) {
      code += "SELECT MIN (\"" + columnNameString(columnName) + "\") FROM stockbuild " + dateCondition + " " + symbol + ";";
    }
    return code;
  }
}


