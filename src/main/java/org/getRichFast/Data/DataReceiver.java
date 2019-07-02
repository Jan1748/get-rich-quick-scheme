package org.getRichFast.Data;

import java.util.ArrayList;
import org.getRichFast.Data.Database.ColumNameEnum;
import org.getRichFast.Data.Database.DateEnum;
import org.getRichFast.Data.Database.ValueEnum;
import org.getRichFast.Data.Entity.StockBuild;

public interface DataReceiver {

  /**
   * Must bei initialized before starting functions
   */
  void initialize();

  void insertDataToDatabase(ArrayList<StockBuild> stocks);
  void searchForValue(ValueEnum valueEnum, ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date2, String symbol);

}
