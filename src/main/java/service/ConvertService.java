package service;

import entity.Currency;

public interface ConvertService {
    double convertAcrossUSD(double rateSource, double rateDestination, double quantity);
    double convertAcrossUSD(Currency currencySource, Currency  currencyDestination, double quantity);

}
