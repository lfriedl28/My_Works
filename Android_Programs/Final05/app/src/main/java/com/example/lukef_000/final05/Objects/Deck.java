package com.example.lukef_000.final05.Objects;

import java.io.Serializable;

public class Deck implements Serializable{
    public String deckName, deckDesc;
    public int deckID, typeID;

    public Deck(String deckName, String deckDesc, int deckID, int typeID) {
        this.deckName = deckName;
        this.deckDesc = deckDesc;
        this.deckID = deckID;
        this.typeID = typeID;
    }
}
