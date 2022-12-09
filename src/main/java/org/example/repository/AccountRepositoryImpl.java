package org.example.repository;

import com.google.gson.Gson;
import org.example.entity.Account;

import java.io.*;
import java.util.Scanner;


public class AccountRepositoryImpl implements AccountRepository {

    private final Gson gson;

    private final String fileName;

    private Account[] accountsRerosit;


    public AccountRepositoryImpl( String fileName) {
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
        accountsRerosit = gson.fromJson(temp, Account[].class);

        sc.close();
    }

    private void write() {
        String stringForWrite = gson.toJson(accountsRerosit);
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
    public void updateAccountInRepo(Account account) {

        for (int i = 0, entityRepositLength = accountsRerosit.length; i < entityRepositLength; i++) {
            if (account.getAccountName().equals(accountsRerosit[i].getAccountName())) {
                accountsRerosit[i] = account;
            }
        }
        write();
    }

    public void updateAccountInRepo(Account accountSource, Account accountDestin) {

        for (int i = 0, entityRepositLength = accountsRerosit.length; i < entityRepositLength; i++) {
            if (accountSource.getAccountName().equals(accountsRerosit[i].getAccountName())) {
                accountsRerosit[i] = accountSource;
            }
            if (accountDestin.getAccountName().equals(accountsRerosit[i].getAccountName())) {
                accountsRerosit[i] = accountDestin;
            }
        }
        write();
    }
    public Account getAccountByKey(String key) {
        for (Account account : accountsRerosit) {
            if (key.equals(account.getAccountName()))
                { return account; }
        }
        return null;
    }

}
