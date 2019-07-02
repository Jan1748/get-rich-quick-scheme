package org.getRichFast.Model.Downloading;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.getRichFast.Model.Parsing.QuandlCodeParser;

public class QuandlCodeFinder {

  public static ArrayList<String> getQuandlCodes(String stockCode, String quandlApiKey) throws IOException {
    System.out.println("QuandlCodeFinder Beginn");
    ArrayList<String> quandleCodes = new ArrayList<>();
    System.out.println("Start download data");
    ArrayList<String> content = new ArrayList<>();
    String url = "https://www.quandl.com/api/v3/databases/" + stockCode + "/metadata?api_key=" + quandlApiKey;
    System.out.println("URL: " + url);
    URL apiUrl = new URL(url);
    HttpURLConnection apiConnection = (HttpURLConnection) apiUrl.openConnection();
    System.out.println("\tTrying connect to: " + url);
    apiConnection.setRequestMethod("GET");
    System.out.println("\tRequest Method: " + apiConnection.getRequestMethod());
    int status = apiConnection.getResponseCode();
    System.out.println("\tHTTP response: " + status);
    if(status == 200) {
      ZipInputStream in = new ZipInputStream(apiConnection.getInputStream());
      for (ZipEntry zipEntry; (zipEntry = in.getNextEntry()) != null; ) {
        System.out.println("reading zipEntry " + zipEntry.getName());
        Scanner sc = new Scanner(in);
        String quandleCode;
        while (sc.hasNextLine()) {
          quandleCodes.add(QuandlCodeParser.getQuandlCodes(sc.nextLine()));
        }
        System.out.println("reading " + zipEntry.getName() + " completed");
      }
      in.close();
    }else {
      System.out.println("Error something went wrong");
    }
      System.out.println("QuandlCodeFinder End");
    return quandleCodes;
  }
}