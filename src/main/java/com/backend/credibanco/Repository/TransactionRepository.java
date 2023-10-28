package com.backend.credibanco.Repository;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.credibanco.Entity.CardEntity;
import com.backend.credibanco.Entity.TransactionEntity;





@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

  TransactionEntity findBytransactionId(Integer transactionId);
  //Optional<TransactionEntity> findById(Long Id);
   // void deleteBycardId(Long cardId);

}
 