package org.getRichFast.Model.InterfaceConnection;

import org.getRichFast.Model.ConnectToUI;
import org.getRichFast.UI.ConsoleUI.ConsoleOutputReceiver;
import org.getRichFast.UI.UIReceiver;

public class ModelToUIConnection implements ConnectToUI {

  private UIReceiver uiReceiver = new ConsoleOutputReceiver();

  @Override
  public void outputValues(Double[] values) {
    uiReceiver.valueConsoleOutput(values);
  }
}
