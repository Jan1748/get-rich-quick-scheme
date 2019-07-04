package org.getRichFast.Model;

import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;
import org.getRichFast.Model.Entity.DataShifter;
import org.getRichFast.Model.Entity.StockBuild;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.getRichFast.Data.Database.DatabaseConnection;
import org.getRichFast.Model.Downloading.QuandlCodeFinder;
import org.getRichFast.Model.Entity.DataShifter;
import org.getRichFast.Model.Entity.StockBuild;

public class RequestEditorThreads extends Thread {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private ArrayList<String> symbols;
    private String symbol;
    private String apiKey;
    private int startpoint;
    private int end;
    private int threadnumber;
    private int errors;

    public RequestEditorThreads(ArrayList<String> symbols, String symbol, String apiKey, int startpoint, int end, int threadnumber) {
        this.symbols = symbols;
        this.apiKey = apiKey;
        this.end = end;
        this.startpoint = startpoint;
        this.symbol = symbol;
        this.threadnumber = threadnumber;
        this.errors = 0;

    }

    public void run() {
        for (int i = startpoint; i < end; i++) {
            System.out.println("Stock nr. " + (i-startpoint) +" from: " + (end-startpoint) + " Thread nr." + threadnumber);
            ArrayList<StockBuild> stocks = null;
            try {
                stocks = DataShifter.getAndParseData(apiKey, symbols.get(i));
            } catch (Exception e) {
                break;
            }
            try {

                if (stocks.get(0) != null) {
                    databaseConnection.insertDataToDatabase(stocks);
                }
            }catch (Exception e){
                if(errors > 3){
                    i++;
                }
                System.out.println("Restart Stock Download");
                errors++;
                i--;
            }
        }
        System.out.println("Thread nr." +threadnumber +" finished");
    }
}
