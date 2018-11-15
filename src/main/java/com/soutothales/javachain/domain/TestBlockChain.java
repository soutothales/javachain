package com.soutothales.javachain.domain;

import javassist.bytecode.analysis.ControlFlow;

import java.util.List;

/*
* THIS CLASS IS FOR PURE TESTING!!!
*
* */

public class TestBlockChain {

    public static void main(String[] args) {
        BlockChain bc = new BlockChain();
        bc.newTransaction("thales", "dantas", 10);
        System.out.println(bc.getCurrent_transactions());
        System.out.println(bc.getChain());
        bc.newBlock(2);
        System.out.println(bc.getCurrent_transactions());
        System.out.println(bc.getChain());
        newTransaction2(bc, new Transaction("thales", "souto", 44));
        //bc.newBlock(3);
        System.out.println(bc.getCurrent_transactions());
        System.out.println(bc.getChain());
    }
    public static List<Transaction> newTransaction2(BlockChain bc, Transaction transaction) {
        bc.newTransaction(transaction.getSender(), transaction.getRecipient(), transaction.getAmount());

        return bc.getCurrent_transactions();
    }

}
