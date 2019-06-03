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

import com.example.lukef_000.final05.Objects.Card;
import com.example.lukef_000.final05.dbConnect.MagicDbDao;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyCollection extends AppCompatActivity {

        private MagicDbDao dao;
        private ArrayList<Card> cards;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my_collection);
            dao = new MagicDbDao(this);
            String cn = getIntent().getStringExtra("cardName");

            cards = dao.pullAllCardsFromDB();
            setArrayAdapter();
            displayPriceOnText();
        }

        @Override
        protected void onResume() {
            super.onResume();
            cards = dao.pullAllCardsFromDB();
            setArrayAdapter();
            displayPriceOnText();
        }

        public void loadCardInfo(Card card){
            Intent intent = new Intent(this, cardInfo.class);
            intent.putExtra("SelectedCard", card);
            startActivity(intent);
        }

        private void setArrayAdapter(){
            ListView lv = findViewById(R.id.cardCollectionView);

            ArrayAdapter adapter = new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_1, cards){
                        @Override
                        public View getView(int position, View convertedView, ViewGroup parent){
                            View view = super.getView(position, convertedView, parent);
                            TextView text1 = view.findViewById(android.R.id.text1);

                            text1.setText(cards.get(position).cardName);
                            return view;
                }
            };
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    loadCardInfo((Card) adapterView.getItemAtPosition(i));
                }
            });
        }

        private void displayPriceOnText(){
            double cost = 0.00;
            for (Card card:cards) {
                cost += (Double.parseDouble(card.cardValue));
            }
            ((TextView)findViewById(R.id.totalValue)).setText("$" + new DecimalFormat("#.##").format(cost));
        }

    }

