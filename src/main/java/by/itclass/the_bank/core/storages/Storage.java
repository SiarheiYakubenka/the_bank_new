package by.itclass.the_bank.core.storages;

import by.itclass.the_bank.core.accounts.Account;
import by.itclass.the_bank.core.accounts.NotEnoughValuableException;

public class Storage {
    private Valuable values;
    private int amount;
    private Account serviceAccount;

    public Storage(Valuable values, Account serviceAccount){
        if (values == null) {
            throw new NullPointerException("Необходимо указать непустое содержимое для ячейки");
        }
        if(serviceAccount == null) {
            throw new NullPointerException("Необхожимо указать непустой аккаунт для обслуживания ячейки");
        }
        this.values = values;
        this.serviceAccount = serviceAccount;
    }

    public Account getServiceAccount() {
        return serviceAccount;
    }

    public Valuable getValues() {
        return values;
    }

    public int getAmount() {
        return amount;
    }

    public void setServiceAccount(Account serviceAccount) {
        if (serviceAccount == null) {
            throw new IllegalArgumentException("Аккаунт для обслуживания не может быть пустым");
        }
        this.serviceAccount = serviceAccount;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Вносимое количество должно быть положительным");
        }
        if(values instanceof PreciousMetal && amount >3000) {
            throw new IllegalArgumentException("В ячейке не может храниться более 3 кг");
        }
        this.amount += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cнимаемое количество должно быть положительным");
        }
        if (this.amount < amount) {
            throw new NotEnoughValuableException();
        }
        this.amount -= amount;
    }

    public boolean changeStorableType(Valuable newType) {
        if (amount == 0) {
            this.values = newType;
            return true;
        } else {
            return false;
        }
    }
}
