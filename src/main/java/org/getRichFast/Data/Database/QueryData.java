package org.getRichFast.Data.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryData {

  public static Double[] getQueriedData(String request, Connection connection) throws SQLException {
    Double[] data = new Double[4];
    ResultSet resultSet = null;
    String[] requests = request.split(";");
    for (int i = 0; i < 7; i += 2) {
      Statement statement = connection.createStatement();
      String requestNew = requests[i/2] + ";";
      try {
        resultSet = statement.executeQuery(requestNew);

        while (resultSet.next()) {
          try {
            data[i / 2] = resultSet.getDouble("min");
          } catch (Exception e) {
            data[i / 2] = resultSet.getDouble("max");
          }
        }
      } catch (Exception e) {
      }
    }
    return data;
  }
}
