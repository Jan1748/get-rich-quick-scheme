package org.getRichFast.Model.Charts;

import java.io.File;
import java.io.IOException;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;


public class CreateLineChartPNG {
  private DefaultCategoryDataset dataset;
  public void generateChartPNG(JDBCCategoryDataset jdbcCategoryDataset, String symbol) {
    dataset = jdbcCategoryDataset;

    //Create the chart
    JFreeChart chart = ChartFactory.createLineChart(symbol, "Years","Number of Schools", dataset,PlotOrientation.VERTICAL,true,true,false);

    //Save chart as PNG
    try {
      ChartUtils.saveChartAsPNG(new File("LineChart.png"), chart, 800, 600);
      System.out.println("PNG Created");
    } catch (IOException e) {
      System.out.println("ERROR");
    }
  }
}
