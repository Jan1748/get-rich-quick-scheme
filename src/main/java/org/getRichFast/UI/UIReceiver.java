package org.getRichFast.UI;

import java.util.ArrayList;
import org.getRichFast.Model.Entity.PerformingStocks;

public interface UIReceiver {

  void outputStringToConsole(String output);

  void valueConsoleOutput(Double[] values);

  void outputPerformingFromStocks(ArrayList<PerformingStocks> sortedPerformingFromStocks);

}
