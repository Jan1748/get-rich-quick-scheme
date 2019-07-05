package org.getRichFast;

import org.getRichFast.Data.Database.DatabaseRequestBuilder;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Charts.CreateCandleStickChartPNG;
import org.getRichFast.Model.Charts.CreateLineChartPNG;
import org.getRichFast.Model.InterfaceConnection.InterfaceConnectorToDatabase;
import org.getRichFast.UI.ConsoleUI.Menus;

public class Main {
  public static void main(String[] args) {
    InterfaceConnectorToDatabase interfaceConnectorToDatabase = new InterfaceConnectorToDatabase();
    interfaceConnectorToDatabase.createLineChart(ValueEnum.ALL, SymbolEnum.ATTACHED, DateEnum.INTERVAL, ColumnNameEnum.OPEN, "2016-01-01", "2017-01-01", "FSE/1COV_X");

    Menus menus = new Menus();
    menus.startMenu();
  }
}
