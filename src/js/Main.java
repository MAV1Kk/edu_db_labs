package com.example;

import com.example.dao.AccountDAO;
import com.example.dao.AccountDAOImpl;
import com.example.model.Account;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AccountDAO dao = new AccountDAOImpl();

            // CREATE: додаємо запис
            Account a = new Account("Olena", "Shevchenko",
                                    "olena_shev", "olena@example.com",
                                    "pass123", 2);
            dao.addAccount(a);
            System.out.println("Created: " + a);

            // READ: всі записи
            List<Account> all = dao.getAllAccounts();
            all.forEach(System.out::println);

            // UPDATE: міняємо email
            a.setEmail("olena.shev@newmail.com");
            dao.updateAccount(a);
            System.out.println("After update: " + dao.getAccountById(a.getId()));

            // DELETE: видаляємо запис
            dao.deleteAccount(a.getId());
            System.out.println("After delete:");
            dao.getAllAccounts().forEach(System.out::println);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
