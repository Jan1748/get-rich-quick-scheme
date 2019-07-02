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
  public static String[] getQueriedData(String request, Connection connection) throws SQLException {
    String[] datas = new String[4];
    request = "SELECT MAX (\"Open\") FROM stockbuild WHERE \"Date\" < '2013-05-05' ;SELECT MAX (\"High\") FROM stockbuild WHERE \"Date\" < '2013-05-05' ;SELECT MAX (\"Low\") FROM stockbuild WHERE \"Date\" < '2013-05-05' ;SELECT MAX (\"Close\") FROM stockbuild WHERE \"Date\" < '2013-05-05' ;";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(request);
    int i = 0;
    String[] values = new String[]{"Open", "High", "Low", "Close"};
    while (resultSet.next()) {
      datas[i] = resultSet.getString(values[i]);
      i++;
    }
    return datas;
  }
}
