package org.getRichFast.Downloading;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.getRichFast.Parsing.QuandlCodeParser;
import org.getRichFast.UI.InputFunctions;

public class QuandlCodeFinder {

  public ArrayList<String> getQuandlCodes(String stockCode, String quandlApiKey) throws IOException {
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
    ZipInputStream in = new ZipInputStream(apiConnection.getInputStream());
    for (ZipEntry zipEntry;(zipEntry = in.getNextEntry()) != null; )
    {
      System.out.println("reading zipEntry " + zipEntry.getName());
      Scanner sc = new Scanner(in);
      String quandleCode;
      while (sc.hasNextLine()) {
        quandleCodes.add(QuandlCodeParser.getQuandlCodes(sc.nextLine()));
      }
      System.out.println("reading " + zipEntry.getName() + " completed");
    }
    in.close();
    return quandleCodes;
  }
}