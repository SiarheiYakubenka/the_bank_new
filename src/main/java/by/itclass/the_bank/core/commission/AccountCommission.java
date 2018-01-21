package by.itclass.the_bank.core.commission;

import by.itclass.the_bank.core.accounts.Account;
import by.itclass.the_bank.core.accounts.Currency;

public class AccountCommission extends Commission<Account>{

    private final Account account;

    public AccountCommission(Account account) {
        if(account == null){
            throw new IllegalArgumentException("Нельзя создать коммисию с пустым счетом");
        }
        this.account = account;
    }


    @Override
    public void checkCommissionMonthly() {

        Currency currency = account.getCurrency();
        switch (currency){

            case BYN:
                break;
            case EUR:
        }

    }
}
