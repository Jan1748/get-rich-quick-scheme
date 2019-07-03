package org.getRichFast;

import org.getRichFast.Model.Charts.CreateLineChart;
import org.getRichFast.UI.ConsoleUI.Menus;

public class Main {
  public static void main(String[] args) {
    CreateLineChart createLineChart = new CreateLineChart();
    createLineChart.LineChart_AWT();
    Menus menus = new Menus();
    menus.startMenu();
  }
}
