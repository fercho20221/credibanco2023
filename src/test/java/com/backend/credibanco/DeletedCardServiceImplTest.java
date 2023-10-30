package com.backend.credibanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backend.credibanco.Repository.CardRepository;
import com.backend.credibanco.ServiceImpl.CardServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DeletedCardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @Test
    public void testEliminarCard_Exito() {
        // Supongamos que la eliminación es exitosa
        Long cardId = 5343508668973147L;
        Mockito.doNothing().when(cardRepository).deleteBycardId(cardId);

        Boolean resultado = cardService.deletedCard(cardId);

        assertTrue(resultado);
    }

    @Test
    public void testEliminarCard_Error() {
        // Supongamos que la eliminación genera una excepción
        Long cardId = 12345L;
        Mockito.doThrow(new RuntimeException("Error al eliminar")).when(cardRepository).deleteBycardId(cardId);

        Boolean resultado = cardService.deletedCard(cardId);

        assertFalse(resultado);
    }
}
