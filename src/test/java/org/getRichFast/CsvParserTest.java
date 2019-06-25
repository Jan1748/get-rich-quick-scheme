package org.getRichFast;

import java.util.ArrayList;
import org.getRichFast.Parsing.CsvParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsvParserTest {

  @Test
  public void CsvParserTest() {
    CsvParser csvParser = new CsvParser();
    ArrayList<String> lines = new ArrayList<>();
    lines.add("1,2,3,4,5,67");
    lines.add("5,8,7,548");

    ArrayList<String[]> parsedLinesActual = csvParser.getDataArrayList(lines);

    ArrayList<String[]> parsedLinesExcepted = new ArrayList<>();
    parsedLinesExcepted.add(new String[]{"1", "2", "3", "4", "5", "67"});
    parsedLinesExcepted.add(new String[]{"5", "8", "7", "548"});

    Assertions.assertArrayEquals(parsedLinesExcepted.get(0), parsedLinesActual.get(0));
    Assertions.assertArrayEquals(parsedLinesExcepted.get(1), parsedLinesActual.get(1));
  }
}
