package org.getRichFast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetQuandlData {

  //FIXME: visibility modifier
  GetQuandlData(String quandlApiKey) {
    this.quandlApiKey = quandlApiKey;
  }

  private String quandlApiKey;

  private ArrayList<String> downloadDataFromQuandl(String quandlCode) {
    ArrayList<String> content = new ArrayList<>();
    //FIXME: improve error handling and logging output
    try {
      String url =
          "https://www.quandl.com/api/v3/datasets/" + quandlCode + ".csv?api_key=" + quandlApiKey;
      URL apiUrl = new URL(url);
      HttpURLConnection apiConnection = (HttpURLConnection) apiUrl.openConnection();
      apiConnection.setRequestMethod("GET");
      int status = apiConnection.getResponseCode();
      BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
      String inputline;
      while ((inputline = in.readLine()) != null) {
        content.add(inputline);
      }
      in.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

  public ArrayList<String> getNotParsedDataArraylist(String quandlCode) {
    return downloadDataFromQuandl(quandlCode);
  }
}
