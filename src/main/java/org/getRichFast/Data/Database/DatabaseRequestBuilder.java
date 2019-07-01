package org.getRichFast.Data.Database;


public class DatabaseRequestBuilder {

  public static String requestBuild(ValueEnum valueEnum,ColumNameEnum columNameEnum, DateEnum dateEnum, String date, String date1, String Symbol) {
    String sqlCode = "SELECT ";
    if(valueEnum == ValueEnum.MIN){
      sqlCode += "MIN ";
    }
    if(valueEnum == ValueEnum.MAX){
      sqlCode += "MAX ";
    }
    if(columNameEnum == ColumNameEnum.OPEN){
      sqlCode += "(\"Open\") ";
    }
    if(columNameEnum == ColumNameEnum.HIGH){
      sqlCode += "(\"High\") ";
    }
    if(columNameEnum == ColumNameEnum.LOW){
      sqlCode += "(\"Low\") ";
    }
    if(columNameEnum == ColumNameEnum.CLOSE){
      sqlCode += "(\"Close\") ";
    }
      sqlCode += "FROM stockbuild WHERE ";
    if (dateEnum == DateEnum.EXACT) {
      sqlCode += "\"Date\" = ' " + date + "'";
    }
    if (dateEnum == DateEnum.INTERVALL && date1 != null) {
      sqlCode += "\"Date\" > '" + date + "' AND \"Date\" < '" + date1 + "'";
    }
    if (dateEnum == DateEnum.BEFORE) {
      sqlCode += "\"Date\" < '" + date + "'";
    }
    if (dateEnum == DateEnum.AFTER) {
      sqlCode += "\"Date\" > '" + date + "'";
    }
    if(Symbol != null){
      sqlCode += " AND \"Symbol\" = '" + Symbol + "'";
    }

    sqlCode += ";";
    return sqlCode;
  }

}
