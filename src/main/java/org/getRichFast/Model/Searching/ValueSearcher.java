package org.getRichFast.Model.Searching;

import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.DateEnum;
import org.getRichFast.Model.ProcessDecisions;

public class ValueSearcher implements ProcessDecisions {

  //FIXME: maybe separate UI- code from Model- from Data-Code!!

  private DatabaseConnection databaseConnection = new DatabaseConnection();
  private DateSearcher dateSearcher = new DateSearcher();
  private SymbolSearcher symbolSearcher = new SymbolSearcher();

  private void getLowestValues(String dateCondition, String symbol) {
    String[] value = new String[]{"Open", "High", "Low", "Close"};
    String code = null;
    for (int x = 0; x < 4; x++) {
      //sqlStatement = connection.prepareStatement("SELECT MIN (?) FROM stockbuild ?;");
      //sqlStatement.setString(1, value[x]);
      //sqlStatement.setString(1, dateCondition);
      code = "SELECT MIN (\"" + value[x] + "\") FROM stockbuild " + dateCondition + " " + symbol + ";";
    }
  }

  private void getHighestValues(String dateCondition, String symbol) {
    String[] value = new String[]{"Open", "High", "Low", "Close"};
    String code = null;
      for (int x = 0; x < 4; x++) {
//        sqlStatement = connection.prepareStatement("SELECT MAX (?) FROM stockbuild ?;");
//        sqlStatement.setString(1, value[x]);
//        sqlStatement.setString(2, dateCondition);
        code = "SELECT MAX (\"" + value[x] + "\") FROM stockbuild " + dateCondition + " " + symbol + ";";

    }
  }

  private DataReceiver dataReceiver;

  @Override
  public void searchForHighestValue(DateEnum dateEnum, String date, String date2, String symbol) {
    getHighestValues();
  }

  @Override
  public void searchForLowestValue(DateEnum dateEnum, String date, String date2, String symbol) {

  }

  @Override
  public void downloadQuandlWholeStockMarket(String symbol, String apiKey) {

  }

  @Override
  public void downloadQuandlSingleStock(String symbol, String apiKey) {

  }
}