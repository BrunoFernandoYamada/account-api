package br.com.byamada.accountapi.domain.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceResponse {
    private BigDecimal balance;
}
