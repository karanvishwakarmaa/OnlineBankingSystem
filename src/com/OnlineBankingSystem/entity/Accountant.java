package com.OnlineBankingSystem.entity;

public class Accountant {
    private String AccountantUsername;
    private String AccountantEmail;
    private String AccountantPassword;

    public Accountant(String accountantUsername, String accountantEmail, String accountantPassword) {
        this.AccountantUsername = accountantUsername;
        this.AccountantEmail = accountantEmail;
        this.AccountantPassword = accountantPassword;
    }
    public Accountant() {
        super();
    }

    public String getAccountantUsername() {
        return AccountantUsername;
    }

    public void setAccountantUsername(String accountantUsername) {
        AccountantUsername = accountantUsername;
    }

    public String getAccountantEmail() {
        return AccountantEmail;
    }

    public void setAccountantEmail(String accountantEmail) {
        AccountantEmail = accountantEmail;
    }

    public String getAccountantPassword() {
        return AccountantPassword;
    }

    public void setAccountantPassword(String accountantPassword) {
        AccountantPassword = accountantPassword;
    }

    @Override
    public String toString() {
        return "Accountant{" +
                "AccountantUsername='" + AccountantUsername + '\'' +
                ", AccountantEmail='" + AccountantEmail + '\'' +
                ", AccountantPassword='" + AccountantPassword + '\'' +
                '}';
    }
}
