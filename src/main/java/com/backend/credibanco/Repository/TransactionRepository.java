package com.backend.credibanco.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.credibanco.Entity.TransactionEntity;





@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

  TransactionEntity findBytransactionId(Integer transactionId);
 // Optional<TransactionEntity> findById(Integer Id);
   // void deleteBycardId(Long cardId);

}
 