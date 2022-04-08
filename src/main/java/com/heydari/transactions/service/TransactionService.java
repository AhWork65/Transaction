package com.heydari.transactions.service;


import com.heydari.transactions.exception.TransactionCreateException;
import com.heydari.transactions.model.Deposit.Deposit;
import com.heydari.transactions.model.Transaction;
import com.heydari.transactions.model.TransactionsType;
import com.heydari.transactions.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository transactionRepository;

//==============================================================================
    private boolean validTransaction(Transaction transaction) {

        if (transaction== null)
            return  false;
        if (transaction.getPrice() == null)
            return  false;
        if (transaction.getSourceNumber() == null)
            return  false;
        if (transaction.getType() == null )
            return  false;
        if (transaction.getType().equals(TransactionsType.TRANSFER))
            return  (transaction.getDestinationNumber()!= null);

        return true;
    }

//======================================================================================
    @Transactional(rollbackFor = {Exception.class})
    public Transaction createTransaction (Transaction transaction) throws TransactionCreateException {
        LOGGER.debug("createTransaction : Service input paramet is :{}", (transaction == null) ? " null ":transaction.toString());
        if (!validTransaction(transaction)) {
            LOGGER.debug("createTransaction Service input paramet Not Valid");
            throw new TransactionCreateException("Paramet Not Valid");
        }
        UUID uuid=UUID.randomUUID();
        transaction.setIssueTracking(uuid.toString());
        LOGGER.debug("Transaction IssueTracking is :{}", transaction.getIssueTracking());

        return transactionRepository.save(transaction);
    }
//======================================================================================
public List<Transaction> getAllTransactionsByDepoit(String number) throws TransactionCreateException {
    LOGGER.debug("getAllTransactionsByDepoit Service input paramet is :{}", number);
    if (number == null){
        LOGGER.debug("getAllTransactionsByDepoit Deposit number is null");
        throw new TransactionCreateException("Deposit number is null");
    }

        return transactionRepository.findAllBySourceNumberEqualsOrDestinationNumberEquals(number,number);
}
//======================================================================================
public Transaction getTransactionsByIssueTracking(String issueTracking){
    LOGGER.debug("getAllTransactionsByDepoit Service input paramet is :{}", issueTracking);
    return transactionRepository.findDepositByIssueTracking(issueTracking);
}

}
