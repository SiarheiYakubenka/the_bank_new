package by.itclass.the_bank.core;

import by.itclass.the_bank.core.accounts.*;
import by.itclass.the_bank.core.clients.Client;
import by.itclass.the_bank.core.storages.Storage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bank {

    static Set<Client> clients;

    static Map<Long, Account> accounts;
    static Map<Long, Storage> storages;

    static Map<Client, Set<Long>> clientAccounts;
    private static Map<Client, Set<Long>> clientStorages;

    private static long accountId;
    private static long storageId;

    static {

        clients = new HashSet<>();
        accounts = new HashMap<>();
        storages = new HashMap<>();

        clientStorages = new HashMap<>();
        clientAccounts = new HashMap<>();
        accountId = 1;
        storageId = 1;
    }


    public static void addClient(String name, String secondName, String passportID){
        Client customer = new Client(name, secondName, passportID);
        clients.add(customer);
        clientAccounts.put(customer, new HashSet<>());
        clientStorages.put(customer, new HashSet<>());
    }

    public static Long addNewDebitAccount(Client customer, Currency currency) {
        if (customer == null) {
            throw new IllegalArgumentException("Нельзя создать счет для несущуствующего клиента");
        }
        Account account = new DebitAccount(currency);
        accounts.put(accountId, account);
        clientAccounts.get(customer).add(accountId);
        long temp = accountId;
        accountId++;
        return temp;
    }

    public static Long addNewPercentAccount(Client customer, Currency currency, float percent) {
        if (customer == null) {
            throw new IllegalArgumentException("Нельзя создать счет для несущуствующего клиента");
        }
        PercentAccount account = new PercentAccount(currency, percent);
        accounts.put(accountId, account);
        clientAccounts.get(customer).add(accountId);
        long temp = accountId;
        accountId++;
        return temp;
    }

    public static Long addNewCreditAccount(Client customer, Currency currency, float percent) {
        if (customer == null) {
            throw new IllegalArgumentException("Нельзя создать счет для несущуствующего клиента");
        }
        CreditAccount account = new CreditAccount(currency, percent);
        accounts.put(accountId, account);
        clientAccounts.get(customer).add(accountId);
        long temp = accountId;
        accountId++;
        return temp;
    }

    public static void depositOnAccount(Client customer, BigDecimal amount, long id) {
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }
        if (!clientAccounts.get(customer).contains(id)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        accounts.get(id).deposit(amount, accounts.get(id).getCurrency());
    }

    public static void withdrawFromAccount(Client customer, BigDecimal amount, long id) {
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }
        if (!clientAccounts.get(customer).contains(id)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        accounts.get(id).withdraw(amount, accounts.get(id).getCurrency());
    }

    public static BigDecimal getAccountBalance(Client customer, long id) {
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }
        if (!clientAccounts.get(customer).contains(id)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        return accounts.get(id).getBalance();
    }

    public static void transfer(Client customer1, Client customer2 ,
                                BigDecimal amount, Currency currency, long source, long destination) {

        if (customer1 == null || customer2 == null || !clients.contains(customer1) || !clients.contains(customer2)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(source) || !accounts.containsKey(destination)) {
            throw new IllegalArgumentException("Cчета не существует");
        }
        if (!clientAccounts.get(customer1).contains(source) || !clientAccounts.get(customer2).contains(destination)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        accounts.get(source).transfer(accounts.get(destination), amount, currency);
    }

    public static boolean searchClientByPassportNumber(String passportNumber) {
        Client fakeClient = new Client("_", "_", passportNumber);
        return clients.contains(fakeClient);
    }

    public static boolean searchAccountById(long id){

        return accounts.containsKey(id);
    }


    public static void addStorage(Client customer, Storage storage){
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (storage == null){
            throw new IllegalArgumentException("storage = null");
        }
        storages.put(storageId, storage);

    }
}
