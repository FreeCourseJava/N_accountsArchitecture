package org.example.entity;

public class Account {

    public String accountName;

    public String currencyAbbrev;

    public double balance;

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", currencyAbbrev='" + currencyAbbrev + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getCurrencyAbbrev() {
        return currencyAbbrev;
    }

    public String getAccountName() {
        return accountName;
    }
}
