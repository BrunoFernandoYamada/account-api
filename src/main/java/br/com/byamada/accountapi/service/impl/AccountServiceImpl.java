package br.com.byamada.accountapi.service.impl;

import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.repository.AccountRepository;
import br.com.byamada.accountapi.domain.response.BalanceResponse;
import br.com.byamada.accountapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    public Account createAccount(String document) {
            if(repository.findByDocument(document).isPresent()){
                throw new IllegalArgumentException("Already exists an account with the informed document.");
            }
            Account account = Account.builder()
                    .document(document)
                    .balance(BigDecimal.ZERO)
                    .build();
        return repository.save(account);
    }

    public Account findAccountById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Account doesn't exists."));
    }

    public Account updateAccountAmount(Account account) {
        Account accountDb = findAccountById(account.getAccountId());

        if(!accountDb.getDocument().equals(account.getDocument())){
            throw new IllegalArgumentException("Invalid document to update origin doc: "+ accountDb.getDocument() + ", destination doc:"  + accountDb.getDocument());
        }
        accountDb.setBalance(account.getBalance());
        return repository.save(account);
    }

    public BalanceResponse findBalance(Long accountId) {
        Account account = findAccountById(accountId);
        return BalanceResponse.builder()
                .balance(account.getBalance())
                .build();

    }
}
