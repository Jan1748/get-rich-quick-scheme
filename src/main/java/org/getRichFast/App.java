package org.getRichFast;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    String userName = System.getProperty("user.name");
    DataToDatabase data = new DataToDatabase();
    data.getAndParseData();
  }
}
