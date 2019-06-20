package org.getRichFast;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseStringsToDatatypes {
  public ParseStringsToDatatypes(String type, String input) throws ParseException {
    switch (type){
      case "Date":parseToDate(input);
      default: parseToBigDecimal(input);
    }

  }
  private Date parseToDate(String dateString) throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.parse(dateString);
  }
  private BigDecimal parseToBigDecimal(String number){
    return new BigDecimal(number);
  }

}
