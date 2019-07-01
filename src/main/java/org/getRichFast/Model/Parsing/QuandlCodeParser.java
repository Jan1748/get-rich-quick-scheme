package org.getRichFast.Model.Parsing;

public class QuandlCodeParser {

  public static String getQuandlCodes(String line) {
    line.replace("(", ":");
    line.replace(")", ":");
    String[] splited = line.split(":");
    String splitComma = splited[0];
    String[] split = splitComma.split(",");
    return split[0];
  }
}
