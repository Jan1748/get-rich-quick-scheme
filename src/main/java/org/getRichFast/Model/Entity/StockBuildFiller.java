package org.getRichFast.Model.Entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import org.getRichFast.Model.Parsing.LineParser;

public class StockBuildFiller {

  private LineParser lineParser = new LineParser();

  public ArrayList<StockBuild> fillStockBuild(ArrayList<String[]> data, String name) throws ParseException {

    System.out.println("Start filling StockBuild");

    ArrayList<StockBuild> stock = new ArrayList<>();
    for (int x = 1; x < data.size(); x++) {
      StockBuild stockBuild = new StockBuild(name, lineParser.parseToCalendar(data.get(x)[0]));
      stockBuild.setOpen(lineParser.parseToBigDecimal(data.get(x)[1]));
      stockBuild.setHigh(lineParser.parseToBigDecimal(data.get(x)[2]));
      stockBuild.setLow(lineParser.parseToBigDecimal(data.get(x)[3]));
      stockBuild.setClose(lineParser.parseToBigDecimal(data.get(x)[4]));
      stock.add(stockBuild);
    }
    System.out.println("StockBuild filled successfully");
    return stock;
  }

}
