package org.getRichFast;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StockBuildFiller {

  private LineParser lineParser = new LineParser();

  public StockBuild[] fillStockBuild(ArrayList<String[]> data, String name) throws ParseException {

    System.out.println("Start filling StockBuild");

    StockBuild[] stock = new StockBuild[data.size() - 1];
    BigDecimal[] stockBuildBigDecimal = new BigDecimal[data.get(0).length];
    Calendar date = null;

    for(int x = 1; x < data.size(); x++) {
      for(int y = 0; y <= 4; y++) {
        if (y == 0) {
          date = lineParser.parseToCalendar(data.get(x)[y]);
        } else {
          stockBuildBigDecimal[y - 1] = lineParser.parseToBigDecimal(data.get(x)[y]);
        }
      }
      StockBuild stockBuild = new StockBuild(name, date, stockBuildBigDecimal[0], stockBuildBigDecimal[1], stockBuildBigDecimal[2], stockBuildBigDecimal[3]);
      stock[x - 1] = stockBuild;
      System.out.println("\tstockBuild " + x + " filled");
    }
    System.out.println("\tStockBuild filled successfully");
    return stock;
  }

}
