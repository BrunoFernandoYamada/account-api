package br.com.byamada.accountapi.strategies;

import br.com.byamada.accountapi.domain.enums.OperationTypeEnum;
import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.model.OperationType;
import br.com.byamada.accountapi.domain.model.Transaction;
import br.com.byamada.accountapi.domain.request.TransactionRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class CompraParceladaStrategy implements OperationStrategy {

    @Override
    public boolean doesApplyStrategy(OperationType operationType) {
        return OperationTypeEnum.COMPRA_PARCELADA.getName().equals(operationType.getDescription());
    }

    @Override
    public Transaction process(Account currentAccount, TransactionRequest transaction, OperationType operationType) {

            if(currentAccount.getBalance().subtract(transaction.getAmount()).compareTo(BigDecimal.ZERO) >= 0){
                transaction.setAmount(transaction.getAmount().negate());
                currentAccount.setBalance(currentAccount.getBalance().add(transaction.getAmount()));
            }else{
                throw new IllegalArgumentException("Customer balance is insufficient to process installments.");
            }
       
        return Transaction.builder()
                .account(currentAccount)
                .operationType(operationType)
                .amount(transaction.getAmount())
                .eventTime(LocalDateTime.now())
                .build();
    }
}
