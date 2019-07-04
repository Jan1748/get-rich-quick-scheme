package org.getRichFast.Model.Charts;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;


public class CreateLineChart {
  private DefaultCategoryDataset dataset;
  public void LineChart_AWT() {
    dataset = new JDBCCategoryDataset();




    //Create the chart
    JFreeChart chart = ChartFactory.createLineChart("Gaming", "Years","Number of Schools", dataset,PlotOrientation.VERTICAL,true,true,false);

    //Save chart as PNG
    try {
      ChartUtilities.saveChartAsPNG(new File("LineChart.png"), chart, 800, 600);
      System.out.println("PNG Created");
    } catch (IOException e) {
      System.out.println("ERROR");
    }
  }
  public void createDataset(){
    dataset.addValue( 15 , "schools" , "1970" );
    dataset.addValue( 30 , "schools" , "1980" );
    dataset.addValue( null , "schools" ,  "1990" );
    dataset.addValue( 120 , "schools" , "2000" );
    dataset.addValue( 240 , "schools" , "2010" );
    dataset.addValue( 300 , "schools" , "2014" );
  }
}
