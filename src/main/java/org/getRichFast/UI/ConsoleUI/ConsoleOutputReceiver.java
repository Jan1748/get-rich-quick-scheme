package org.getRichFast.UI.ConsoleUI;

import org.getRichFast.UI.UIReceiver;

public class ConsoleOutputReceiver implements UIReceiver {

  @Override
  public void outputStringToConsole(String output) {
    System.out.println(output);
  }

  @Override
  public void valueConsoleOutput(String[] values) {
    System.out.println("Gaming");
    String[] names = new String[]{"Open", "High", "Low", "Close"};
    for (int i = 0; i < 4; i++){
      System.out.println(names[i] + ": " + values[i]);
    }
  }
}
