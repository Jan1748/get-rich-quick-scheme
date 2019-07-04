package org.getRichFast.Model.Charts;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class CreateLineChart {
  public void LineChart_AWT() {

    //Prepare the data set
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue( 15 , "schools" , "1970" );
    dataset.addValue( 30 , "schools" , "1980" );
    dataset.addValue( 60 , "schools" ,  "1990" );
    dataset.addValue( 120 , "schools" , "2000" );
    dataset.addValue( 240 , "schools" , "2010" );
    dataset.addValue( 300 , "schools" , "2014" );

    //Create the chart
    JFreeChart chart = ChartFactory.createLineChart("Gaming", "Years","Number of Schools", dataset, PlotOrientation.VERTICAL,true,true,false);

    //Save chart as PNG
    try {
      ChartUtils.saveChartAsPNG(new File("soft3d.png"), chart, 400, 300);
    } catch (IOException e) {
      System.out.println("ERROR");
    }
  }
}
