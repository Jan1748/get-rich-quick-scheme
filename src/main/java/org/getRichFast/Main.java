package org.getRichFast;

import org.getRichFast.Database.DatabaseConnection;
import org.getRichFast.UI.Menus;

public class Main {

  public static void main(String[] args) {
    DatabaseConnection database = new DatabaseConnection();
    database.connect();
    Menus menus = new Menus();
    menus.startMenu();
    DatabaseConnection databaseConnection = new DatabaseConnection();
    //databaseConnection.connect();
  }
}
