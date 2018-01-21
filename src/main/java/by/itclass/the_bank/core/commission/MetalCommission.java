package by.itclass.the_bank.core.commission;

import by.itclass.the_bank.core.accounts.Account;
import by.itclass.the_bank.core.storages.Storage;
import by.itclass.the_bank.core.storages.PreciousMetal;
import by.itclass.the_bank.core.storages.Share;

import java.math.BigDecimal;

public class MetalCommission extends Commission<PreciousMetal> {

    private  final Storage storage;
    private Account account;

    public MetalCommission(Storage storage, Account account){
        if (storage != null || storage.getValues().getClass() != PreciousMetal.class){
            throw new IllegalArgumentException("Хранилище не должно отсутствовать или хранить что-то кроме металла");
        }
        if (account == null) {
            throw new IllegalArgumentException("Обслуживающий счет не должен отсутствовать");
        }
        this.storage = storage;
        this.account = account;
    }

    @Override
    public void checkCommissionMonthly() {
        Share share =(Share)storage.getValues();
        double sum = storage.getAmount() * share.getValue();
        sum = Math.ceil(sum/50) * 0.1;
        account.withdraw((new BigDecimal(String.valueOf(sum))), account.getCurrency());


    }
}
