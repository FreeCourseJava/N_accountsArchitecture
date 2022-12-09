package entity;

import Annotations.ID;

public class Account {

    @ID(name = "String")
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
}
