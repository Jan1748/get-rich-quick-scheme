package org.getRichFast.Data;

import java.util.ArrayList;
import org.getRichFast.Data.Database.ColumNameEnum;
import org.getRichFast.Data.Database.DateEnum;
import org.getRichFast.Model.Entity.StockBuild;

public interface DataReceiver {

  /**
   * Must bei initialized before starting functions
   */
  void initialize();

  void insertDataToDatabase(ArrayList<StockBuild> stocks);
  void searchForHighestValue(String sqlCode);
  void searchForlowestValue(String sqlCode);

}
