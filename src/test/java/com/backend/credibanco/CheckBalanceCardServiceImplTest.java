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

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CheckBalanceCardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @Test
    public void testCheckBalanceCard_Exito() {
        Long cardId = 3938845015297983L;

        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardId(cardId);
        Mockito.when(cardRepository.findByCardId(cardId)).thenReturn(cardEntity);

        CardEntity checkedCard1 = cardRepository.findByCardId(cardId);

        assertNotNull(checkedCard1);
        assertEquals(cardId, checkedCard1.getCardId());
    }

}
