package org.getRichFast.Properties;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PropertiesCreate {

  public static void createProperties() {
    OutputStream output = null;
    try {
      output = new FileOutputStream("src/main/resources/config.properties");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    java.util.Properties prop = new java.util.Properties();

    prop.setProperty("db.user", "User");
    prop.setProperty("db.password", "Password");
    prop.setProperty("db.name", "DatabaseName");
    prop.setProperty("api.Key", "ApiKey");

    try {
      prop.store(output, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(prop);
  }
}
