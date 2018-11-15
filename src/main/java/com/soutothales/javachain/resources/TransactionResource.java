package com.soutothales.javachain.resources;


import com.soutothales.javachain.domain.Transaction;
import com.soutothales.javachain.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/transaction/new")
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST)
    public List<Transaction> newTransaction(@RequestBody Transaction transaction) {
        return transactionService.newTransaction(transaction.getSender(), transaction.getRecipient(), transaction.getAmount());
    }

}
