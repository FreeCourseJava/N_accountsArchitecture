package org.example.repository;

import org.example.entity.Account;

public interface AccountRepository{
    public void updateAccountInRepo(Account entitySource, Account entityDestin);
    public void updateAccountInRepo(Account entity);

    Account getAccountByKey(String nameAccSource);
}
