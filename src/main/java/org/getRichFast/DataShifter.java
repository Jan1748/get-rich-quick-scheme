package org.getRichFast;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class DataShifter {

  public void getAndParseData(String[] args) throws IOException, ParseException {

    String quandlApiKey = args[0];
    String quandlCode = args[1];
    QuandlDownloader quandlDownloader = new QuandlDownloader(quandlApiKey);
    ArrayList<String> data = quandlDownloader.getNotParsedDataArraylist(quandlCode);
    CsvParser csvParser = new CsvParser();
    ArrayList<String[]> csvParsedData = csvParser.getDataArrayList(data);
    StockBuildFiller stockBuildFiller = new StockBuildFiller();
    StockBuild[] stock = stockBuildFiller.fillStockBuild(csvParsedData, quandlCode);
    for (int x = 0; x < stock.length; x++) {
      System.out.println(stock[x].toString());
    }

    //putdataIntoStockBuild(parsedData, quandlcode);
    //StockBuild stockBuild = new StockBuild();

  }

  //void putdataIntoStockBuild(ArrayList<String[]> data, String quandlcode){
  //  for (int i = 1; i < data.size(); i++) {
  //    String[] mystring = data.get(i);
  //    for (int x = 0; x < mystring.length; x++) {
  //    }
  //  }
  //}
}
