package org.getRichFast;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CsvParserTest {

  @ParameterizedTest
  @CsvFileSource(resources = "/CsvParserTestSource.csv")
  public void CsvParserTest(){
    CsvParser csvParser = new CsvParser();
    
  }
}
