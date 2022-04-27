package br.com.byamada.accountapi.domain.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionRequest {
    private Long account_id;
    private Long operation_type_id;
    private BigDecimal amount;

}
