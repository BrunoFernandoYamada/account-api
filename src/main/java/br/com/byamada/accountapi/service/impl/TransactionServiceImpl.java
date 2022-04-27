package br.com.byamada.accountapi.service.impl;

import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.model.OperationType;
import br.com.byamada.accountapi.domain.model.Transaction;
import br.com.byamada.accountapi.domain.repository.TransactionRepository;
import br.com.byamada.accountapi.domain.request.TransactionRequest;
import br.com.byamada.accountapi.service.AccountService;
import br.com.byamada.accountapi.service.OperationTypeService;
import br.com.byamada.accountapi.service.TransactionService;
import br.com.byamada.accountapi.strategies.OperationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationTypeService operationTypeService;

    @Autowired
    private List<OperationStrategy> strategies;

    @Transactional
    public Transaction saveTransaction(TransactionRequest transactionRequest) {
        if(transactionRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Invalid Transaction, send a positive amount value format.");
        }
        Transaction transaction = manageTransactionRequest(transactionRequest);
        accountService.updateAccountAmount(transaction.getAccount());
        return transactionRepository.save(transaction);
    }

    private Transaction manageTransactionRequest(TransactionRequest transactionRequest){

        Account account = accountService.findAccountById(transactionRequest.getAccount_id());
        OperationType operationType = operationTypeService.findOperationTypeById(transactionRequest.getOperation_type_id());
        Transaction transaction;

        for(OperationStrategy operationStrategy : strategies){
            if(operationStrategy.doesApplyStrategy(operationType)){
                return operationStrategy.process(account, transactionRequest, operationType);
            }
        }

        throw new IllegalArgumentException("Invalid Transaction");
    }

}
