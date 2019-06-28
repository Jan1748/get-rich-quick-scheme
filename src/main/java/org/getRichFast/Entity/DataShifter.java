package org.getRichFast.Entity;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.getRichFast.Downloading.QuandlDownloader;
import org.getRichFast.Entity.StockBuild;
import org.getRichFast.Entity.StockBuildFiller;
import org.getRichFast.Parsing.CsvParser;

public class DataShifter {

  public ArrayList<StockBuild> getAndParseData(String quandlApiKey, String quandlCode) throws IOException, ParseException {

    QuandlDownloader quandlDownloader = new QuandlDownloader(quandlApiKey);
    ArrayList<String> data = quandlDownloader.getNotParsedDataArraylist(quandlCode);
    CsvParser csvParser = new CsvParser();
    ArrayList<String[]> csvParsedData = csvParser.getDataArrayList(data);
    StockBuildFiller stockBuildFiller = new StockBuildFiller();
    ArrayList<StockBuild> stock = stockBuildFiller.fillStockBuild(csvParsedData, quandlCode);
    return stock;
  }
}
