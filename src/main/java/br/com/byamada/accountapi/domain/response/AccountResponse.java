package br.com.byamada.accountapi.domain.response;

import br.com.byamada.accountapi.domain.model.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {

    private Long account_id;
    private String document_number;

}
