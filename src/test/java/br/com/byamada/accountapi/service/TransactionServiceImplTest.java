package br.com.byamada.accountapi.service;

import br.com.byamada.accountapi.domain.model.Transaction;
import br.com.byamada.accountapi.domain.repository.TransactionRepository;
import br.com.byamada.accountapi.domain.request.TransactionRequest;
import br.com.byamada.accountapi.mock.MockModel;
import br.com.byamada.accountapi.domain.request.impl.TransactionServiceImpl;
import br.com.byamada.accountapi.strategies.CompraAVistaStrategy;
import br.com.byamada.accountapi.strategies.OperationStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private OperationTypeService operationTypeService;

    @Mock
    private List<OperationStrategy> strategies;

    @Test
    void saveTransaction() {
        OperationStrategy compraAVistaStrategy = mock(CompraAVistaStrategy.class);

        Iterator mockIterator = mock(Iterator.class);
        when(strategies.iterator()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true, false);
        when(mockIterator.next()).thenReturn(compraAVistaStrategy, compraAVistaStrategy);
        when(compraAVistaStrategy.doesApplyStrategy(any())).thenReturn(true);
        when(compraAVistaStrategy.process(any(), any(), any())).thenReturn(MockModel.mockTransaction());
        when(accountService.findAccountById(any())).thenReturn(MockModel.mockAccount());
        when(accountService.updateAccountAmount(any())).thenReturn(MockModel.mockAccount());
        when(operationTypeService.findOperationTypeById(anyLong())).thenReturn(MockModel.mockOperationType());
        Transaction transaction = transactionService.saveTransaction(MockModel.mockContaAVistaTransactionRequest());

        verify(compraAVistaStrategy, times(1)).doesApplyStrategy(any());
        verify(compraAVistaStrategy, times(1)).process(any(), any(), any());
    }

    public Transaction saveTransaction(TransactionRequest transactionRequest){
        return null;
    }



}