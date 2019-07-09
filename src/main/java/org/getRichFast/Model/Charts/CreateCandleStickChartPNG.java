package org.getRichFast.Model.Charts;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.getRichFast.Data.Database.Enum.ChartEnum;
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
    double lowestLow = getLowestLow(dataset);
    double highestHigh = getHighestHigh(dataset);
    JFreeChart jFreeChart = createChart(dataset);
    jFreeChart.getXYPlot().getRangeAxis().setRange(lowestLow*0.95, highestHigh*1.05);


    try {
      String[] names = input.get(0).getSymbol().split("/");
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date testDate = input.get(0).getDate().getTime();
      String dateString1 = dateFormat.format(testDate);
      testDate = input.get(input.size()-1).getDate().getTime();
      String dateString2 = dateFormat.format(testDate);
      String name = input.get(0).getSymbol();
      name = name.replace("/", "-");
      StockFolderCreator.createStockFolder(ChartEnum.CandleStickChart, names[0]);
      String path = "StockCharts/CandleStickChart/" + names[0]+ "/" + name + "_from_" + dateString2 + "_to_" + dateString1 + ".png";

      System.out.println("Path " + path);
      ChartUtils.saveChartAsPNG(new File(path), jFreeChart, 1920, 1080);
      System.out.println("PNG Created");
    } catch (Exception e) {
      System.out.println("ERROR");
      e.printStackTrace();
    }
  }

  protected DefaultHighLowDataset getData(ArrayList<StockBuild> stockBuild) {
    int scale = 21;
    int blend = stockBuild.size() % scale;

    if (stockBuild.size() - scale <= 0){
      blend = 0;
      scale = 1;
    }

    Date[] date = new Date[((stockBuild.size() - blend) / scale)];
    double[] open = new double[((stockBuild.size() - blend) / scale)];
    double[] high = new double[((stockBuild.size() - blend) / scale)];
    double[] low = new double[((stockBuild.size() - blend) / scale)];
    double[] close = new double[((stockBuild.size() - blend) / scale)];
    double[] volume = new double[((stockBuild.size() - blend) / scale)];

    int y = 0;

    System.out.println(stockBuild.size() - blend);
    for (int x = 0; x < stockBuild.size() - blend; x += scale) {
      date[y] = stockBuild.get(x).getDate().getTime();
      if (stockBuild.get(x).getOpen() != null) {
        open[y] = stockBuild.get(x).getOpen().doubleValue();
      } else {
        if (x != 0 && stockBuild.get(x).getOpen() != null){
          open[y] = open[y - 1];
        }
        else if(stockBuild.get(x).getOpen() != null){
          open[y] = stockBuild.get(x).getLow().doubleValue();
        }
      }
      if (stockBuild.get(x).getHigh() != null) {
        high[y] = stockBuild.get(x).getHigh().doubleValue();
      } else {
        if (x != 0 && stockBuild.get(x).getHigh() != null) {
          high[y] = stockBuild.get(x).getClose().doubleValue();
        }
      }
      if (stockBuild.get(x).getLow() != null) {
        low[y] = stockBuild.get(x).getLow().doubleValue();
      } else {
        if (x != 0 && stockBuild.get(x).getLow() != null){
          low[y] = stockBuild.get(x).getOpen().doubleValue();
        }
      }
      if (stockBuild.get(x).getClose() != null) {
        close[y] = stockBuild.get(x).getClose().doubleValue();
      } else {
        if (x != 0 && stockBuild.get(x).getClose() != null){
          close[y] = stockBuild.get(x).getHigh().doubleValue();
        }
      }
      volume[y] = 20d;
      y += 1;
    }
    DefaultHighLowDataset item = new DefaultHighLowDataset("test", date, open, high, low, close, volume);
    return item;
  }

  private JFreeChart createChart(DefaultHighLowDataset dataset) {
    JFreeChart jFreeChart = ChartFactory.createCandlestickChart("Test", "Time", "Value", dataset, true);
    return jFreeChart;
  }
  private double getLowestLow(DefaultHighLowDataset dataset){
    double lowest;
    lowest = dataset.getLowValue(0,0);
    for(int i=1;i<dataset.getItemCount(0);i++){
      if(dataset.getLowValue(0,i) < lowest){
        lowest = dataset.getLowValue(0,i);
      }
    }

    return lowest;
  }


  private double getHighestHigh(DefaultHighLowDataset dataset){
    double highest;
    highest = dataset.getHighValue(0,0);
    for(int i=1;i<dataset.getItemCount(0);i++){
      if(dataset.getLowValue(0,i) > highest){
        highest = dataset.getHighValue(0,i);
      }
    }

    return highest;
  }
}
