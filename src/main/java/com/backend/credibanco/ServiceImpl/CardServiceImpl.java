package com.backend.credibanco.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;

import com.backend.credibanco.Entity.CardEntity;
import com.backend.credibanco.Repository.CardRepository;
import com.backend.credibanco.Service.CardService;


import jakarta.transaction.Transactional;

@Service
public class CardServiceImpl implements CardService {
   
    @Autowired
    private CardRepository cardRepository; // Inyeccion de dependencias

    @Override
    public CardEntity generarCard(Integer productId) {

        Long numeroTarjeta = generarNumeroDeTarjeta(productId);

        CardEntity tarjeta = new CardEntity();
        tarjeta.setCardId(numeroTarjeta);

        return cardRepository.saveAndFlush(tarjeta);

    }

    private Long generarNumeroDeTarjeta(Integer productId) {

        if (productId == null) {
            throw new IllegalArgumentException("El productId no puede ser nulo");
        }

        String productIdStr = productId.toString();

        Random random = new Random();
        StringBuilder numeroTarjeta = new StringBuilder(productIdStr);

        while (numeroTarjeta.length() < 16) {
            numeroTarjeta.append(random.nextInt(10));
        }

        Long cardId = Long.parseLong(numeroTarjeta.toString());

        return cardId;
    }

    @Transactional
    public Boolean eliminarCard(Long cardId) {
        
        try {
            cardRepository.deleteBycardId(cardId);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    public CardEntity enrollCard(CardEntity cardRequest) {

        Long cardId = cardRequest.getCardId();
        String firstName = cardRequest.getFirstName();
        String lastName = cardRequest.getLastName();
        CardEntity card = cardRepository.findByCardId(cardId);


        LocalDateTime expirationDate = LocalDateTime.now().plusYears(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        String formatExpirationDate = expirationDate.format(formatter);

        card.setExpirationDate(formatExpirationDate);
        card.setFirstName(firstName);
        card.setLastName(lastName);
        card.setEnroll(true);
        card.setBalance((long) 0);
        card.setCurrency("USD");

        return cardRepository.saveAndFlush(card);
    }

    @Override
    public CardEntity balanceCard(CardEntity cardBalance) {

        Long cardId = cardBalance.getCardId();
        Long balance = cardBalance.getBalance();

        CardEntity cardBalance1 = cardRepository.findByCardId(cardId);

        Long currentBalance = cardBalance1.getBalance();

        Long newBalance = currentBalance + balance;

        cardBalance1.setBalance(newBalance);

        return cardRepository.saveAndFlush(cardBalance1);

    }

        
        public CardEntity checkBalanceCard(Long cardId) {

        CardEntity cardEntity = cardRepository.findByCardId(cardId);
       
        return cardRepository.saveAndFlush(cardEntity);
}

}
