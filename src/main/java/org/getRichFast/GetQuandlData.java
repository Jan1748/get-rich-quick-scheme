package org.getRichFast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetQuandlData {

  GetQuandlData(String quandlApiKey) {
    this.quandlApiKey = quandlApiKey;
  }

  private String quandlApiKey;

  public void downloadDataFromQuandl(String quandlCode) {
    try {
      String url =
          "https://www.quandl.com/api/v3/datasets/" + quandlCode + ".csv?api_key=" + quandlApiKey;
      URL apiUrl = new URL(url);
      //Desktop.getDesktop().browse(apiUri);
      HttpURLConnection apiConnection = (HttpURLConnection) apiUrl.openConnection();
      apiConnection.setRequestMethod("GET");

      int status = apiConnection.getResponseCode();
      BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
      String inputline;
      ArrayList<String> content = new ArrayList<>();
      while ((inputline = in.readLine()) != null) {
        content.add(inputline);
      }
      in.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
