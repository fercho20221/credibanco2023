package com.backend.credibanco.Service;

import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.springframework.http.ResponseEntity;


import com.backend.credibanco.Entity.TransactionEntity;

public interface TransactionService {

   
    public ResponseEntity<String>  performTransaction(Long cardId, Long price);
    public TransactionEntity checkTransaction(Integer transactionId);
    public ResponseEntity<String> updateBalance(Integer transactionId);
    
   


}
