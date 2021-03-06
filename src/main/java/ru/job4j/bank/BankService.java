package ru.job4j.bank;

import java.util.*;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        List<Account> accounts = new ArrayList<>();
        users.putIfAbsent(user, accounts);
    }

    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            if (!users.get(user.get()).contains(account)) {
                users.get(user.get()).add(account);
            }
        }
    }

    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
            return accounts.stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst();
        } else {
            return Optional.empty();
        }
    }

    public void transferMoney(String srcPassport, String srcRequisite,
                              String destPassport, String destRequisite, double amount) {
        Optional<Account> srcOptionalAccount = findByRequisite(srcPassport, srcRequisite);
        Account srcAccount = null;
        if (srcOptionalAccount.isPresent()) {
            srcAccount = srcOptionalAccount.get();
        }
        Optional<Account> destOptionalAccount = findByRequisite(destPassport, destRequisite);
        Account destAccount = null;
        if (destOptionalAccount.isPresent()) {
            destAccount = destOptionalAccount.get();
        }
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            destAccount.setBalance(destAccount.getBalance() + amount);
            srcAccount.setBalance(srcAccount.getBalance() - amount);
        }
    }
}
