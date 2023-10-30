package com.backend.credibanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backend.credibanco.Entity.TransactionEntity;
import com.backend.credibanco.Repository.TransactionRepository;
import com.backend.credibanco.ServiceImpl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CheckTransactionImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        // Configura el comportamiento de los mocks si es necesario
    }

    @Test
    public void testCheckTransaction_Successful() {
        // Configura el comportamiento del mock transactionRepository
        TransactionEntity expectedTransaction = new TransactionEntity();
        expectedTransaction.setTransactionId(12345);
        Mockito.when(transactionRepository.findBytransactionId(12345)).thenReturn(expectedTransaction);

        // Ejecuta el método que deseas probar
        TransactionEntity result = transactionServiceImpl.checkTransaction(12345);

        // Verifica si el resultado es el esperado
        assertEquals(expectedTransaction, result);
    }

    @Test
    public void testCheckTransaction_NotFound() {
        // Configura el comportamiento del mock transactionRepository para devolver null
        Mockito.when(transactionRepository.findBytransactionId(54321)).thenReturn(null);

        // Ejecuta el método que deseas probar
        TransactionEntity result = transactionServiceImpl.checkTransaction(54321);

        // Verifica si el resultado es null
        assertNull(result);
    }

    // Agrega más pruebas para diferentes escenarios, como transacción no encontrada, etc.
}
