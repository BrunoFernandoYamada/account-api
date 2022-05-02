package br.com.byamada.accountapi.service;

import br.com.byamada.accountapi.domain.model.OperationType;
import br.com.byamada.accountapi.domain.repository.OperationTypeRepository;
import br.com.byamada.accountapi.mock.MockModel;
import br.com.byamada.accountapi.domain.request.impl.OperationTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class OperationTypeServiceImplTest {

    @InjectMocks
    private OperationTypeServiceImpl operationTypeService;

    @Mock
    private OperationTypeRepository repository;


    @Test
    void findOperationTypeById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(MockModel.mockOperationType()));

        OperationType operationType = operationTypeService.findOperationTypeById(Long.valueOf(4));

        assertEquals("PAGAMENTO", operationType.getDescription());
        assertEquals(Long.valueOf(4), operationType.getOperationTypeId());
    }

    @Test
    void findOperationTypeByIdException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> operationTypeService.findOperationTypeById(Long.valueOf(4))
        );
        assertTrue(thrown.getMessage().contains("OperationType doesn't exists."));
    }



}