package org.getRichFast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class ParsingCSVToStringArrayTest {
  @Test
  public void testParsing(){
    ParsingCSVToStringArray parsingCSVToStringArray = new ParsingCSVToStringArray();
    String[] expactet = new String[]{"Gaming", "Games"};
    //assertEquals(expactet, parsingCSVToStringArray.ParsingStringLine("Gaming, Games", 1));
  }
}
