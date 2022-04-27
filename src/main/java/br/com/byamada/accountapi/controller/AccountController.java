package br.com.byamada.accountapi.controller;

import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.request.AccountRequest;
import br.com.byamada.accountapi.domain.response.AccountResponse;
import br.com.byamada.accountapi.domain.response.BalanceResponse;
import br.com.byamada.accountapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody AccountRequest accountRequest) {
        accountService.createAccount(accountRequest.getDocument_number());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> findAccountById(@PathVariable Long accountId) {
        Account account = accountService.findAccountById(accountId);
        return ResponseEntity.ok().body(AccountResponse.builder()
                .account_id(account.getAccountId())
                .document_number(account.getDocument())
                .build());
    }

    @GetMapping("balance/{accountId}")
    public ResponseEntity<BalanceResponse> findBalanceByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok().body(accountService.findBalance(accountId));
    }

}
