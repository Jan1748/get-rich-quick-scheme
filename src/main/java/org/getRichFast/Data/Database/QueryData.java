package org.getRichFast.Data.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryData {
  public static String[] getQueriedData(String request, Connection connection) throws SQLException {
    String[] datas = new String[]{"","","",""};
    String[] requests = request.split(";");
    String requestNew = "";
    int counter = 0;
    for(int i = 0; i < 4; i++) {
      Statement statement = connection.createStatement();
      requestNew = requests[counter] + ";";
      if(requestNew.equals(";")){
          counter++;
        System.out.println("Continue " + counter);
          continue;
      }
      System.out.println("Request: |" + requestNew + "|");
      ResultSet resultSet = statement.executeQuery(requestNew);
      while (resultSet.next()) {
        try {
          datas[i] = resultSet.getString("Min");
        }catch (Exception e) {
          datas[i] = resultSet.getString("Max");
        }
      }
    }
    return datas;
  }
}
