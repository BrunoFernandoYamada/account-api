package br.com.byamada.accountapi.service;

import br.com.byamada.accountapi.domain.model.OperationType;

public interface OperationTypeService {

    OperationType findOperationTypeById(Long id);
}
