package com.heydari.transactions.repository;

import com.heydari.transactions.model.Deposit.Deposit;
import com.heydari.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findDepositByIssueTracking(String number);
    List<Transaction> findAllBySourceNumberEqualsOrDestinationNumberEquals(String source, String destination);
}
