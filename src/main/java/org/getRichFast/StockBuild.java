package org.getRichFast;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

//stock

public class StockBuild {

  private String symbol;
  private Calendar date;
  private BigDecimal open;
  private BigDecimal close;
  private BigDecimal low;
  private BigDecimal high;

  public StockBuild(String symbol, Calendar date, BigDecimal open, BigDecimal close, BigDecimal low,
      BigDecimal high) {
    this.symbol = symbol;
    this.date = date;
    this.open = open;
    this.close = close;
    this.low = low;
    this.high = high;
  }

  public String getSymbol() {
    return symbol;
  }

  public Calendar getDate() {
    return date;
  }

  public BigDecimal getOpen() {
    return open;
  }

  public BigDecimal getClose() {
    return close;
  }

  public BigDecimal getLow() {
    return low;
  }

  public BigDecimal getHigh() {
    return high;
  }

  @Override
  public String toString() {
    return "StockBuild{" +
        "symbol='" + symbol + '\'' +
        ", open=" + open +
        ", close=" + close +
        ", low=" + low +
        ", high=" + high +
        '}';
  }
}
