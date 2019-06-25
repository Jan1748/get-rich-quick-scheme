package org.getRichFast.Searching;

import java.util.ArrayList;
import java.util.Calendar;
import org.getRichFast.Entity.StockBuild;
import org.getRichFast.UI.InputFunctions;

public class SearchMethods {

  public static ArrayList<StockBuild> dateSearch(ArrayList<StockBuild> stocks, String name) {
    Calendar firstDate = InputFunctions.getInputCalendar();
    ArrayList<StockBuild> stockFounds = new ArrayList<>();
    int counter = 0;

    for (int i = 0; i < stocks.size(); i++) {
      StockBuild stock = stocks.get(i);
      Boolean check = false;
      switch (name) {
        case "exact":
          if (SearchFunktions.exactCalendarDate(stock.getDate(), firstDate)) {
            check = true;
          } break;
        case "interval":
          Calendar endDate = InputFunctions.getInputCalendar();
          if (SearchFunktions.calendarInterval(stock.getDate(), firstDate, endDate)) {
            check = true;
          }
          break;
        case "before":if (SearchFunktions.beforeCalendar(stock.getDate(), firstDate)) {
          check = true;
        }
          break;
        case "after":if (SearchFunktions.afterCalendar(stock.getDate(), firstDate)) {
          check = true;
        }
          break;
      }
      if (check) {
        counter++;
        stockFounds.add(stock);
        System.out.println("#" + counter + " Stock nr " + i + " was added. " + stock.toString());
      }
    }
    System.out.println(counter + " results were found");
    return stockFounds;
  }
}
