package org.getRichFast.Model.InterfaceConnection;

import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Data.Database.Enum.ChartEnum;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Charts.CreateCandleStickChartPNG;
import org.getRichFast.Model.Charts.CreateLineChartPNG;
import org.getRichFast.Model.Charts.StockFolderCreator;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;
import org.getRichFast.Model.Entity.StockBuild;
import org.getRichFast.Model.ProcessDecisions;
import org.getRichFast.Model.RequestEditor;
import org.getRichFast.Model.RequestEditorThreads;

import java.io.IOException;
import java.util.ArrayList;
import org.getRichFast.UI.ConsoleUI.Menus;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.junit.jupiter.api.parallel.Execution;

public class InterfaceConnectorToDatabase implements ProcessDecisions {

  private DataReceiver dataReceiver = new DatabaseConnection();
  private RequestEditor requestEditor = new RequestEditor();
  private DatabaseConnection databaseConnection = new DatabaseConnection();


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
    ArrayList<RequestEditorThreads> threadsArrayList = new ArrayList<>();
    for (int i = 0; i < threads; i++) {
      RequestEditorThreads requestEditorThreads = new RequestEditorThreads(symbols, symbol, apiKey, number, (number + numbersize), i);
      requestEditorThreads.start();
      threadsArrayList.add(requestEditorThreads);
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      number += numbersize;
    }
    for (int i = 0; i < threads; i++) {
      while (threadsArrayList.get(i).isAlive()) {
        //System.out.println("Is Alive");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println("All Threads Finished");
    Menus menus = new Menus();
    menus.startMenu();
  }

  @Override
  public void downloadQuandlSingleStock(String symbol, String apiKey) {
    requestEditor.downloadQuandlSingleStock(symbol, apiKey);
  }

  @Override
  public void createLineChart(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol, String symbol2) {
    ArrayList<StockBuild> stockData = dataReceiver.getQueriedDataset(valueEnum, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);
    ArrayList<StockBuild> stockData2 = dataReceiver.getQueriedDataset(valueEnum, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol2);
    if (stockData!= null && stockData2 != null){
      CreateLineChartPNG createLineChartPNG = new CreateLineChartPNG();
      createLineChartPNG.generateChartPNG(stockData, stockData2);
    }
  }

  @Override
  public void createCandleStickChart(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol) {
    ArrayList<StockBuild> stockData = dataReceiver.getQueriedDataset(valueEnum, symbolEnum, dateEnum, columnNameEnum, date, date2, symbol);
    if (stockData.size() >= 1) {
      CreateCandleStickChartPNG createCandleStickChartPNG = new CreateCandleStickChartPNG();
      createCandleStickChartPNG.candlestick(stockData, null);
    }
  }

  @Override
  public void generateAllChartsFromStock(String stockName, String apiKey, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2) {
    try {
      ArrayList<String> codnames = QuandlCodeFinder.getQuandlCodes(stockName, apiKey);
      for (int i = 1; i < codnames.size(); i++) {
        createLineChart(ValueEnum.ALL, SymbolEnum.ATTACHED, dateEnum, columnNameEnum, date, date2, codnames.get(i), null);
        createCandleStickChart(ValueEnum.ALL, SymbolEnum.ATTACHED, dateEnum, columnNameEnum, date, date2, codnames.get(i));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void visulizeTwoCharts(String stockName, String apiKey, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2) {
    ArrayList<String> codnames = null;
    try {
      codnames = QuandlCodeFinder.getQuandlCodes(stockName, apiKey);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int i =1;i < codnames.size(); i++) {
      for (int x =1;x < codnames.size(); x++) {
        if (i != x) {
          createLineChart(ValueEnum.ALL, SymbolEnum.ATTACHED, dateEnum, columnNameEnum, date, date2, codnames.get(i), codnames.get(x));
        }
      }
    }

  }
}