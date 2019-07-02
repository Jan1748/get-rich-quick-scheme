package org.getRichFast.Data.Database;

import org.getRichFast.Model.ConnectToUI;
import org.getRichFast.Model.InterfaceConnection.ModelToUIConnection;
import org.getRichFast.UI.ConsoleUI.ConsoleOutputReceiver;

public class DatabaseToModel implements DatabaseToModelConnection {
  private ConnectToUI connectToUI = new ModelToUIConnection();
  @Override
  public void outputValues(Double[] values) {
    connectToUI.outputValues(values);
  }
}
