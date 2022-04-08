package com.heydari.transactions;


import com.heydari.transactions.exception.TransactionCreateException;
import com.heydari.transactions.model.Transaction;
import com.heydari.transactions.model.TransactionStatus;
import com.heydari.transactions.model.TransactionsType;
import com.heydari.transactions.repository.TransactionRepository;
import com.heydari.transactions.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TransactionTest {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

//==============================================================================
    @Test
    void Create_Transaction_Test_Happy_Scenario() throws TransactionCreateException {
        Transaction transaction = new Transaction(1l,null,new BigDecimal(100L),TransactionStatus.SUCCESSFUL,TransactionsType.TRANSFER,"1","2","12334567890","Test");
        when(transactionRepository.save(Matchers.any())).thenReturn(transaction);
        Transaction retTransaction = transactionService.createTransaction(transaction);
        assertEquals(retTransaction.getId(), 1l);
    }
//==============================================================================
    @Test
    void create_Transaction_Test_ByNull_Paramt() throws TransactionCreateException {
        assertThrows(TransactionCreateException.class, () -> transactionService.createTransaction(null));
    }
//==============================================================================
@Test
void get_AllTransactions_ByDepoit_ByNull_Paramt() throws TransactionCreateException {
    assertThrows(TransactionCreateException.class, () -> transactionService.getAllTransactionsByDepoit(null));
}
//==============================================================================
    @Test
    void get_AllTransactions_ByDepoit_Happy_Scenario() throws TransactionCreateException {
        when(transactionRepository.findAllBySourceNumberEqualsOrDestinationNumberEquals(Matchers.any(), Matchers.any())).thenAnswer(t -> {
            List<Transaction> customerList = new ArrayList<>();
            customerList.add( new Transaction(1l,null,new BigDecimal(100L),TransactionStatus.SUCCESSFUL,TransactionsType.TRANSFER,"1","2","12334567890","Test"));
            customerList.add( new Transaction(2l,null,new BigDecimal(100L),TransactionStatus.SUCCESSFUL,TransactionsType.TRANSFER,"1","2","12334567890","Test"));
            return customerList;
        });
        List<Transaction> transactionList = transactionService.getAllTransactionsByDepoit("1");
        assertEquals(transactionList.size(), 2);
    }



    //==============================================================================
    @Test
    void get_Transactions_ByIssue_Tracking_Test() throws TransactionCreateException {
        when(transactionRepository.findDepositByIssueTracking(Matchers.any())).thenAnswer(t -> {
            return new Transaction(2l,null,new BigDecimal(100L),TransactionStatus.SUCCESSFUL,TransactionsType.TRANSFER,"1","2","12334567890","Test");
        });
        Transaction transaction = transactionService.getTransactionsByIssueTracking("12334567890");
        assertEquals(transaction.getIssueTracking(), "12334567890");
    }

}
