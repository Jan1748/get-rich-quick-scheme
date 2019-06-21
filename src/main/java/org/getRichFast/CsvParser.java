package org.getRichFast;

import java.util.ArrayList;

public class CsvParser {

  private String[] splitingStringLine(String line) {
    String[] splited = line.split(",");
    return splited;
  }

  public ArrayList<String[]> getDataArrayList(ArrayList<String> lines) {
    System.out.println("Start parsing data");
    ArrayList<String[]> data = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      data.add(splitingStringLine(lines.get(i)));
    }
    System.out.println("\tParsing successfully completed");
    return data;
  }
}