package com.backend.credibanco;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.backend.credibanco.Entity.CardEntity;
import com.backend.credibanco.Entity.TransactionEntity;
import com.backend.credibanco.Repository.CardRepository;
import com.backend.credibanco.Repository.TransactionRepository;
import com.backend.credibanco.ServiceImpl.TransactionServiceImpl;

import java.time.LocalDateTime;


@ExtendWith(MockitoExtension.class)
public class UpdateBalanceServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CardRepository cardEntityRepository;

    @BeforeEach
    public void setUp() {
        // Configura el comportamiento de los mocks si es necesario
    }

    @Test
    public void testUpdateBalance_SuccessfulUpdate() {
        // Configura el comportamiento del mock transactionRepository
        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionId(572235);
        transaction.setTransactionState("Completed");
        transaction.setTransactionTime(LocalDateTime.now().minusHours(12)); // Dentro de las 24 horas

        

        Mockito.when(transactionRepository.findBytransactionId(572235)).thenReturn(transaction);

        // Configura el comportamiento del mock cardEntityRepository
        CardEntity card = new CardEntity();
        card.setBalance(200L); // Saldo actual
        
      //  Mockito.when(cardEntityRepository.save(card)).thenReturn(card);
         Mockito.when(transactionRepository.findBytransactionId(anyInt())).thenReturn(transaction);
        // Ejecuta el método que deseas probar
        ResponseEntity<String> response = transactionServiceImpl.updateBalance(572235);

        // Verifica si el resultado es el esperado
        assertEquals("Balance updated successfully, and transaction anulated", response.getBody());
        assertEquals(200L, card.getBalance()); // Verifica el nuevo saldo
        assertEquals("Anulated", transaction.getTransactionState()); // Verifica el estado de la transacción
    }

    // Agrega más pruebas para diferentes escenarios, como transacción no encontrada, transacción en estado incorrecto, transacción más antigua, etc.
}

