package br.com.byamada.accountapi.service;

import br.com.byamada.accountapi.domain.model.Transaction;
import br.com.byamada.accountapi.domain.request.TransactionRequest;

public interface TransactionService {
    Transaction saveTransaction(TransactionRequest transactionRequest);
}
