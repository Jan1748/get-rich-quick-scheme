package org.getRichFast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateTest {

  @ParameterizedTest
  @ValueSource(strings = {"24-06-2019", "2019-06-24", "2019-24-06", "06-24-2019"})
  public void dateFormatTest(String dateString) throws ParseException {
    LineParser lineParser = new LineParser();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Calendar calendarActual = lineParser.parseToCalendar(dateString);
    Date date = simpleDateFormat.parse(dateString);
    Calendar calendarExcepted = Calendar.getInstance();
    calendarExcepted.setTime(date);

    Assertions.assertEquals(calendarExcepted, calendarActual);
  }
}
