package br.com.byamada.accountapi.controller;

import br.com.byamada.accountapi.domain.request.TransactionRequest;
import br.com.byamada.accountapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> saveTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionService.saveTransaction(transactionRequest);
       return new ResponseEntity(HttpStatus.CREATED);

    }

}
