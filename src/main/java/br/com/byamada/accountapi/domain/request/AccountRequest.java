package br.com.byamada.accountapi.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountRequest {
    @JsonProperty("document_number")
    private String documentNumber;
    private BigDecimal amount;
}
