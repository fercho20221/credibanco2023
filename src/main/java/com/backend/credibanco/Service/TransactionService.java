package com.backend.credibanco.Service;

import org.springframework.http.ResponseEntity;


import com.backend.credibanco.Entity.TransactionEntity;

public interface TransactionService {

   
    public ResponseEntity<String>  performTransaction(Long cardId, Long price);
    public TransactionEntity checkTransaction(Integer transactionId);
    public TransactionEntity checkTransactions(Integer transactionId);
   


}
