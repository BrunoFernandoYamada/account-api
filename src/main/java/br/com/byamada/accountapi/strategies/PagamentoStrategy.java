package br.com.byamada.accountapi.strategies;

import br.com.byamada.accountapi.domain.enums.OperationTypeEnum;
import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.model.OperationType;
import br.com.byamada.accountapi.domain.model.Transaction;
import br.com.byamada.accountapi.domain.request.TransactionRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PagamentoStrategy implements OperationStrategy {

    @Override
    public boolean doesApplyStrategy(OperationType operationType) {
        return OperationTypeEnum.PAGAMENTO.getName().equals(operationType.getDescription());
    }

    @Override
    public Transaction process(Account currentAccount, TransactionRequest transaction, OperationType operationType) {
        currentAccount.setBalance(currentAccount.getBalance().add(transaction.getAmount()));
        return Transaction.builder()
                .account(currentAccount)
                .operationType(operationType)
                .amount(transaction.getAmount().abs())
                .eventTime(LocalDateTime.now())
                .build();
    }
}
