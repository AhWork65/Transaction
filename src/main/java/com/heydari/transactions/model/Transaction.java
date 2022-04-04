package com.heydari.transactions.model;

import com.heydari.transactions.model.Deposit.Deposit;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date todoDate;
    private BigDecimal price;
    private TransactionStatus status;
    private TransactionsType type;
    private String sourceNumber;
    private String destinationNumber;
    private String issueTracking;
    private String description;
}
