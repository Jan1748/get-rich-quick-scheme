package org.getRichFast.Model.InterfaceConnection;

import java.util.ArrayList;
import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.ProcessDecisions;
import org.getRichFast.Model.RequestEditor;

public class InterfaceConnector implements ProcessDecisions {

  private DataReceiver dataReceiver;
  private RequestEditor requestEditor = new RequestEditor();

  @Override
  public void searchForValue(ValueEnum valueEnum, DateEnum dateEnum, String date, String date2, String symbol) {
    dataReceiver.search(valueEnum, dateEnum, date, date2, symbol);
  }

  @Override
  public void downloadQuandlWholeStockMarket(String symbol, String apiKey) {
    requestEditor.downloadQuandlWholeStockMarket(symbol, apiKey);
  }

  @Override
  public void downloadQuandlSingleStock(String symbol, String apiKey) {
    requestEditor.downloadQuandlSingleStock(symbol, apiKey);
  }
}