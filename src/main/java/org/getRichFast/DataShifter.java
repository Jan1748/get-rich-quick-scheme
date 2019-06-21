package org.getRichFast;

import java.io.IOException;
import java.util.ArrayList;

public class DataShifter {

  public void getAndParseData(String[] args) throws IOException {

    String quandlApiKey = args[0];
    String quandlCode = args[1];
    QuandlDownloader quandlDownloader = new QuandlDownloader(quandlApiKey);
    ArrayList<String> data = quandlDownloader.getNotParsedDataArraylist(quandlCode);
    CsvParser parsing = new CsvParser();
    ArrayList<String[]> parsedData = parsing.getDataArrayList(data);
    //putdataIntoStockBuild(parsedData, quandlcode);
    //StockBuild stockBuild = new StockBuild();

    //TODO: Make a console application to output the data

  }

  //void putdataIntoStockBuild(ArrayList<String[]> data, String quandlcode){
  //  for (int i = 1; i < data.size(); i++) {
  //    String[] mystring = data.get(i);
  //    for (int x = 0; x < mystring.length; x++) {
  //    }
  //  }
  //}
}
