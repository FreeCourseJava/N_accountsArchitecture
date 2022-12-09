package service;

import entity.Account;
import entity.Currency;
import repository.EntityRepositoryImpl;

public class MakeTransactionServiceImp {
    public void makeTransaction(String nameAccSource, String nameAccDestin, double quantity){

        EntityRepositoryImpl<Account> accountsRepository = new EntityRepositoryImpl<>(Account[].class, "accounts.json");
        Account accSource = accountsRepository.getEntityByKey(nameAccSource);
        Account accDestin = accountsRepository.getEntityByKey(nameAccDestin);

        EntityRepositoryImpl<Currency> currenciesRepository = new EntityRepositoryImpl<>(Currency[].class, "currencies.json");
        Currency currencySource = currenciesRepository.getEntityByKey(accSource.getCurrencyAbbrev());
        Currency currencyDestin = currenciesRepository.getEntityByKey(accDestin.getCurrencyAbbrev());

        ConvertService convertService = new ConvertServiceImpl();
        double amount = convertService.convertAcrossUSD(currencySource, currencyDestin, quantity);

        accSource.balance -= amount;
        accDestin.balance += amount;

        accountsRepository.updateEntityInRepo(accSource, accDestin);

    }
}
