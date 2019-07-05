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
      File folder = new File("ChartsPNG's");
      folder.mkdir();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date testDate = input.get(0).getDate().getTime();
      String dateString1 = dateFormat.format(testDate);
      testDate = input.get(input.size()-1).getDate().getTime();
      String dateString2 = dateFormat.format(testDate);
      String name = stockBuild.getSymbol();
      name = name.replace("/", "-");
      String path = "ChartsPNG's/" + name + "_from_" +dateString2 + "_to_" + dateString1 + ".png";

      System.out.println("Path " + path);
      ChartUtils.saveChartAsPNG(new File(path), chart, 1920, 1080);
      System.out.println("PNG Created");
    } catch (Exception e) {
      System.out.println("ERROR");
      e.printStackTrace();
    }
  }

  private TimeSeriesCollection createDataset(ArrayList<StockBuild> data) {
    TimeSeries series1 = new TimeSeries("Open");
    TimeSeries series2 = new TimeSeries("High");
    TimeSeries series3 = new TimeSeries("Low");
    TimeSeries series4 = new TimeSeries("Close");

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < data.size();i++) {
      StockBuild stockBuild = data.get(i);
      Date currentDate;
      currentDate = data.get(i).getDate().getTime();
      RegularTimePeriod regularTimePeriod = new Day(currentDate);
      series1.addOrUpdate(regularTimePeriod, data.get(i).getOpen());
      series2.addOrUpdate(regularTimePeriod, data.get(i).getHigh());
      series3.addOrUpdate(regularTimePeriod, data.get(i).getLow());
      series4.addOrUpdate(regularTimePeriod, data.get(i).getClose());

    }
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(series1);
    dataset.addSeries(series2);
    dataset.addSeries(series3);
    dataset.addSeries(series4);

    return dataset;
  }
}
