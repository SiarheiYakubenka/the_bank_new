package by.itclass.the_bank.core.accounts;

import java.math.BigDecimal;

public class Exchange {
    static BigDecimal Convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount){
        BigDecimal result = amount;
        result = result.multiply(toCurrency.getPrice()).setScale(6, BigDecimal.ROUND_HALF_EVEN);
        result = result.divide(fromCurrency.getPrice(), 2, BigDecimal.ROUND_HALF_EVEN);
        return result;
    }
}
