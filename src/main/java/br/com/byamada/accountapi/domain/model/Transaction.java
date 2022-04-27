package br.com.byamada.accountapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TransactionId;

    @ManyToOne
    private Account account;

    @ManyToOne
    private OperationType operationType;

    private BigDecimal amount;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

}
