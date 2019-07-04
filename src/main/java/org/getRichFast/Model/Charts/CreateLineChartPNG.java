package org.getRichFast.Model.Charts;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.getRichFast.Model.Entity.StockBuild;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;


public class CreateLineChartPNG {
  private DefaultCategoryDataset dataset;
  public void generateChartPNG(ArrayList<StockBuild> data) {
    StockBuild stockBuild = data.get(0);
    for(int i = 0; i < data.size(); i++){
      stockBuild = data.get(i);
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date dateTest = stockBuild.getDate().getTime();
      String dateString = dateFormat.format(dateTest);
      System.out.println("Date " + stockBuild.getDate());
      dataset.addValue(stockBuild.getOpen(), "OPEN", dateString);
    }
    JFreeChart chart = ChartFactory.createLineChart( stockBuild.getSymbol(),"Time","Value", dataset,PlotOrientation.VERTICAL,false,false,false);

    try {
      ChartUtils.saveChartAsPNG(new File("LineChart.png"), chart, 800, 600);
      System.out.println("PNG Created");
    } catch (Exception e) {
      System.out.println("ERROR");
    }
  }
}
