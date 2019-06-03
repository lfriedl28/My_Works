package com.example.lukef_000.final05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lukef_000.final05.Objects.Deck;
import com.example.lukef_000.final05.dbConnect.MagicDbDao;

public class deckCreationActivity extends AppCompatActivity {

    private MagicDbDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_creation);
        dao = new MagicDbDao(this);
    }

    public void insertDeckIntoDB(){
        String deckName = ((EditText) findViewById(R.id.DeckNameEditText)).getText().toString().trim();
        String deckType = ((EditText)findViewById(R.id.DeckTypeEditText)).getText().toString().trim();
        int typeID = allowOnlyOneFormat();
        dao.insertDeckIntoDB(deckName, deckType, typeID);
        finish();
    }

    public void loadDeckInfo(Deck deck){
        Intent intent = new Intent(this, deckInfo.class);
        intent.putExtra("deck", deck);
        startActivity(intent);

    }

    public void submitDeckCreation(View view){
        insertDeckIntoDB();
    }

    public int allowOnlyOneFormat(){
        CheckBox standardCheck = findViewById(R.id.standardCheckBox);
        CheckBox commanderCheck = findViewById(R.id.commanderCheckBox);
        int id=0;
       if(isboxChecked()){

           if(commanderCheck.isChecked()){
               id = 1;
           } else if(standardCheck.isChecked()){
               id=2;
           } else {
               displayErrorMessage();
           }
       }
       return id;
    }

    public boolean isboxChecked(){
        CheckBox standardCheckBox = findViewById(R.id.standardCheckBox);
        CheckBox commanderCheckBox = findViewById(R.id.commanderCheckBox);
        return (standardCheckBox.isChecked() || commanderCheckBox.isChecked());
    }

    public void displayErrorMessage() {
        Toast.makeText(this, R.string.error_not_all_fields, Toast.LENGTH_SHORT).show();
    }

}
