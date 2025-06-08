package com.example.dao;

import com.example.model.Account;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Реалізація AccountDAO за допомогою прямого JDBC.
 */
public class AccountDAOImpl implements AccountDAO {
    private final Connection conn;

    public AccountDAOImpl() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    @Override
    public void addAccount(Account a) throws SQLException {
        String sql = "INSERT INTO account " +
                     "(first_name,last_name,username,email,password,role_id) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            ps.setString(3, a.getUsername());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getPassword());
            ps.setInt(6, a.getRoleId());
            ps.executeUpdate();
            // Отримуємо згенерований PK
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    a.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Account getAccountById(int id) throws SQLException {
        String sql = "SELECT * FROM account WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Account> getAllAccounts() throws SQLException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM account";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Account(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("role_id")
                ));
            }
        }
        return list;
    }

    @Override
    public void updateAccount(Account a) throws SQLException {
        String sql = "UPDATE account SET first_name=?, last_name=?, username=?, " +
                     "email=?, password=?, role_id=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            ps.setString(3, a.getUsername());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getPassword());
            ps.setInt(6, a.getRoleId());
            ps.setInt(7, a.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteAccount(int id) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM account WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}