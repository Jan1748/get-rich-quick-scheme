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
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.DefaultHighLowDataset;

public class CreateCandleStickChartPNG {

  public void candlestick(ArrayList<StockBuild> input) {

    CandlestickRenderer renderer = new CandlestickRenderer();
    DefaultHighLowDataset dataset = getData(input);
    JFreeChart jFreeChart = createChart(dataset);

    try {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date testDate = input.get(0).getDate().getTime();
      String dateString1 = dateFormat.format(testDate);
      testDate = input.get(input.size()).getDate().getTime();
      String dateString2 = dateFormat.format(testDate);
      String name = input.get(0).getSymbol();
      name = name.replace("/", "-");
      String path = "ChartsPNG's/" + name + "_from_" + dateString2 + "_to_" + dateString1 + ".png";

      System.out.println("Path " + path);
      ChartUtils.saveChartAsPNG(new File(path), jFreeChart, 1920, 1080);
      System.out.println("PNG Created");
    } catch (Exception e) {
      System.out.println("ERROR");
      e.printStackTrace();
    }
  }

  protected DefaultHighLowDataset getData(ArrayList<StockBuild> stockBuild) {
    Date[] date = new Date[stockBuild.size()];
    double[] open = new double[stockBuild.size()];
    double[] high = new double[stockBuild.size()];
    double[] low = new double[stockBuild.size()];
    double[] close = new double[stockBuild.size()];
    double[] volume = new double[stockBuild.size()];

    for (int x = 0; x < stockBuild.size(); x++) {
      date[x] = stockBuild.get(x).getDate().getTime();
      if (stockBuild.get(x).getOpen() != null) {
        open[x] = stockBuild.get(x).getOpen().doubleValue();
      } else {
        open[x] = open[x - 1];
      }
      if (stockBuild.get(x).getHigh() != null) {
        high[x] = stockBuild.get(x).getHigh().doubleValue();
      } else {
        high[x] = high[x - 1];
      }
      if (stockBuild.get(x).getLow() != null) {
        low[x] = stockBuild.get(x).getLow().doubleValue();
      } else {
        low[x] = low[x - 1];
      }
      if (stockBuild.get(x).getClose() != null) {
        close[x] = stockBuild.get(x).getClose().doubleValue();
      } else {
        close[x] = close[x - 1];
      }
      volume[x] = 2d;
    }

    DefaultHighLowDataset item = new DefaultHighLowDataset("", date, open, high, low, close, volume);
    return item;
  }

  private JFreeChart createChart(DefaultHighLowDataset dataset) {
    JFreeChart jFreeChart = ChartFactory.createCandlestickChart("Test", "Time", "Value", dataset, true);
    return jFreeChart;
  }
}
