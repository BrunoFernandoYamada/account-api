package br.com.byamada.accountapi.domain.request.impl;

import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.repository.AccountRepository;
import br.com.byamada.accountapi.domain.request.AccountRequest;
import br.com.byamada.accountapi.domain.response.BalanceResponse;
import br.com.byamada.accountapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    public Account createAccount(AccountRequest accountRequest) {
        if(StringUtils.isEmpty(accountRequest.getDocumentNumber())) {
            throw new IllegalArgumentException("Invalid request, send a valid account");
        }

        if(repository.findByDocument(accountRequest.getDocumentNumber()).isPresent()){
                throw new IllegalArgumentException("Already exists an account with the informed document.");
        }

        if(accountRequest.getAmount() == null) {
            throw new IllegalArgumentException("Invalid amount.");
        }

        Account account;

        if(accountRequest.getAmount().compareTo(BigDecimal.ZERO) >= 0){
            account = Account.builder().document(accountRequest.getDocumentNumber())
                    .balance(accountRequest.getAmount())
                    .build();
        } else {
            throw new IllegalArgumentException("Send a positive value.");
        }

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
