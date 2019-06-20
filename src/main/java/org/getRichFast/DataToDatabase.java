package org.getRichFast;

import java.util.ArrayList;

public class DataToDatabase {

  private void getAndParseData(){
    GetQuandlData getQuandlData = new GetQuandlData("4nAVrexhFHXrX1TuYNsF");
    ArrayList<String> data = getQuandlData.getNotParsedDataArraylist("FSE/WAC_X");
    ParsingCSVToStringArray parsing = new ParsingCSVToStringArray;
    ArrayList<String[]> parsedData = parsing.getDataArrayList(data);
  }
  void testOutput(){

  }

}
