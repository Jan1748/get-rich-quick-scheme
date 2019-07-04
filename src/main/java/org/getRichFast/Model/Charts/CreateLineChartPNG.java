package org.getRichFast.Model.Charts;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.getRichFast.Model.Entity.StockBuild;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class CreateLineChartPNG {
  private ArrayList<StockBuild> data;
  public void generateChartPNG(ArrayList<StockBuild> input) {

    data = input;
    System.out.println("Data length " + data.size());
    StockBuild stockBuild = data.get(0);
    DefaultCategoryDataset dataset = createDataset(stockBuild.getSymbol());
    System.out.println(dataset.getColumnKeys());
    JFreeChart chart = ChartFactory.createLineChart(
        stockBuild.getSymbol(),
        "Years","Number of Schools",
        dataset,
        PlotOrientation.VERTICAL,
        true,true,false);


    try {
      ChartUtils.saveChartAsPNG(new File("LineChart.png"), chart, 800, 600);
      System.out.println("PNG Created");
    } catch (Exception e) {
      System.out.println("ERROR");
    }
  }
  private DefaultCategoryDataset createDataset(String symbol) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    //for(int i = 0; i < data.size(); i++) {
      StockBuild stockBuild = data.get(0);
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date dateTest = stockBuild.getDate().getTime();
      String dateString = dateFormat.format(dateTest);
      dataset.setValue(1, "Open", dateString);
    stockBuild = data.get(1);
      dataset.addValue(2, "Open", dateString);
    stockBuild = data.get(2);
      dataset.addValue(3, "Open", dateString);

    //}
    return dataset;
  }
}
