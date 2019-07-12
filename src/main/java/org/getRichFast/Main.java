package org.getRichFast;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.getRichFast.Data.DataReceiver;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Model.Algorithms.CrossCoroliationFinder;
import org.getRichFast.UI.ConsoleUI.Menus;

public class Main {

  public static void main(String[] args) {
//    CrossCoroliationFinder crossCoroliationFinder = new CrossCoroliationFinder();
//    ArrayList<String[]> test = crossCoroliationFinder.getCrossCoroliation("FSE", "4nAVrexhFHXrX1TuYNsF", SymbolEnum.SINGLE, DateEnum.NULL, null, null, BigDecimal.valueOf(10));
//    for (int x = 0; x < test.size(); x++) {
//      System.out.println("Stock 1: " + test.get(x)[0] + " Stock 2: " + test.get(x)[1]);
//    }
    Menus menus = new Menus();
    menus.startMenu();
  }
}
