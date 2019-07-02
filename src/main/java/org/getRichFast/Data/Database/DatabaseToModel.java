package org.getRichFast.Data.Database;

import org.getRichFast.UI.ConsoleUI.ConsoleOutputReceiver;

public class DatabaseToModel implements DatabaseToModelConnection {
  private ConsoleOutputReceiver consoleOutputReceiver;
  @Override
  public void outputValues(String[] values) {
    consoleOutputReceiver.valueConsoleOutput(values);
  }
}
