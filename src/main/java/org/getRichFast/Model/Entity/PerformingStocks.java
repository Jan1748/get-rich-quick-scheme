package org.getRichFast.Model.Entity;

import java.math.BigDecimal;

public class PerformingStocks {

  private String stockCode;
  private double performancePercent;
  private double performanceAbsolute;

  public PerformingStocks(String stockCode, double performancePercent, double performanceAbsolute){
    this.stockCode = stockCode;
    this.performancePercent = performancePercent;
    this.performanceAbsolute = performanceAbsolute;
  }

  public String getStockCode() {
    return stockCode;
  }

  public double getPerformancePercent() {
    return performancePercent;
  }

  public double getPerformanceAbsolute() {
    return performanceAbsolute;
  }

  @Override
  public String toString() {
    return "PerformingStocks{" +
        "stockCode='" + stockCode + '\'' +
        ", performancePercent=" + performancePercent +
        ", performanceAbsolute=" + performanceAbsolute +
        '}';
  }
}
