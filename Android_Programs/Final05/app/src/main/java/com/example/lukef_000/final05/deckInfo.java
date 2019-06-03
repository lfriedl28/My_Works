package com.example.lukef_000.final05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lukef_000.final05.Objects.Card;
import com.example.lukef_000.final05.Objects.Deck;
import com.example.lukef_000.final05.Objects.DeckCard;
import com.example.lukef_000.final05.dbConnect.MagicDbContract;
import com.example.lukef_000.final05.dbConnect.MagicDbDao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;

public class deckInfo extends AppCompatActivity {

    private Deck deck;
    private MagicDbDao dao;
    private ArrayList<Card> cardsInDeck;
    private ArrayList<DeckCard> deckCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_info);
        dao = new MagicDbDao(this);
        addDeckInfo();
        populateDeckList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_deck_info);
        dao = new MagicDbDao(this);
        addDeckInfo();
        populateDeckList();
    }

    private void addDeckInfo(){
        deck = (Deck) getIntent().getSerializableExtra("deck");
        deckCards = dao.pullAllCardsFromDeckCardDB(deck.deckID);
        ArrayList<Card> allCards = dao.pullAllCardsFromDB();
        cardsInDeck = new ArrayList<>();

        for (DeckCard dc:deckCards) {
            for (Card c:allCards) {
                if(dc.cardID == c.cardId){
                   cardsInDeck.add(c);
                   break;
                }
            }
        }
    }

    private void populateDeckList(){
        ListView lv = findViewById(R.id.deckInfoListView);

        ArrayAdapter adapter = new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_1, cardsInDeck){

            @Override
            public View getView(int position, View convertedView, ViewGroup parent){
                View view = super.getView(position, convertedView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                text1.setText(cardsInDeck.get(position).cardName);
                return view;
            }
        };
        lv.setAdapter(adapter);
    }

    // Unused method
    /*
    private int howManyOfCard(int cardId){
        for (DeckCard dc:deckCards) {
            if(dc.cardID == cardId){
                return dc.quantity;
            }
        }
        return 0;
    }
    */

    public void clearDeck(View view){
        dao.removeAllDeckCards(deck.deckID);
        dao.removeDeckFromDB(deck);
        finish();
    }

}
