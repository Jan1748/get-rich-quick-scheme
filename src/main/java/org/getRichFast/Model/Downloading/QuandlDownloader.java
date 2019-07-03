package org.getRichFast.Model.Downloading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class QuandlDownloader {

  public QuandlDownloader(String quandlApiKey) {
    this.quandlApiKey = quandlApiKey;
  }

  private String quandlApiKey;

  private ArrayList<String> downloadDataFromQuandl(String quandlCode) throws IOException {
    System.out.println("Start download data from stock: " + quandlCode);
    ArrayList<String> content = new ArrayList<>();
    String url =
        "https://www.quandl.com/api/v3/datasets/" + quandlCode + ".csv?api_key=" + quandlApiKey;
    URL apiUrl = new URL(url);
    HttpURLConnection apiConnection = (HttpURLConnection) apiUrl.openConnection();
    //System.out.println("\tTrying connect to: " + url);
    apiConnection.setRequestMethod("GET");
    //System.out.println("\tRequest Method: " + apiConnection.getRequestMethod());
    int status = 0;
    try {
      status = apiConnection.getResponseCode();
    }catch (Exception e){
      System.out.println("Error nr 1");
    }
    //System.out.println("\tHTTP response: " + status);
    if (status == 200) {
      BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        content.add(inputLine);
      }
      in.close();
      //System.out.println("\tDownload successfully completed");
    }else if (status == 429){
      System.out.println("Error Limit reached " +quandlCode);
    }else {
      System.out.println("Error invalid API-Key");
    }
    return content;
  }

  public ArrayList<String> getNotParsedDataArraylist(String quandlCode) throws IOException {
    return downloadDataFromQuandl(quandlCode);
  }
}
