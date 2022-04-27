package br.com.byamada.accountapi.strategies;

import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.model.OperationType;
import br.com.byamada.accountapi.domain.model.Transaction;
import br.com.byamada.accountapi.domain.request.TransactionRequest;

public interface OperationStrategy {
    boolean doesApplyStrategy(OperationType operationType);
    Transaction process(Account account, TransactionRequest transaction, OperationType operationType);
}
