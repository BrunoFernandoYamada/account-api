package br.com.byamada.accountapi.service.impl;

import br.com.byamada.accountapi.domain.model.OperationType;
import br.com.byamada.accountapi.domain.repository.OperationTypeRepository;
import br.com.byamada.accountapi.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationTypeServiceImpl implements OperationTypeService {

    @Autowired
    private OperationTypeRepository repository;

    public OperationType findOperationTypeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("OperationType doesn't exists."));
    }

}
