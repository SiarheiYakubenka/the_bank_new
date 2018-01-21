package by.itclass.the_bank.core.accounts;

import java.math.BigDecimal;

public class CreditAccount extends Account {

    private BigDecimal percent;
    private BigDecimal sumPercent;

    public CreditAccount(Currency currency, float percent) {
        super(currency);
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент должен быть положительным");
        }
        this.percent = new BigDecimal(String.valueOf(percent));
        sumPercent = new BigDecimal("0");
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
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
        setBalance(getBalance().subtract(amount));
    }


    public void daylyCalcOfPercent(){
        BigDecimal sum = getBalance().multiply(percent).setScale(3, BigDecimal.ROUND_HALF_EVEN);
        sum = sum.divide( new BigDecimal("100"), 3, BigDecimal.ROUND_HALF_EVEN);
        sum = sum.divide(new BigDecimal("365"), 2, BigDecimal.ROUND_HALF_UP);
        sumPercent = sumPercent.add(sum).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void recalcMonthOutcome(){
        withdraw(sumPercent, getCurrency());
        sumPercent = new BigDecimal("0");
    }

    @Override
    public String toString() {
        return "CreditAccount{" +
                "percent=" + percent +
                " balance=" + getBalance() +
                " currency=" + getCurrency()+
                "}";
    }
}
