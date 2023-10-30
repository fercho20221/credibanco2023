package com.backend.credibanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.http.ResponseEntity;

import com.backend.credibanco.Entity.CardEntity;
import com.backend.credibanco.Repository.CardRepository;
import com.backend.credibanco.Repository.TransactionRepository;
import com.backend.credibanco.ServiceImpl.CardServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PerformTransactionServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        PowerMockito.mockStatic(CardServiceImpl.class);
    }

    @Test
    public void testPerformTransaction_SuccessfulTransaction() {
        Long cardId = 12345L;
        Long price = 100L;

        CardEntity card = new CardEntity();
        card.setCardId(cardId);
        card.setBalance(200L);

        Mockito.when(cardRepository.findByCardId(cardId)).thenReturn(card);

        PowerMockito.when(CardServiceImpl.generateRandomTransactionId()).thenReturn(123456);

        ResponseEntity<String> response = cardService.performTransaction(cardId, price);

        assertEquals(200L - price, card.getBalance());
        assertEquals("Successful Transaction", response.getBody());
        assertEquals(200L - price, card.getTransactions().get(0).getBalance());
        assertEquals(123456, card.getTransactions().get(0).getTransactionId());
        assertEquals("Completed", card.getTransactions().get(0).getTransactionState());
        assertNotNull(card.getTransactions().get(0).getTransactionTime());
    }

    @Test
    public void testPerformTransaction_InsufficientBalance() {
        Long cardId = 12345L;
        Long price = 300L;

        CardEntity card = new CardEntity();
        card.setCardId(cardId);
        card.setBalance(200L);

        Mockito.when(cardRepository.findByCardId(cardId)).thenReturn(card);

        ResponseEntity<String> response = cardService.performTransaction(cardId, price);

        assertEquals(200L, card.getBalance());
        assertEquals("Insufficient balance for the transaction", response.getBody());
    }

    @Test
    public void testPerformTransaction_CardNotFound() {
        Long cardId = 12345L;
        Long price = 100L;

        Mockito.when(cardRepository.findByCardId(cardId)).thenReturn(null);

        ResponseEntity<String> response = cardService.performTransaction(cardId, price);

        assertEquals("Card not found", response.getBody());
    }
}
