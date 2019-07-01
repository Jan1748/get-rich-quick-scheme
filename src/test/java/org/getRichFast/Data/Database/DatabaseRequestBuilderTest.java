package org.getRichFast.Data.Database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabaseRequestBuilderTest {
  @Test
  public void mytest(){
    DatabaseRequestBuilder databaseRequestBuilder = new DatabaseRequestBuilder();
    //databaseRequestBuilder.requestBuild(ValueEnum.MAX, ColumNameEnum.OPEN, DateEnum.BEFORE, "2015-01-01", null, "FSE/EON_X");
    Assertions.assertEquals("SELECT MAX (\"Open\") FROM stockbuild WHERE \"Date\" < '2015-01-01' AND \"Symbol\" = 'FSE/EON_X';", databaseRequestBuilder.requestBuild(ValueEnum.MAX, ColumNameEnum.OPEN, DateEnum.BEFORE, "2015-01-01", null, "FSE/EON_X"));
  }

}