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

    }

    @Test
    public void testUpdateBalance_SuccessfulUpdate() {

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionId(572235);
        transaction.setTransactionState("Completed");
        transaction.setTransactionTime(LocalDateTime.now().minusHours(12));

        Mockito.when(transactionRepository.findBytransactionId(572235)).thenReturn(transaction);

        CardEntity card = new CardEntity();
        card.setBalance(200L);

        Mockito.when(transactionRepository.findBytransactionId(anyInt())).thenReturn(transaction);

        ResponseEntity<String> response = transactionServiceImpl.updateBalance(572235);

        assertEquals("Balance updated successfully, and transaction anulated", response.getBody());
        assertEquals(200L, card.getBalance());
        assertEquals("Anulated", transaction.getTransactionState());
    }

}
