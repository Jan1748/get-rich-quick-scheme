package org.getRichFast.Data.Database;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.getRichFast.Model.Parsing.QuandlCodeParser;

public class QueryData {
  public static Double[] getQueriedData(String request, Connection connection) throws SQLException {
    Double[] data = new Double[4];
    ResultSet resultSet = null;
    String[] requests = request.split(";");

    for(int i = 0; i < 4; i++) {
      Statement statement = connection.createStatement();
      String requestNew = requests[i] + ";";
      resultSet = statement.executeQuery(requestNew);

      while (resultSet.next()) {
        try {
          data[i] = resultSet.getDouble("min");
        }catch (Exception e) {
          data[i] = resultSet.getDouble("max");
        }
      }
    }
    return data;
  }
}
