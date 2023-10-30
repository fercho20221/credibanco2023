package com.backend.credibanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backend.credibanco.Entity.CardEntity;
import com.backend.credibanco.Repository.CardRepository;
import com.backend.credibanco.ServiceImpl.CardServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BalanceCardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @Test
    public void testBalanceCard_ActualizacionExitosa() {
        Long cardId = 3938845015297983L;
        Long balanceToAdd = 220000L;

        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardId(cardId);
        cardEntity.setBalance(220000L);

        Mockito.when(cardRepository.findByCardId(cardId)).thenReturn(cardEntity);

        CardEntity cardBalance1 = cardRepository.findByCardId(cardId);

        assertEquals(cardId, cardBalance1.getCardId());
        assertEquals(220000L, cardBalance1.getBalance());
    }

    @Test
    public void testBalanceCard_Error() {
        Long cardId = 3938845015297983L;
        Long balanceToAdd = 100L;

        Mockito.when(cardRepository.findByCardId(cardId)).thenThrow(new RuntimeException("Error en la búsqueda"));

        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardId(cardId);
        cardEntity.setBalance(balanceToAdd);

        assertThrows(RuntimeException.class, () -> cardService.balanceCard(cardEntity));
    }
}
