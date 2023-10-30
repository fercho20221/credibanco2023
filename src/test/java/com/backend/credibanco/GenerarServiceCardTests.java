package com.backend.credibanco;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.backend.credibanco.Service.CardService;
import com.backend.credibanco.Entity.CardEntity;
import com.backend.credibanco.Repository.CardRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class GenerarServiceCardTests {

    @MockBean
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @Test
    public void testGenerarCard() {
        Integer productId = 123456; 

        CardEntity mockCard = new CardEntity(); 

       
        when(cardRepository.saveAndFlush(ArgumentMatchers.any(CardEntity.class))).thenReturn(mockCard);

        CardEntity generatedCard = cardService.generarCard(productId);

        assertNotNull(generatedCard);
        assertEquals(mockCard, generatedCard);
    }
}
