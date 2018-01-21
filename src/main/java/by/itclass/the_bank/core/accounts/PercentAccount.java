package by.itclass.the_bank.core.accounts;

import java.math.BigDecimal;

public class PercentAccount extends Account {

    private BigDecimal percent;
    private BigDecimal sumPercent;

    public PercentAccount(Currency currency, float percent) {
        super(currency);
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент должен быть положительным");
        }
        this.percent = new BigDecimal(String.valueOf(percent));
        sumPercent = new BigDecimal("0");
    }


    public void calcOfPercent(){
        BigDecimal sum = getBalance().multiply(percent).setScale(3, BigDecimal.ROUND_HALF_EVEN);
        sum = sum.divide( new BigDecimal("100"), 3, BigDecimal.ROUND_HALF_EVEN);
        sum = sum.divide(new BigDecimal("365"), 2, BigDecimal.ROUND_HALF_UP);
        sumPercent = sumPercent.add(sum).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    public void recalcMonthOutcome(){
        deposit(sumPercent, this.getCurrency());
        sumPercent = new BigDecimal("0");
    }

    @Override
    public void withdraw(BigDecimal amount, Currency currency) {
        if (amount == null){
            throw new IllegalArgumentException("Сумма не может быть пустой (не передана)");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Валюта не может быть пустой (не передана)");
        }
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
        return "PercentAccount{" +
                "percent=" + percent +
                " balance=" + getBalance() +
                " currency=" + getCurrency()+
                "}";
    }
}
