package com.backend.credibanco.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.credibanco.Entity.CardEntity;
import com.backend.credibanco.Entity.TransactionEntity;
import com.backend.credibanco.Service.TransactionService;

@RestController
public class transactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction/purchase")
    public ResponseEntity<String> purchase(@RequestBody TransactionEntity request) {
        ResponseEntity<String> response;

        try {
            ResponseEntity<String> transactionResult = transactionService.performTransaction(request.getCardId(),
                    request.getPrice());
            if (transactionResult.getStatusCode() == HttpStatus.OK) {
                response = ResponseEntity.ok("Successful Transaction");
            } else {
                response = ResponseEntity.status(transactionResult.getStatusCode()).body(transactionResult.getBody());
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Transaction Error: " + e.getMessage());
        }

        return response;
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<TransactionEntity> queryTransaction(@PathVariable Integer transactionId) {

        TransactionEntity trans = transactionService.checkTransaction(transactionId);

        return new ResponseEntity<>(trans, HttpStatus.OK);

    }

}
