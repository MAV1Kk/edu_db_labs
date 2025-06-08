package com.example.dao;

import com.example.model.Account;
import java.sql.SQLException;
import java.util.List;

/**
 * Контракт CRUD-операцій з таблицею account.
 */
public interface AccountDAO {
    void addAccount(Account a) throws SQLException;
    Account getAccountById(int id) throws SQLException;
    List<Account> getAllAccounts() throws SQLException;
    void updateAccount(Account a) throws SQLException;
    void deleteAccount(int id) throws SQLException;
}