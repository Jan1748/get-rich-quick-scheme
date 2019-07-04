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
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class CreateLineChartPNG {

  public void generateChartPNG(ArrayList<StockBuild> input) {

    StockBuild stockBuild = input.get(0);
    TimeSeriesCollection dataset = createDataset(input);
    JFreeChart chart = ChartFactory.createTimeSeriesChart(
        stockBuild.getSymbol(),
        "Time", "Value",
        dataset);

    try {
      ChartUtils.saveChartAsPNG(new File("LineChart.png"), chart, 800, 600);
      System.out.println("PNG Created");
    } catch (Exception e) {
      System.out.println("ERROR");
    }
  }

  private TimeSeriesCollection createDataset(ArrayList<StockBuild> data) {
    TimeSeries series1 = new TimeSeries("Data");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < data.size();i++) {
      StockBuild stockBuild = data.get(i);
      Date currentDate;
      currentDate = data.get(i).getDate().getTime();
      RegularTimePeriod regularTimePeriod = new Day(currentDate);
      series1.addOrUpdate(regularTimePeriod, data.get(i).getOpen());
    }
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(series1);
    return dataset;
  }
}
