package org.getRichFast;

import java.util.ArrayList;

public class DataToDatabase {

  public void getAndParseData() {
    String quandlcode = "FSE/WAC_X";
    GetQuandlData getQuandlData = new GetQuandlData("4nAVrexhFHXrX1TuYNsF");
    ArrayList<String> data = getQuandlData.getNotParsedDataArraylist(quandlcode);
    ParsingCSVToStringArray parsing = new ParsingCSVToStringArray();
    ArrayList<String[]> parsedData = parsing.getDataArrayList(data);
    //putdataIntoStockBuild(parsedData, quandlcode);
    //StockBuild stockBuild = new StockBuild();
    testOutput(parsedData);
  }

  private void testOutput(ArrayList<String[]> data) {
    String[] names = data.get(0);
    for (int i = 1; i < data.size(); i++) {
      String[] mystring = data.get(i);
      for (int x = 0; x < mystring.length; x++) {
        System.out.println(names[x] + ": " + mystring[x]);
      }
      System.out.println(" ");
    }
  }
  //void putdataIntoStockBuild(ArrayList<String[]> data, String quandlcode){
  //  for (int i = 1; i < data.size(); i++) {
  //    String[] mystring = data.get(i);
  //    for (int x = 0; x < mystring.length; x++) {
  //    }
  //  }
  //}
}
