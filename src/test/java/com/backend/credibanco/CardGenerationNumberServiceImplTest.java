package com.backend.credibanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.backend.credibanco.ServiceImpl.CardServiceImpl;

public class CardGenerationNumberServiceImplTest {

    @Test
    public void testGenerarNumeroDeTarjeta() {
        CardServiceImpl cardService = new CardServiceImpl();

        Integer productIdValido = 123456;
        Long numeroTarjetaValido = cardService.generarNumeroDeTarjeta(productIdValido);
        assertNotNull(numeroTarjetaValido);
        assertTrue(numeroTarjetaValido >= 1000000000000000L && numeroTarjetaValido <= 9999999999999999L);

        Integer productIdNulo = null;
        assertThrows(IllegalArgumentException.class, () -> cardService.generarNumeroDeTarjeta(productIdNulo));
    }
}
