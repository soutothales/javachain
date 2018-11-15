package com.soutothales.javachain.services;

import com.soutothales.javachain.domain.BlockChain;
import com.soutothales.javachain.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    public List<Transaction> newTransaction(String sender, String recipient, Integer amount) {
        BlockChain bc = new BlockChain();
        bc.newTransaction(sender, recipient, amount);

        return bc.getCurrent_transactions();
    }

}
