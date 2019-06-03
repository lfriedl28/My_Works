package com.example.lukef_000.final05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lukef_000.final05.Objects.Card;
import com.example.lukef_000.final05.Objects.Deck;
import com.example.lukef_000.final05.dbConnect.MagicDbDao;

import java.util.ArrayList;

public class AddCardToDeckView extends AppCompatActivity {
    private MagicDbDao dao;
    private ArrayList<Deck> decks;
    private Card selectedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_to_deck_view);
        dao = new MagicDbDao(this);
        decks = dao.pullAllDecksFromDB();
        populateListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_add_card_to_deck_view);
        decks = dao.pullAllDecksFromDB();
        populateListView();
    }

    public void goToDeckCreator(View view){
        Intent intent = new Intent(this, deckCreationActivity.class);
        this.startActivity(intent);
    }

    public void goToDeckInfo(Deck deck){
        addCardToDeck(deck.deckID);
        Intent intent = new Intent(this, deckInfo.class);
        intent.putExtra("deck", deck);
        startActivity(intent);
    }

    public void populateListView(){
        selectedCard = (Card) getIntent().getSerializableExtra("card");
        ListView listView = findViewById(R.id.DecksListView);

        ArrayAdapter adapter = new ArrayAdapter<Deck>(this, android.R.layout.simple_list_item_1, decks){

            @Override
            public View getView(int position, View convertedView, ViewGroup parent){
                View view = super.getView(position, convertedView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                text1.setText(decks.get(position).deckName);
                return view;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToDeckInfo((Deck) adapterView.getItemAtPosition(i));
            }
        });
    }

    public void addCardToDeck(int deckID){
        if(dao.cardExistsInDeckCardsTable(selectedCard.cardId, deckID)){
            Toast.makeText(this, "That card has already been added to this deck, it will not be added", Toast.LENGTH_SHORT).show();
            //write an update statement for the deckCard where you make the quantity increment
            //dao.incrementCardCountInDeckCardsTable(selectedCard.cardId, 1);
        } else {
            dao.insertCardIntoDeck(deckID, selectedCard.cardId , 1);
        }


    }

}
