package org.getRichFast;

import java.util.ArrayList;

public class ParsingCSVToStringArray {
  private String[] parsingStringLine(String line) {
    String[] splited = line.split(",");
    return splited;
  }
  public ArrayList<String[]> getDataArrayList(ArrayList<String> lines){
    ArrayList<String[]> data = new ArrayList<>();
    for( int i = 0; i < lines.size(); i++){
      data.add(parsingStringLine(lines.get(i)));
    }
    return data;
  }
}