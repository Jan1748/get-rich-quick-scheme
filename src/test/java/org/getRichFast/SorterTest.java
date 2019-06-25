package org.getRichFast;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class SorterTest {
  @Test
  public void sorterTest(){
    Sorter sorter = new Sorter();
    String choice = "low";
    BigDecimal input = new BigDecimal(4);
    BigDecimal currentExtremum = new BigDecimal(6);

    Assertions.assertTrue(sorter.compare(choice, input, currentExtremum));

    choice = "high";
    input = new BigDecimal(8);
    currentExtremum = new BigDecimal(6);


    Assertions.assertTrue(sorter.compare(choice, input, currentExtremum));
  }

}
