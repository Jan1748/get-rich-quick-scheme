package org.getRichFast.Model.Charts;

import java.io.File;
import java.math.BigDecimal;
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
    if (dataset == null){
      System.out.println("Leave");
      return;
    }
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
    ArrayList<Date> dateList = new ArrayList<>();
    ArrayList<BigDecimal> openList = new ArrayList<>();
    ArrayList<BigDecimal> highList = new ArrayList<>();
    ArrayList<BigDecimal> lowList = new ArrayList<>();
    ArrayList<BigDecimal> closeList = new ArrayList<>();

    System.out.println(stockBuild.size() - blend);
    int a = 0;
    for (int x = 0; x < stockBuild.size() - blend; x += scale) {
      dateList.add(stockBuild.get(x).getDate().getTime());
      do {
        if (stockBuild.get(x).getOpen() != null && stockBuild.get(x).getClose() != null) {
          openList.add(stockBuild.get(x).getOpen());
          highList.add(stockBuild.get(x).getHigh());
          lowList.add(stockBuild.get(x).getLow());
          closeList.add(stockBuild.get(x).getClose());
          a++;
        }
        for (int i = scale; i > 0; i--){
          if (stockBuild.get(x-i).getOpen()!= null){
            stockBuild.get(x).setOpen(stockBuild.get(x-i).getOpen());
          }if (stockBuild.get(x-i).getClose()!= null){
            stockBuild.get(x).setClose(stockBuild.get(x-i).getClose());
          }
        }
      }if (a > 0);
    }
    double[] open = new double[openList.size()];
    double[] high = new double[highList.size()];
    double[] low = new double[lowList.size()];
    double[] close = new double[closeList.size()];
    double[] volume = new double[];
    if (open[0] != -1 && high[0] != -1 && low[0] != -1 && close[0] != -1) {
      DefaultHighLowDataset item = new DefaultHighLowDataset("test", date, open, high, low, close, volume);
      return item;
    }
    return null;
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
