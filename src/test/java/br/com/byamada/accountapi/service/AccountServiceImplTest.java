package br.com.byamada.accountapi.service;

import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.repository.AccountRepository;
import br.com.byamada.accountapi.mock.MockModel;
import br.com.byamada.accountapi.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountServiceImpl accountService;


    @BeforeEach
    void setUp(){
        when(repository.findByDocument(anyString())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(MockModel.mockAccount());
    }

    @Test
    void createAccount() {
        assertEquals(accountService.createAccount("123456789"), MockModel.mockAccount());
    }

    @Test
    void createAccountException() {
        when(repository.findByDocument(anyString())).thenReturn(Optional.of(MockModel.mockAccount()));

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> accountService.createAccount("123456789")
        );

        assertTrue(thrown.getMessage().contains("Already exists an account with the informed document"));
    }

    @Test
    void findAccountById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(MockModel.mockAccount()));
        assertEquals(accountService.findAccountById(Long.valueOf(1234)), MockModel.mockAccount());
    }

    @Test
    void findAccountByIdException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> accountService.findAccountById(Long.valueOf(1234))
        );

        assertTrue(thrown.getMessage().contains("Account doesn't exists."));
    }

    @Test
    void updateAccountAmount() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(MockModel.mockAccount()));
        when(repository.save(any())).thenReturn(MockModel.mockAccountToBeUpdate());
        Account account = accountService.updateAccountAmount(MockModel.mockAccountToBeUpdate());
        assertEquals(MockModel.mockAccount().getAccountId(), account.getAccountId());
        assertEquals(MockModel.mockAccount().getDocument(), account.getDocument());
        assertNotEquals(MockModel.mockAccount().getBalance(), account.getBalance());
        assertEquals(BigDecimal.valueOf(1000), account.getBalance());
    }

    @Test
    void updateAccountAmountException() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(MockModel.mockAccountOtherDocument()));
        when(repository.save(any())).thenReturn(MockModel.mockAccountToBeUpdate());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> accountService.updateAccountAmount(MockModel.mockAccountToBeUpdate())
        );

        assertTrue(thrown.getMessage().contains("Invalid document to update origin doc"));
    }

    @Test
    void findBalance() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(MockModel.mockAccount()));
        BigDecimal balance = accountService.findBalance(Long.valueOf(1234)).getBalance();
        assertEquals(BigDecimal.ZERO, balance);
    }

    @Test
    void findBalanceException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> accountService.findBalance(Long.valueOf(1234))
        );

        assertTrue(thrown.getMessage().contains("Account doesn't exists."));
    }


}