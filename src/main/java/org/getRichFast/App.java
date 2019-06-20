package org.getRichFast;

//FIXME: remove this crap
/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    //FIXME: remove this crap
    String userName = System.getProperty("user.name");
    DataToDatabase data = new DataToDatabase();
    data.getAndParseData();
  }
}
