package org.getRichFast.Model.InterfaceConnection;

import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Charts.CreateLineChartPNG;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;
import org.getRichFast.Model.Entity.StockBuild;
import org.getRichFast.Model.ProcessDecisions;
import org.getRichFast.Model.RequestEditor;
import org.getRichFast.Model.RequestEditorThreads;

import java.io.IOException;
import java.util.ArrayList;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class InterfaceConnectorToDatabase implements ProcessDecisions {

  private DataReceiver dataReceiver = new DatabaseConnection();
  private RequestEditor requestEditor = new RequestEditor();


  @Override
  public void searchForValue(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    dataReceiver.search(valueEnum, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);
  }

  @Override
  public void downloadQuandlWholeStockMarket(String symbol, String apiKey) {
    //requestEditor.downloadQuandlWholeStockMarket(symbol, apiKey);
    ArrayList<String> symbols = null;
    try {
      symbols = QuandlCodeFinder.getQuandlCodes(symbol, apiKey);
    } catch (IOException e) {
      e.printStackTrace();
    }
    int threads = 8;
    int numbersize = symbols.size() / threads;
    int number = 1;
    for(int i = 0; i < threads; i++) {
      RequestEditorThreads requestEditorThreads = new RequestEditorThreads(symbols, symbol, apiKey, number, (number + numbersize), i);
      requestEditorThreads.start();
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      number+=numbersize;
    }
  }

  @Override
  public void downloadQuandlSingleStock(String symbol, String apiKey) {
    requestEditor.downloadQuandlSingleStock(symbol, apiKey);
  }

  @Override
  public void createLineChart(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum,ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    ArrayList<StockBuild> stockData = dataReceiver.getQueriedDataset(valueEnum, symbolEnum, dateEnum,columnNameEnum, date, date2, symbol);
    CreateLineChartPNG createLineChartPNG = new CreateLineChartPNG();
    createLineChartPNG.generateChartPNG(stockData);
  }
}