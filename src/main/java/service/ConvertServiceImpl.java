package service;

import entity.Currency;

public class ConvertServiceImpl implements ConvertService{
    @Override
    public double convertAcrossUSD(double rateSource, double rateDestin, double quantity) {
        double totalRate = rateSource * rateDestin;
        return quantity * totalRate;
    }

    @Override
    public double convertAcrossUSD(Currency currencySource, Currency currencyDestin, double quantity) {
        double totalRate = currencySource.rateToUsd * currencyDestin.rateToUsd;
        return quantity * totalRate;
    }
}
