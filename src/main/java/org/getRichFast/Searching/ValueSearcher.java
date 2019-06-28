package org.getRichFast.Searching;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.getRichFast.Database.DatabaseConnection;
import org.getRichFast.UI.InputFunctions;

public class ValueSearcher {

  //FIXME: maybe separate UI- code from Model- from Data-Code
  private DatabaseConnection databaseConnection = new DatabaseConnection();
  private Scanner scanner = new Scanner(System.in);

  public ResultSet searchForDate() {
    String sqlCode = null;
    String date;
    String date2;
    System.out.println("What type of date do you want to search for? 1: Exact date 2: Interval of dates 3: Everything before date 4: Everything after date");
    String choice = scanner.nextLine();

    switch (choice) {
      case "1":
        date = InputFunctions.getInputDateString();
        sqlCode = "WHERE \"Date\" = ' " + date + "'";
        searchForValue(sqlCode);
        break;
      case "2":
        date = InputFunctions.getInputDateString();
        date2 = InputFunctions.getInputDateString();
        sqlCode = "WHERE \"Date\" > '" + date + "' AND \"Date\" < '" + date2 + "'";
        searchForValue(sqlCode);
        break;
      case "3":
        date = InputFunctions.getInputDateString();
        sqlCode = "WHERE \"Date\" < '" + date + "'";
        searchForValue(sqlCode);
        break;
      case "4":
        date = InputFunctions.getInputDateString();
        sqlCode = "WHERE \"Date\" > '" + date + "'";
        searchForValue(sqlCode);
        break;
      default:
        System.out.println("Please enter a valid choice");
    }
    return null;

  }

  private ResultSet searchForValue(String dateCondition) {
    Statement statement;
    ResultSet result;
    String sqlCode = null;
    String highLowChoice = InputFunctions.scan("What do you want to search for? 1: Lowest value 2: Highest value 3: Output the data");
    String[] valueNames = new String[]{"Open", "High", "Low", "Close", "Lowest", "Highest"};

    try {
      statement = databaseConnection.connect().createStatement();

      switch (highLowChoice) {
        case "1":
          String choice = InputFunctions.scan("For which data do you want the lowest value? 1: " + valueNames[0] + " 2: " + valueNames[1] + " 3: " + valueNames[2] + " 4: " + valueNames[3]);
          switch (choice) {
            case "1":
              sqlCode = "SELECT MIN (\"Open\") FROM stockbuild " + dateCondition + ";";
              result = statement.executeQuery(sqlCode);
              while (result.next()) {
                System.out.println("Min open: " + result.getDouble("Min"));
              }
              break;
            case "2":
              sqlCode = "SELECT MIN (\"High\") FROM stockbuild " + dateCondition + ";";
              result = statement.executeQuery(sqlCode);
              while (result.next()) {
                System.out.println("Min high: " + result.getDouble("Min"));
              }
              break;
            case "3":
              sqlCode = "SELECT MIN (\"Low\") FROM stockbuild " + dateCondition + ";";
              result = statement.executeQuery(sqlCode);
              while (result.next()) {
                System.out.println("Min low: " + result.getDouble("Min"));
              }
              break;
            case "4":
              sqlCode = "SELECT MIN (\"Close\") FROM stockbuild " + dateCondition + ";";
              result = statement.executeQuery(sqlCode);
              while (result.next()) {
                System.out.println("Min close: " + result.getDouble("Min"));
              }
              break;
            default:
              System.out.println("Please enter a valid choice");
          }
          break;
        case "2":
          choice = InputFunctions.scan("For which data do you want the highest value? 1: Open 2: High 3: Low 4: Close");
          switch (choice) {
            case "1":
              sqlCode = "SELECT MAX (\"Open\") FROM stockbuild " + dateCondition + ";";
              result = statement.executeQuery(sqlCode);
              while (result.next()) {
                System.out.println("Max open: " + result.getDouble("Max"));
              }
              break;
            case "2":
              sqlCode = "SELECT MAX (\"High\") FROM stockbuild " + dateCondition + ";";
              result = statement.executeQuery(sqlCode);
              while (result.next()) {
                System.out.println("Max high: " + result.getDouble("Max"));
              }
              break;
            case "3":
              sqlCode = "SELECT MAX (\"Low\") FROM stockbuild " + dateCondition + ";";
              result = statement.executeQuery(sqlCode);
              while (result.next()) {
                System.out.println("Max low: " + result.getDouble("Max"));
              }
              break;
            case "4":
              sqlCode = "SELECT MAX (\"Close\") FROM stockbuild " + dateCondition + ";";
              result = statement.executeQuery(sqlCode);
              while (result.next()) {
                System.out.println("Max close: " + result.getDouble("Max"));
              }
              break;
            default:
              System.out.println("Please enter a valid choice");
          }
        case "3":
          sqlCode = "SELECT * FROM stockbuild " + dateCondition + ";";
          result = statement.executeQuery(sqlCode);
          System.out.printf("%-12.12s %-12.12s %-8.8s %-8.8s %-8.8s %-8.8s%n", "Symbol", "Date", "Open", "High", "Low", "Close");
          while (result.next()) {
            System.out
                .printf("%-12.12s %-12.12s %-8.8s %-8.8s %-8.8s %-8.8s%n", result.getString("date"), result.getString("symbol"), result.getString("open"), result.getString("high"),
                    result.getString("low"), result.getString("close"));
          }
      }
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }


}
