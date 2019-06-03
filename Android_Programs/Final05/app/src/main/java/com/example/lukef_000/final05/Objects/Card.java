package com.example.lukef_000.final05.Objects;

import java.io.Serializable;

public class Card implements Serializable {
    public String cardName, cardDesc, cardFlavor, cardPack, cardRarity, cardColor, cardValue;
    public int cardCmc, cardPower, cardToughness, cardId;

    public Card(String cardName, String cardDesc, String cardFlavor, String cardPack, String cardRarity, String cardColor, int cardCmc, int cardPower, int cardToughness, String cardValue, int cardId) {
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.cardFlavor = cardFlavor;
        this.cardPack = cardPack;
        this.cardRarity = cardRarity;
        this.cardCmc = cardCmc;
        this.cardPower = cardPower;
        this.cardToughness = cardToughness;
        this.cardValue = cardValue;
        this.cardColor = cardColor;
        this.cardId = cardId;
    }

}

