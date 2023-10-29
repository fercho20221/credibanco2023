package com.backend.credibanco.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.credibanco.Service.CardService;
import com.backend.credibanco.Entity.CardEntity;

@RestController
public class cardController {

    @Autowired
    CardService cardService;

    @GetMapping("/card/{productId}/number")
    public ResponseEntity<CardEntity> generarNumeroTarjeta(@PathVariable Integer productId) {

        CardEntity tarjeta = cardService.generarCard(productId);

        return new ResponseEntity<>(tarjeta, HttpStatus.OK);
    }

    @GetMapping("/card/balance/{cardId}")
    public ResponseEntity<CardEntity> queryBalance(@PathVariable Long cardId) {
        
        CardEntity balance = cardService.checkBalanceCard(cardId);

        return new ResponseEntity<>(balance, HttpStatus.OK);

    }

    @PostMapping("/card/enroll")
    private CardEntity enrollCard(@RequestBody CardEntity cardRequest) {
      
        return cardService.enrollCard(cardRequest);
    }

    @PostMapping("/card/balance")
    private CardEntity balanceCard(@RequestBody CardEntity cardBalance) {
        

       return  cardService.balanceCard(cardBalance);
    }

    @DeleteMapping("card/{cardId}")
    public Boolean eliminarCard(@PathVariable("cardId") Long cardId) {
       return  cardService.eliminarCard(cardId);



    }

}
