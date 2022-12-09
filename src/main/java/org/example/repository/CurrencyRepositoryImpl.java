package org.example.repository;

import com.google.gson.Gson;
import org.example.entity.Currency;

import java.io.*;
import java.util.Scanner;


public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final Gson gson;

    private final String fileName;

    private Currency[] currenciesRerosit;


    public CurrencyRepositoryImpl(String fileName) {
        this.gson = new Gson();
        this.fileName = fileName;
        this.read();
    }

    private void read() {
        InputStream InputStream = null;
        try {
            InputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Scanner sc = new Scanner(InputStream);
        String temp = "";
        temp = sc.nextLine();
        currenciesRerosit = gson.fromJson(temp, Currency[].class);

        sc.close();
    }

    private void write() {
        String stringForWrite = gson.toJson(currenciesRerosit);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PrintStream writer = new PrintStream(outputStream);
        writer.println(stringForWrite);

        writer.close();
    }

    @Override
    public void updateCurrencyInRepo(Currency currency) {

        for (int i = 0, currenciesRepositLength = currenciesRerosit.length; i < currenciesRepositLength; i++) {
            if (currency.getAbbrev().equals(currenciesRerosit[i].getAbbrev())) {
                currenciesRerosit[i] = currency;
            }
        }
        write();
    }

    public Currency getCurrencyByAbbrev(String currencyAbbrev) {
        for (Currency currency : currenciesRerosit) {
            if (currencyAbbrev.equals(currency.getAbbrev()))
                { return currency; }
        }
        return null;
    }

}
