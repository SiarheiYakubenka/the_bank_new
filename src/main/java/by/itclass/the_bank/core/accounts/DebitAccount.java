package by.itclass.the_bank.core.accounts;

import java.math.BigDecimal;

public class DebitAccount extends Account{

    public DebitAccount(Currency currency) {
        super(currency);
    }

    @Override
    public void withdraw(BigDecimal amount, Currency currency) {
        if (amount == null)
            throw new IllegalArgumentException("Сумма не может быть пустой (не передана)");
        if (currency == null)
            throw new IllegalArgumentException("Валюта не может быть пустой (не передана)");
        if(amount.signum() <= 0){
            throw new NegativeNumberException("Неправильная сумма для снятия", amount.toString());
        }
        if (getBalance().compareTo(amount) < 0){
            throw new NotEnoughValuableException();
        }
        if(getCurrency() == currency){
            setBalance(getBalance().subtract(amount));
        } else {
            BigDecimal convertedAmount = Exchange.Convert(currency, getCurrency(), amount);
            setBalance(getBalance().subtract(convertedAmount));
        }
    }

    @Override
    public String toString() {
        return "DebitAccount{} " +
                "balance=" + getBalance() +
                " currency=" + getCurrency()+
                "}";
    }
}
