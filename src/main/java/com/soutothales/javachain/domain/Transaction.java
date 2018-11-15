package com.soutothales.javachain.domain;

import java.io.Serializable;
import java.util.Objects;

//@Entity
public class Transaction implements Serializable {

    private String sender;
    private String recipient;
    private Integer amount;

    public Transaction(String sender, String recipient, Integer amount) {
        //BlockChain bc = new BlockChain();
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        //bc.newTransaction(sender, recipient, amount);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(sender, that.sender) &&
                Objects.equals(recipient, that.recipient) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, recipient, amount);
    }
}
