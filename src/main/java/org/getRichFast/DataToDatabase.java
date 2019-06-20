package org.getRichFast;

import java.util.ArrayList;

public class DataToDatabase {

  public void getAndParseData() {
    GetQuandlData getQuandlData = new GetQuandlData("4nAVrexhFHXrX1TuYNsF");
    ArrayList<String> data = getQuandlData.getNotParsedDataArraylist("FSE/WAC_X");
    ParsingCSVToStringArray parsing = new ParsingCSVToStringArray();
    ArrayList<String[]> parsedData = parsing.getDataArrayList(data);
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
}
