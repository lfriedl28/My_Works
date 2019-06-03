package com.example.lukef_000.final05.Objects;

import java.io.Serializable;

public class DeckCard implements Serializable {

   public int deckID, cardID, quantity;

   public DeckCard(int deckID, int cardID, int quantity){
       this.deckID = deckID;
       this.cardID = cardID;
       this.quantity = quantity;
   }

}
