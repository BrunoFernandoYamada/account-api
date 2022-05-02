package br.com.byamada.accountapi.mock;

import br.com.byamada.accountapi.domain.model.Account;
import br.com.byamada.accountapi.domain.model.OperationType;
import br.com.byamada.accountapi.domain.model.Transaction;
import br.com.byamada.accountapi.domain.request.AccountRequest;
import br.com.byamada.accountapi.domain.request.TransactionRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MockModel {
    public static Account mockAccount(){
        return Account.builder()
                .accountId(Long.valueOf(1234))
                .document("123456789")
                .balance(BigDecimal.ZERO)
                .build();
    }

    public static Account mockAccountOtherDocument(){
        return Account.builder()
                .accountId(Long.valueOf(1234))
                .document("12345000999")
                .balance(BigDecimal.ZERO)
                .build();
    }

    public static Account mockAccountToBeUpdate(){
        return Account.builder()
                .accountId(Long.valueOf(1234))
                .document("123456789")
                .balance(BigDecimal.valueOf(1000))
                .build();
    }

    public static OperationType mockOperationType(){
        return OperationType.builder()
                .operationTypeId(Long.valueOf(4))
                .description("PAGAMENTO")
                .build();
    }

    public static TransactionRequest mockContaAVistaTransactionRequest() {
        return TransactionRequest.builder()
                .account_id(Long.valueOf(1))
                .operation_type_id(Long.valueOf(4))
                .amount(BigDecimal.valueOf(1000))
                .build();
    }

    public static Transaction mockTransaction() {
        return Transaction.builder()
                .account(mockAccount())
                .operationType(mockOperationType())
                .amount(BigDecimal.valueOf(1000))
                .eventTime(LocalDateTime.now())
                .build();
    }

    public static AccountRequest mockAccountRequest(){
        return AccountRequest.builder()
                .documentNumber("123456789")
                .amount(BigDecimal.valueOf(1000))
                .build();
    }


}
