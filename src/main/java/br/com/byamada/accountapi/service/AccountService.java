package br.com.byamada.accountapi.service;

import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.request.AccountRequest;
import br.com.byamada.accountapi.domain.response.BalanceResponse;

public interface AccountService {

    Account createAccount(AccountRequest accountRequest);
    Account findAccountById(Long id);
    Account updateAccountAmount(Account account);
    BalanceResponse findBalance(Long accountId);
}
