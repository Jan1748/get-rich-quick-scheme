package org.getRichFast;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    String userName = System.getProperty("user.name");
    GetQuandlData getQuandlData = new GetQuandlData("4nAVrexhFHXrX1TuYNsF");
    getQuandlData.downloadDataFromQuandl("FSE/WAC_X");
  }
}
