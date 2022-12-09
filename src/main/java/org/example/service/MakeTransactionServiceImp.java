package org.example.service;

import org.example.entity.Account;
import org.example.entity.Currency;
import org.example.repository.*;

public class MakeTransactionServiceImp {
    public void makeTransaction(String nameAccSource, String nameAccDestin, double quantity) {

        AccountRepository accountsRepository = new AccountRepositoryImpl("accounts.json");
        Account accSource = accountsRepository.getAccountByKey(nameAccSource);
        Account accDestin = accountsRepository.getAccountByKey(nameAccDestin);

        CurrencyRepository currenciesRepository = new CurrencyRepositoryImpl("currencies.json");
        Currency currencySource = currenciesRepository.getCurrencyByAbbrev(accSource.getCurrencyAbbrev());
        Currency currencyDestin = currenciesRepository.getCurrencyByAbbrev(accDestin.getCurrencyAbbrev());

        ConvertService convertService = new ConvertServiceImpl();
        double amount = convertService.convertAcrossUSD(currencySource, currencyDestin, quantity);

        accSource.balance -= amount;
        accDestin.balance += amount;

        accountsRepository.updateAccountInRepo(accSource, accDestin);
    }
}
