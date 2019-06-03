package com.example.lukef_000.final05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lukef_000.final05.Objects.Card;
import com.example.lukef_000.final05.dbConnect.MagicDbDao;

public class cardInfo extends AppCompatActivity {

    private Card card;
    private MagicDbDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);
        addCardInfo();
        dao = new MagicDbDao(this);
    }

    public void addCardInfo(){
        card = (Card) getIntent().getSerializableExtra("SelectedCard");
        ((TextView) findViewById(R.id.CardNameTextView)).setText("Name: " + card.cardName);
        ((TextView) findViewById(R.id.CmcTextView)).setText("Converted Mana Cost: " + card.cardCmc);
        ((TextView) findViewById(R.id.RarityTextView)).setText("Rarity: " + card.cardRarity);
        ((TextView) findViewById(R.id.ColorTextView)).setText("Color: " + card.cardColor);
        ((TextView) findViewById(R.id.DescriptionTextView)).setText("Description: " + card.cardDesc);
        ((TextView) findViewById(R.id.FlavorTextView)).setText("Flavor Text: " + card.cardFlavor);
        ((TextView) findViewById(R.id.PowerTextView)).setText("Power: " + card.cardPower );
        ((TextView) findViewById(R.id.ToughnessTextView)).setText("Toughness: " + card.cardToughness);
        ((TextView) findViewById(R.id.CardPack)).setText("Set/Pack: " + card.cardPack);
        ((TextView) findViewById(R.id.cardValue)).setText("Card Value: " + card.cardValue);


    }

    public void addToDeck(View view){

        Intent intent = new Intent(this, AddCardToDeckView.class);
        intent.putExtra("card", card);
        this.startActivity(intent);
    }

    public void removeFromCollection(View view){
        dao.removeCard(card);
        finish();
    }

}