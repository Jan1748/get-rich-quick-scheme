package org.getRichFast;

import org.getRichFast.Model.Charts.CreateLineChartPNG;
import org.getRichFast.UI.ConsoleUI.Menus;

public class Main {
  public static void main(String[] args) {
    CreateLineChartPNG createLineChart = new CreateLineChartPNG();
    createLineChart.generateChartPNG();
    Menus menus = new Menus();
    menus.startMenu();
  }
}
