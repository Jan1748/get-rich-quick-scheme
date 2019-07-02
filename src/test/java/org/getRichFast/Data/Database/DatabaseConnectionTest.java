package org.getRichFast.Data.Database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionTest {
  @Test
  public void serchForValueTest(){
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Assertions.assertEquals("Gaming", databaseConnection.getValue(ValueEnum.MAX, DateEnum.BEFORE, "2013-05-05", null, null));
  }

}
