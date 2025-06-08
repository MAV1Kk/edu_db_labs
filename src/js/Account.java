package com.example.model;

public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int roleId;

    public Account() { }

    // Конструктор для створення нового запису
    public Account(String firstName, String lastName,
                   String username, String email,
                   String password, int roleId) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.username  = username;
        this.email     = email;
        this.password  = password;
        this.roleId    = roleId;
    }

    // Конструктор для читання з бази (включно з id)
    public Account(int id, String firstName, String lastName,
                   String username, String email,
                   String password, int roleId) {
        this(firstName, lastName, username, email, password, roleId);
        this.id = id;
    }

    // Геттери і сеттери для всіх полів
    // ...

    @Override
    public String toString() {
        return "Account{" +
               "id=" + id +
               ", firstName='" + firstName + ''' +
               ", lastName='" + lastName + ''' +
               ", username='" + username + ''' +
               ", email='" + email + ''' +
               ", roleId=" + roleId +
               '}';
    }
}
