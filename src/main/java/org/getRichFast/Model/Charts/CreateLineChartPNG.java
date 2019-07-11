package org.getRichFast.Model.Charts;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.getRichFast.Data.Database.Enum.ChartEnum;
import org.getRichFast.Model.Entity.StockBuild;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class CreateLineChartPNG {

  public void generateChartPNG(ArrayList<StockBuild> input, ArrayList<StockBuild> input2) {
    StockBuild stockBuild = input.get(0);
    String title = stockBuild.getSymbol();
    if (input2 != null){
      title += " and " + input2.get(0).getSymbol();
    }
    TimeSeriesCollection dataset = createDataset(input, input2);
    JFreeChart chart = ChartFactory.createTimeSeriesChart(
        title,
        "Time", "Value",
        dataset);

    try {
      String[] names = stockBuild.getSymbol().split("/");
      String[] secondNames = input2.get(0).getSymbol().split("/");
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date testDate = input.get(0).getDate().getTime();
      String dateString1 = dateFormat.format(testDate);
      testDate = input.get(input.size() - 1).getDate().getTime();
      String dateString2 = dateFormat.format(testDate);
      String name = stockBuild.getSymbol();
      name = name.replace("/", "-");
      String name2 = input2.get(0).getSymbol();
      name2 =name2.replace("/", "-");
      StockFolderCreator.createStockFolder(ChartEnum.LineChart, names[0]);
      String path = "StockCharts/LineChart/" + names[0] + "/" + name + "_from_" + dateString2 + "_to_" + dateString1 + ".png";
      if (input2 != null){
        StockFolderCreator.createStockFolders(ChartEnum.LineChart, names[0], secondNames[1]);
        path = "StockCharts/LineChart/" + names[0] + "/"+ secondNames[1] + "/" + name + "_and_" + name2 +"_from_" + dateString2 + "_to_" + dateString1 + ".png";
      }
      System.out.println("Path " + path);
      ChartUtils.saveChartAsPNG(new File(path), chart, 1920, 1080);
      System.out.println("PNG Created");
    } catch (Exception e) {
      System.out.println("ERROR");
      e.printStackTrace();
    }
  }

  private TimeSeriesCollection createDataset(ArrayList<StockBuild> data, ArrayList<StockBuild> data2) {
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    if (data != null) {
      TimeSeries series1 = new TimeSeries(data.get(0).getSymbol() + " Open");
      TimeSeries series2 = new TimeSeries(data.get(0).getSymbol() + " High");
      TimeSeries series3 = new TimeSeries(data.get(0).getSymbol() + " Low");
      TimeSeries series4 = new TimeSeries(data.get(0).getSymbol() + " Close");

      for (int i = 0; i < data.size(); i++) {
        Date currentDate;
        currentDate = data.get(i).getDate().getTime();
        RegularTimePeriod regularTimePeriod = new Day(currentDate);
        series1.addOrUpdate(regularTimePeriod, data.get(i).getOpen());
        series2.addOrUpdate(regularTimePeriod, data.get(i).getHigh());
        series3.addOrUpdate(regularTimePeriod, data.get(i).getLow());
        series4.addOrUpdate(regularTimePeriod, data.get(i).getClose());
      }
      dataset.addSeries(series1);
      dataset.addSeries(series2);
      dataset.addSeries(series3);
      dataset.addSeries(series4);
    }
    if (data2 != null) {
      TimeSeriesCollection dataset2 = new TimeSeriesCollection();
      TimeSeries series5 = new TimeSeries(data2.get(0).getSymbol() +" Open");
      TimeSeries series6 = new TimeSeries(data2.get(0).getSymbol() +" High");
      TimeSeries series7 = new TimeSeries(data2.get(0).getSymbol() +" Low");
      TimeSeries series8 = new TimeSeries(data2.get(0).getSymbol() +" Close");
      for (int i = 0; i < data2.size(); i++) {
        Date currentDate;
        currentDate = data2.get(i).getDate().getTime();
        RegularTimePeriod regularTimePeriod = new Day(currentDate);
        series5.addOrUpdate(regularTimePeriod, data2.get(i).getOpen());
        series6.addOrUpdate(regularTimePeriod, data2.get(i).getHigh());
        series7.addOrUpdate(regularTimePeriod, data2.get(i).getLow());
        series8.addOrUpdate(regularTimePeriod, data2.get(i).getClose());

      }
      dataset.addSeries(series5);
      dataset.addSeries(series6);
      dataset.addSeries(series7);
      dataset.addSeries(series8);
    }
    return dataset;
  }
}
