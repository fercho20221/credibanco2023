package com.backend.credibanco.ServiceImpl;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.backend.credibanco.Entity.CardEntity;
import com.backend.credibanco.Entity.TransactionEntity;
import com.backend.credibanco.Repository.CardRepository;
import com.backend.credibanco.Repository.TransactionRepository;
import com.backend.credibanco.Service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService {
    
    @Autowired
    private CardRepository cardEntityRepository;

    @Autowired
    private TransactionRepository transactionRepository; // Inyeccion de dependencias
    
    public ResponseEntity<String> performTransaction(Long cardId, Long price) {
        CardEntity card = cardEntityRepository.findByCardId(cardId);

        if (card != null) {
            if (card.getBalance() >= price) {
                TransactionEntity transaction = new TransactionEntity();
                transaction.setCardEntity(card);
                transaction.setBalance(card.getBalance() - price);
                transaction.setPrice(price);
                transaction.setCardId(cardId);

                // Generar un número aleatorio de 6 dígitos
                int randomTransactionId = generateRandomTransactionId();
                transaction.setTransactionId(randomTransactionId);

                transaction.setTransactionState("Completed");
                transaction.setTransactionTime(LocalDateTime.now());

                transactionRepository.save(transaction);

               
                // Agregar la nueva transacción a la lista de transacciones de la tarjeta
                card.getTransactions().add(transaction);
              

                // Actualizar el saldo y guardar la tarjeta
                card.setBalance(card.getBalance() - price);
                cardEntityRepository.save(card);

                return ResponseEntity.ok("Successful Transaction");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance for the transaction");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }
    }

    private int generateRandomTransactionId() {
        Random random = new Random();
        int min = 100000; // El valor mínimo de un número de 6 dígitos
        int max = 999999; // El valor máximo de un número de 6 dígitos
        return random.nextInt((max - min) + 1) + min;
    }



    @Override
    public TransactionEntity checkTransaction(Integer transactionId) {
       
        TransactionEntity transactionEntity = transactionRepository.findBytransactionId(transactionId);
        
        return transactionEntity;
    }
    
    public ResponseEntity<String> updateBalance(Integer transactionId) {
        TransactionEntity transaction = transactionRepository.findBytransactionId(transactionId);
    
        if (transaction != null) {
            // Verifica si el estado de la transacción es "Completed"
            if ("Completed".equals(transaction.getTransactionState())) {
                LocalDateTime transactionTime = transaction.getTransactionTime();
                LocalDateTime currentTime = LocalDateTime.now();
                long hoursDifference = ChronoUnit.HOURS.between(transactionTime, currentTime);
                long daysDifference = ChronoUnit.DAYS.between(transactionTime, currentTime);
    
                // Verifica si no han pasado más de 24 horas (1 día) desde la transacción
                if (hoursDifference < 24 && daysDifference == 0) {
                    CardEntity card = transaction.getCardEntity(); // Obtener la tarjeta asociada a la transacción
    
                    if (card != null) {
                        Long price = transaction.getPrice(); // Obtener el precio de la transacción
                        card.setBalance(card.getBalance() + price); // Incrementar el saldo
    
                        // Cambia el estado de la transacción a "Anulated"
                        transaction.setTransactionState("Anulated");
    
                        // Guarda los cambios en la tarjeta y en la transacción
                        cardEntityRepository.save(card);
                        transactionRepository.save(transaction);
    
                        return ResponseEntity.ok("Balance updated successfully, and transaction anulated");
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transaction is older than 24 hours or more than a day");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transaction is not in 'Completed' state");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
        }
    }
    
    
    

    

  

    
  

    




}
