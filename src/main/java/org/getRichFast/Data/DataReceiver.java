package org.getRichFast.Data;

import java.util.ArrayList;
import org.getRichFast.Data.Database.ColumNameEnum;
import org.getRichFast.Data.Database.DateEnum;
import org.getRichFast.Data.Entity.StockBuild;

public interface DataReceiver {

  /**
   * Must bei initialized before starting functions
   */
  void initialize();

  void insertDataToDatabase(ArrayList<StockBuild> stocks);

  void getMaximalDateValue(ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date1);
  void getMaximalDateSymbolValue(ColumNameEnum columNameEnum, DateEnum dateEnum, String date,String date1, String symbol);
  void getMaximalSymbolValue(ColumNameEnum columNameEnum, String symbol);
  void getMaximalValue(ColumNameEnum columNameEnum);

  void getMinimalDateValue(ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date1);
  void getMinimalDateSymbolValue(ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date1, String symbol);
  void getMinimalSymbolValue(ColumNameEnum columNameEnum,String symbol);
  void getMinimalValue(ColumNameEnum columNameEnum);

}
