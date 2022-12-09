package org.example.repository;

import org.example.entity.Currency;

public interface CurrencyRepository {
    public void updateCurrencyInRepo(Currency currency);

    Currency getCurrencyByAbbrev(String currencyAbbrev);
}
