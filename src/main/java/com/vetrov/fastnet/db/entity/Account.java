package com.vetrov.fastnet.db.entity;


public class Account extends Entity {
    private static final long serialVersionUID = 1L;
    private long number;
    private double balance;

    public Account() {
    }

    public Account(long id) {
        super(id);
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
