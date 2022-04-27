package br.com.byamada.accountapi.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private int statusCode;
    private String message;
    private Long timestamp;

}
