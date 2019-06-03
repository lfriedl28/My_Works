package com.example.lukef_000.final05.dbConnect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lukef_000.final05.Objects.Card;
import com.example.lukef_000.final05.Objects.Deck;
import com.example.lukef_000.final05.Objects.DeckCard;

import java.util.ArrayList;

public class MagicDbDao {
    private MagicDbHelper dbHelper;

    public MagicDbDao(Context context) {
        dbHelper = new MagicDbHelper(context);
        if(dbHelper.getNumEntriesInTypesTable(getDBRead()) == 0){
            dbHelper.createDefaultDeckTypes(getDBWrite());
        }
    }

    private SQLiteDatabase getDBWrite() {
        return dbHelper.getWritableDatabase();
    }

    private SQLiteDatabase getDBRead() {
        return dbHelper.getReadableDatabase();
    }

    public void insertCardIntoDB(String cardName, String cmc, String power, String toughness, String description, String flavor, String pack, String color, String rarity, String value) {
        dbHelper.insertCardIntoDB(getDBWrite(), cardName, cmc, power, toughness, description, flavor, pack, color, rarity, value);
    }

    public Card pullLastCardFromDB() {
        return dbHelper.pullLastCardFromDB(getDBRead());
    }

    public ArrayList<Card> pullAllCardsFromDB() {
        return dbHelper.pullAllCardsFromDB(getDBRead());
    }

    public int getLastInsertedCardId(){
        return dbHelper.pullLastCardFromDB(getDBRead()).cardId;
    }

    public void updateCardEntry(Card card){
        dbHelper.updateCardInDB(getDBWrite(), card);
    }

    public void removeCard(Card card) { dbHelper.removeCardFromDB(getDBWrite(), card);}

    public ArrayList<Deck> pullAllDecksFromDB() {
        return dbHelper.pullAllDecksFromDB(getDBRead());
    }

    public void insertDeckIntoDB(String deckName, String deckType, int typeID){
        dbHelper.insertDeckIntoDB(getDBWrite(), deckName,deckType, typeID);
    }

    public ArrayList<DeckCard> pullAllCardsFromDeckCardDB(int id){
        return dbHelper.pullAllCardsFromDeck(getDBRead(), id);
    }

    public void insertCardIntoDeck(int deckID, int cardID, int quantity){
        dbHelper.insertCardIntoDeckCards(getDBWrite(), deckID, cardID, quantity);
    }

    public void removeDeckFromDB(Deck deck){
        dbHelper.removeDeckFromDB(getDBRead(), deck);
    }

    public void removeAllDeckCards(int deckID){
        dbHelper.removeAllDeckCards(getDBRead(), deckID);
    }

    public boolean cardExistsInDeckCardsTable(int cardId, int deckId){
        return dbHelper.cardExistsInDeckCardsTable(getDBRead(), cardId, deckId);
    }

    public void incrementCardCountInDeckCardsTable(int cardID, int quantity){
        dbHelper.updateCardQuantityInDeckCardsTable(getDBWrite(), cardID, quantity);
    }

}
