package br.com.byamada.accountapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Operations_Types")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_type_ID")
    private Long operationTypeId;

    @Column(name = "description")
    private String description;

}
