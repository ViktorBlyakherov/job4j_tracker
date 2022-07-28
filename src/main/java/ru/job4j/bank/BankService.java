package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс реализует функционал банковских переводов между акааунтами пользователей
 * Пользователь определяется по паспорту
 * У каждого пользователя может быть несколько счетов
 * @author Viktor Blyakherov
 * @version 1.0
 */
public class BankService {
    /**
     * Пользователи хранятся в Map, ключ - пользователь, значения List счетов пользователя
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя. На вход принимает объект User, если такой пользователь уже есть добавления
     * не происходит. При добавлении создается пустой список счетов.
     * @param user объект пользователь для добавления
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод добавляет счет в список счетов пользователя.
     * На вход принимает номер паспорта пользователя для идентификации и объект Account
     * Если такой счет уже есть у пользователя то добавления не происходит.
     * @param passport номер паспорта, по которому ищется пользователь
     * @param account объект Account который добавляется к списку счетов пользователя
     */
    public void addAccount(String passport, Account account) {
        User tmpUser = findByPassport(passport);
        if (tmpUser != null) {
            List<Account> list = users.get(tmpUser);
            if (!list.contains(account)) {
                list.add(account);
            }
        }
    }

    /**
     * Метод поиска пользователя по паспорту.
     * Принимает на вход номер паспорта.
     * @param passport номер паспорта по которому осуществляется поиск пользователя
     * @return возвращает объект User найденный по номеру паспорта. Если пользователь не найден возвращает null
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод ищет счет пользователя по паспорту и реквизитам счета
     * @param passport номер паспорта по которому ищется пользователь
     * @param requisite реквизиты счета по которому ищется счет для найденного пользователя
     * @return возвращает счет пользователя, если пользователь или счет не найдены возвращает null
     */
    public Account findByRequisite(String passport, String requisite) {
        List<Account> accounts = users.get(findByPassport(passport));
        Account rslAccount = null;
        if (accounts != null) {
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    rslAccount = account;
                    break;
                }
            }
        }
        return rslAccount;
    }

    /**
     * Метод перевода денег между счетами, со счета с которого переводят сумма вычитается, на счет на который
     * переводят сумма прибавляется
     * @param srcPassport паспорт пользователя со счета которого осуществляется перевод
     * @param srcRequisite реквизиты счета с которого осуществляется перевод
     * @param destPassport паспорт пользователя на счет которого осуществляется перевод
     * @param destRequisite реквизиты счета на который осуществляется перевод
     * @param amount сумма перевода
     * @return возвращает true если перевод осуществлен, если один из счетов или пользователей не найдены или денег
     * на счете недостаточно для перевода то возвращает false
     */
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
