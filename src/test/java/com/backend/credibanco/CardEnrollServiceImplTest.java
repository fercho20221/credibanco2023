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
public class CardEnrollServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @Test
    public void testEnrollCard() {

        CardEntity cardRequest = new CardEntity();
        cardRequest.setCardId(3993897660202071L);
        cardRequest.setFirstName("Fernando");
        cardRequest.setLastName("Cuervo");

        CardEntity mockCard = new CardEntity();
        Mockito.when(cardRepository.findByCardId(3993897660202071L)).thenReturn(mockCard);
        Mockito.when(cardRepository.saveAndFlush(mockCard)).thenReturn(mockCard);

        CardEntity enrolledCard = cardService.enrollCard(cardRequest);

        assertEquals("Fernando", enrolledCard.getFirstName());
        assertEquals("Cuervo", enrolledCard.getLastName());
        assertTrue(enrolledCard.isEnroll());
        assertEquals((long) 0, enrolledCard.getBalance());
        assertEquals("USD", enrolledCard.getCurrency());
    }
}
