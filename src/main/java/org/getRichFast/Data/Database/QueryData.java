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
    Double[] datas = new Double[4];
    ResultSet resultSet = null;
    //request = "SELECT MAX (\"Open\") FROM stockbuild WHERE \"Date\" < '2013-05-05' ;SELECT MAX (\"High\") FROM stockbuild WHERE \"Date\" < '2013-05-05' ;SELECT MAX (\"Low\") FROM stockbuild WHERE \"Date\" < '2013-05-05' ;SELECT MAX (\"Close\") FROM stockbuild WHERE \"Date\" < '2013-05-05' ;";
    String[] requests = request.split(";");
    for(int i = 0; i < 4; i++) {
      Statement statement = connection.createStatement();
      String requestNew = requests[i] + ";";
      System.out.println(requestNew);
      resultSet = statement.executeQuery(requestNew);
      while (resultSet.next()) {
        try {
          datas[i] = resultSet.getDouble("min");
        }catch (Exception e) {
          datas[i] = resultSet.getDouble("max");
        }
      }
    }
    return datas;
  }
}
