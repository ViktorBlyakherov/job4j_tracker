package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User tmpUser = findByPassport(passport);
        if (tmpUser != null && account != null && !users.get(tmpUser).contains(account)) {
            users.get(tmpUser).add(account);
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        List<Account> accounts = users.get(findByPassport(passport));
        Account rslAccount = null;
        if (accounts != null) {
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    rslAccount = account;
                }
            }
        }
        return rslAccount;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {

        Account accountSrc = findByRequisite(srcPassport, srcRequisite);
        Account accountDest = findByRequisite(destPassport, destRequisite);
        if (accountSrc == null || accountDest == null || accountSrc.getBalance() < amount) {
            return false;
        }
        accountSrc.setBalance(accountSrc.getBalance() - amount);
        accountDest.setBalance(accountDest.getBalance() + amount);
        return true;
    }
}
