package org.getRichFast;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ParsingCSVToStringArrayTest {

  //@ParameterizedTest(name = {""})
  @CsvFileSource(resources = "C:/Users/lteschner/Downloads/Test1.csv")
  public void testParsing(){
    ParsingCSVToStringArray parsingCSVToStringArray = new ParsingCSVToStringArray();
    
  }
}
