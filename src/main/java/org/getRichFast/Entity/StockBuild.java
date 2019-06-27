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

  public StockBuild(String symbol, Calendar date) {
    this.symbol = symbol;
    this.date = date;
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

  public void setOpen(BigDecimal open) {
    this.open = open;
  }

  public void setClose(BigDecimal close) {
    this.close = close;
  }

  public void setLow(BigDecimal low) {
    this.low = low;
  }

  public void setHigh(BigDecimal high) {
    this.high = high;
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
  public String toCsvString() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date testDate = date.getTime();
    String dateString = dateFormat.format(testDate);
    return dateString + "," + symbol + "," + open + "," + high + "," + low + "," + close + "\n";
  }
}
