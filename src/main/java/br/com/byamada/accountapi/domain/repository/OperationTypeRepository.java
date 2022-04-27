package br.com.byamada.accountapi.domain.repository;

import br.com.byamada.accountapi.domain.model.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {

}
