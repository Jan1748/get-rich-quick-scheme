package org.getRichFast.Searching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.getRichFast.Database.DatabaseConnection;
import org.getRichFast.UI.InputFunctions;

public class ValueSearcher {

  //FIXME: maybe separate UI- code from Model- from Data-Code!!

  private DatabaseConnection databaseConnection = new DatabaseConnection();

  //TODO: Make the symbol as a search parameter (search for a date in a stock)

  public ResultSet[] searchForValue(String dateCondition) {
    String highLowChoice = InputFunctions.scan("What do you want to search for? 1: Lowest value 2: Highest value");
    String[] value = new String[]{"Open", "High", "Low", "Close"};

    switch (highLowChoice) {
      case "1":
        return getLowestValues(value, dateCondition);
      case "2":
        return getHighestValues(value, dateCondition);
    }
    return null;
  }

  private ResultSet[] getLowestValues(String[] value, String dateCondition) {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.connect();
    ResultSet[] resultSets = new ResultSet[4];
    PreparedStatement sqlStatement = null;
    try {
      for (int x = 0; x < 4; x++) {
        sqlStatement = connection.prepareStatement("SELECT MIN (\"?\") FROM stockbuild ?;");
        sqlStatement.setString(1, value[x]);
        sqlStatement.setString(2, dateCondition);
        resultSets[x] = sqlStatement.executeQuery();
      }
      return resultSets;
    } catch (SQLException e) {
      System.out.println("Error!");
    }
    return null;
  }

  private ResultSet[] getHighestValues(String[] value, String dateCondition) {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.connect();
    ResultSet[] resultSets = new ResultSet[4];
    PreparedStatement sqlStatement = null;
    try {
      for (int x = 0; x < 4; x++) {
        sqlStatement = connection.prepareStatement("SELECT MAX (\"?\") FROM stockbuild ?;");
        sqlStatement.setString(1, value[x]);
        sqlStatement.setString(2, dateCondition);
        resultSets[x] = sqlStatement.executeQuery();
      }
      return resultSets;
    } catch (SQLException e) {
      System.out.println("Error!");
    }
    return null;
  }
}