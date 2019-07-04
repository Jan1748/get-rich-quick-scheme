package org.getRichFast.Model.Charts;

import java.io.File;
import java.io.IOException;
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

    JFreeChart chart = ChartFactory.createLineChart(symbol, "Time","Value", dataset,PlotOrientation.VERTICAL,true,true,false);

    try {
      ChartUtils.saveChartAsPNG(new File("LineChart.png"), chart, 800, 600);
      System.out.println("PNG Created");
    } catch (IOException e) {
      System.out.println("ERROR");
    }
  }
}
