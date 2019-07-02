package org.getRichFast.Model.Entity;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.getRichFast.Model.Downloading.QuandlDownloader;
import org.getRichFast.Model.Parsing.CsvParser;

public class DataShifter {

  public static ArrayList<StockBuild> getAndParseData(String quandlApiKey, String quandlCode) throws IOException, ParseException {

    QuandlDownloader quandlDownloader = new QuandlDownloader(quandlApiKey);
    ArrayList<String> data = quandlDownloader.getNotParsedDataArraylist(quandlCode);
    CsvParser csvParser = new CsvParser();
    ArrayList<String[]> csvParsedData = csvParser.getDataArrayList(data);
    StockBuildFiller stockBuildFiller = new StockBuildFiller();
    ArrayList<StockBuild> stock = stockBuildFiller.fillStockBuild(csvParsedData, quandlCode);
    return stock;
  }
}
