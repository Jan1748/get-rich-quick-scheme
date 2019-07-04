package org.getRichFast.Data;

import java.util.ArrayList;
import org.getRichFast.Data.Database.Enum.ColumnNameEnum;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Data.Database.Enum.SymbolEnum;
import org.getRichFast.Data.Database.Enum.ValueEnum;
import org.getRichFast.Model.Entity.StockBuild;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public interface DataReceiver {

  /**
   * Must bei initialized before starting functions
   */
  void initialize();

  void insertDataToDatabase(ArrayList<StockBuild> stocks);

  void search(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum, ColumnNameEnum columnNameEnum, String date, String date2, String symbol);

  JDBCCategoryDataset getQueriedDataset(ValueEnum valueEnum, SymbolEnum symbolEnum, DateEnum dateEnum,ColumnNameEnum columnNameEnum, String date, String date2, String symbol);
}
