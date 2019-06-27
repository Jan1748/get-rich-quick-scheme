package org.getRichFast;

import org.getRichFast.Database.QueryDatabase;
import org.getRichFast.UI.Menus;

public class Main {

  public static void main(String[] args) {
    QueryDatabase queryDatabase = new QueryDatabase();
    queryDatabase.quaryExactDate();
    Menus menus = new Menus();
    menus.startMenu();
  }
}
