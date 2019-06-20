package org.getRichFast;

import java.math.BigDecimal;
import java.util.Date;

public class StokeBuild {
  private String name;
  private Date date;
  private BigDecimal open;
  private BigDecimal close;
  private BigDecimal low;
  private BigDecimal high;

  StokeBuild(String name, Date date, BigDecimal open, BigDecimal close, BigDecimal low, BigDecimal high){
    this.name = name;
    this.date = date;
    this.open = open;
    this.close = close;
    this.low = low;
    this.high = high;
  }

  public String getName() {
    return name;
  }

  public Date getDate() {
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
}
