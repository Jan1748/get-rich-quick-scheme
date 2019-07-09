package org.getRichFast.Model.Charts;

import java.io.File;
import org.getRichFast.Data.Database.Enum.ChartEnum;

public class StockFolderCreator {
  public static String createStockFolder(ChartEnum chartEnum, String stock){
    String path = "StockCharts/" + chartEnum.toString()  + "/" + stock;
    File file = new File(path);
    file.mkdirs();
  return path;
  }
}
