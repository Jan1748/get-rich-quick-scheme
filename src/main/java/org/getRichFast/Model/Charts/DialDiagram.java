package org.getRichFast.Model.Charts;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.chart.ui.GradientPaintTransformType;
import org.jfree.chart.ui.StandardGradientPaintTransformer;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;

public class DialDiagram {
  public void createStandardDialChart(String s, String s1, ValueDataset valuedataset, double d, double d1, double d2, int i) {

    DialPlot dialplot = new DialPlot();
    dialplot.setDataset(valuedataset);
    dialplot.setDialFrame(new StandardDialFrame());
    dialplot.setBackground(new DialBackground());
    DialTextAnnotation dialtextannotation = new DialTextAnnotation(s1);
    dialtextannotation.setFont(new Font("Dialog", 1, 14));
    dialtextannotation.setRadius(0.69999999999999996D);
    dialplot.addLayer(dialtextannotation);
    DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
    dialplot.addLayer(dialvalueindicator);
    StandardDialScale standarddialscale = new StandardDialScale(d, d1, -120D, -300D, 10D, 4);
    standarddialscale.setMajorTickIncrement(d2);
    standarddialscale.setMinorTickCount(i);
    standarddialscale.setTickRadius(0.88D);
    standarddialscale.setTickLabelOffset(0.14999999999999999D);
    standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
    dialplot.addScale(0, standarddialscale);
    dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
    DialCap dialcap = new DialCap();
    dialplot.setCap(dialcap);
    JFreeChart chart = new JFreeChart(s, dialplot);
    try {
      ChartUtils.saveChartAsPNG(new File("dial.png"), chart, 800, 600);
      System.out.println("PNG Created");
    } catch (Exception e) {
      System.out.println("ERROR");
    }
  }

}
