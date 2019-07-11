package org.getRichFast.Model;

import java.util.ArrayList;
import org.getRichFast.Data.Database.Enum.DateEnum;
import org.getRichFast.Model.Entity.PerformingStocks;

public interface ConnectToUI {

  void outputValues(Double[] values);

  void getPeformanceStocks(ArrayList<PerformingStocks> performingStocks);
}
