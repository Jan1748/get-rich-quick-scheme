package org.getRichFast.Entity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

  public StockBuild(String symbol, Calendar date, BigDecimal open, BigDecimal high, BigDecimal low,
      BigDecimal close) {
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
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date testDate = date.getTime();
    String dateString = dateFormat.format(testDate);
    return "StockBuild{" +
        "symbol='" + symbol + '\'' +
        " date=" + dateString +
        ", open=" + open +
        ", close=" + close +
        ", low=" + low +
        ", high=" + high +
        '}';
  }
}
