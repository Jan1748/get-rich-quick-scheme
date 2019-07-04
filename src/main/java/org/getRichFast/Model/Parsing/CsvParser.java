package org.getRichFast.Model.Parsing;

import java.util.ArrayList;

public class CsvParser {

  private String[] splitingStringLine(String line) {
    String[] splited = line.split(",");
    String[] returnSting = new String[]{"", "", "", "", "", "", "", ""};
    for (int i = 0; i < 6; i++) {
      if (splited.length > i) {
        returnSting[i] = splited[i];
      }
    }
    return returnSting;
  }

  public ArrayList<String[]> getDataArrayList(ArrayList<String> lines) {
    //System.out.println("Start parsing data");
    ArrayList<String[]> data = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      data.add(splitingStringLine(lines.get(i)));
    }
    //System.out.println("Csv parsing successfully completed");
    return data;
  }
}