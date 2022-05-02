package br.com.byamada.accountapi.controller.hadler;

import br.com.byamada.accountapi.domain.response.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgExceptionHandler(IllegalArgumentException ex, HttpServletRequest request){
        ErrorResponse standardError = ErrorResponse.builder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintViolationExceptionHandler(IllegalArgumentException ex, HttpServletRequest request){
        ErrorResponse standardError = ErrorResponse.builder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .message("Custumer already exists.")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}
