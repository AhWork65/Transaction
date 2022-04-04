package com.heydari.transactions.controller;


import com.heydari.transactions.exception.TransactionBadRequestException;
import com.heydari.transactions.exception.TransactionCreateException;
import com.heydari.transactions.model.Deposit.Deposit;
import com.heydari.transactions.model.Transaction;
import com.heydari.transactions.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transactionservice")
public class TransactionController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;
//======================================================================================

    @PostMapping("/create")
    public Transaction createTransaction(@RequestBody Transaction transaction){
        LOGGER.info("createTransaction Input Parametr is :{}", transaction.toString());
        Transaction retTransaction;
        try {
            retTransaction = transactionService.createTransaction(transaction);
        } catch (Exception e) {
            LOGGER.error("createTransaction Exception is :{}", e.getMessage());
            throw new TransactionBadRequestException(e.getMessage());
        }
        LOGGER.info("createTransaction return is :{}", retTransaction.toString());
        return retTransaction;

    }

//======================================================================================
    @GetMapping("/getalltransactionsbydepoitnumber/{number}")
    public List<Transaction> getAllTransactionsByDepoitNumber(@PathVariable("number") String number)  {
        LOGGER.info("getAllTransactionsByDepoit Input Parametr is :{}", number);
        List<Transaction> transactions = null;
        try {
            transactions = transactionService.getAllTransactionsByDepoit(number);
        } catch (TransactionCreateException e) {
            LOGGER.error("getAllTransactionsByDepoitNumber Exception is :{}", e.getMessage());
            throw new TransactionBadRequestException(e.getMessage());
        }
        LOGGER.info("return {} Transaction ", transactions.size());
        return transactions;
    }
//======================================================================================
    @GetMapping("/getalltransactionsbydepoit")
    public List<Transaction> getAllTransactionsByDepoit(@RequestBody Deposit deposit)  {
        LOGGER.info("getAllTransactionsByDepoit Input Parametr is :{}", deposit.toString());
        List<Transaction> transactions = null;
        try {
            transactions = transactionService.getAllTransactionsByDepoit(deposit.getNumber());
        } catch (Exception e) {
            LOGGER.error("getAllTransactionsByDepoit Exception is :{}", e.getMessage());
            throw new TransactionBadRequestException(e.getMessage());
        }
        LOGGER.info("return {} Transaction ", transactions.size());
        return transactions;
    }
//======================================================================================

    @GetMapping ("/issueiracking/{issueiracking}")
    public Transaction getTransactionsByIssueTracking(@PathVariable("issueiracking") String issueTracking){
        LOGGER.info("getTransactionsByIssueTracking Input Parametr is :{}", issueTracking);

        Transaction transaction = transactionService.getTransactionsByIssueTracking(issueTracking);
        if (transaction == null){
            LOGGER.error("createTransaction Exception is Null");
            throw new TransactionBadRequestException("Result is Null");
        }
        LOGGER.info("return {} Transaction ", transaction.toString());
        return  transaction;
    }

}

