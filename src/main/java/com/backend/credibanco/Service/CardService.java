package com.backend.credibanco.Service;

import com.backend.credibanco.Entity.CardEntity;

public interface CardService {

    public CardEntity generarCard(Integer productId);

    public CardEntity enrollCard(CardEntity cardRequest);

    public Boolean eliminarCard(Long cardId);

    public CardEntity balanceCard(CardEntity cardBalance);

    public CardEntity checkBalanceCard(Long cardId);

}
