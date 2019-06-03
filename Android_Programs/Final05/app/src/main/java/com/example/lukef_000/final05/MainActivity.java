package com.example.lukef_000.final05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lukef_000.final05.Network.WebService;
import com.example.lukef_000.final05.Objects.Card;
import com.example.lukef_000.final05.dbConnect.MagicDbDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private MagicDbDao dao;
    private boolean allowNavigation = true;
    private Card insertedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        dao = new MagicDbDao(this);

        Spinner cmcView = (Spinner) findViewById(R.id.cmcSpinner);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        for (int i = 0; i < 100; i++) {
            adapter.add(i);
        }

        cmcView.setAdapter(adapter);

        Spinner rarityView = (Spinner) findViewById(R.id.raritySpinner);
        ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        stringAdapter.add("Common");
        stringAdapter.add("Uncommon");
        stringAdapter.add("Rare");
        stringAdapter.add("Mythic");

        rarityView.setAdapter(stringAdapter);

        CheckBox colorlessCheckBox = findViewById(R.id.ColorlessCheckBox);

        colorlessCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()){
                    CheckBox blackCheckBox = findViewById(R.id.BlackCheckBox);
                    blackCheckBox.setChecked(false);
                    blackCheckBox.setClickable(false);

                    CheckBox blueCheckBox = findViewById(R.id.BlueCheckBox);
                    blueCheckBox.setChecked(false);
                    blackCheckBox.setClickable(false);

                    CheckBox redCheckBox = findViewById(R.id.RedCheckBox);
                    redCheckBox.setChecked(false);
                    redCheckBox.setClickable(false);

                    CheckBox greenCheckBox = findViewById(R.id.GreenCheckBox);
                    greenCheckBox.setChecked(false);
                    greenCheckBox.setClickable(false);

                    CheckBox whiteCheckBox = findViewById(R.id.WhiteCheckBox);
                    whiteCheckBox.setChecked(false);
                    whiteCheckBox.setClickable(false);

                } else {
                    CheckBox blackCheckBox = findViewById(R.id.BlackCheckBox);
                    blackCheckBox.setClickable(true);

                    CheckBox blueCheckBox = findViewById(R.id.BlueCheckBox);
                    blackCheckBox.setClickable(true);

                    CheckBox redCheckBox = findViewById(R.id.RedCheckBox);
                    redCheckBox.setClickable(true);


                    CheckBox greenCheckBox = findViewById(R.id.GreenCheckBox);
                    greenCheckBox.setClickable(true);

                    CheckBox whiteCheckBox = findViewById(R.id.WhiteCheckBox);
                    whiteCheckBox.setClickable(true);
                }
            }
        });

        allowNavigation = false;
        WebService ws = new WebService(this);
        ws.execute(WebService.GET_TOKEN);

    }

    public String checkNameText(){
        EditText cardNameText = findViewById(R.id.CardNameEditText);
        String text = cardNameText.getText().toString().trim();

        EditText cardPackText = findViewById(R.id.packEditText);
        String alsoText = cardPackText.getText().toString().trim();
        if(text.isEmpty() && text.equals("") && alsoText.isEmpty() && alsoText.equals("")) {
            cardNameText.setError("This field must be filled in");
            cardNameText.setText("");
            return "";
        }
            text = text.trim().replace(" ", "-");
            return text;
    }

    public String checkColor(){
        if(isBoxChecked()) {
            String string = "";
            if(((CheckBox)(findViewById(R.id.ColorlessCheckBox))).isChecked()) {
                string = "Colorless";
            } else {
                boolean isFirst = true;
                if(((CheckBox)(findViewById(R.id.RedCheckBox))).isChecked()){
                    string += "Red";
                    isFirst = false;
                }
                if(((CheckBox)(findViewById(R.id.BlueCheckBox))).isChecked()) {
                    if(!isFirst) {
                        string += " And ";
                    }
                    string += "Blue";
                    isFirst = false;
                }
                if(((CheckBox)(findViewById(R.id.BlackCheckBox))).isChecked()){
                    if(!isFirst) {
                        string += " And ";
                    }
                    string += "Black";
                    isFirst = false;
                }
                if(((CheckBox)(findViewById(R.id.GreenCheckBox))).isChecked()) {
                    if(!isFirst) {
                        string += " And ";
                    }
                    string += "Green";
                    isFirst = false;
                }
                if(((CheckBox)(findViewById(R.id.WhiteCheckBox))).isChecked()){
                    if(!isFirst) {
                        string += " And ";
                    }
                    string += "White";
                    isFirst = false;
                }
            }
            return  string;
        } else {
            return "";
        }
    }

    public String setDefaultPack() {
        EditText cardSet = findViewById(R.id.packEditText);
        if (cardSet.equals("")){
            cardSet.getText().toString().equalsIgnoreCase("Alpha");
        }
        return cardSet.getText().toString();
    }

    public boolean isBoxChecked() {
        CheckBox blackCheckBox = findViewById(R.id.BlackCheckBox);
        CheckBox blueCheckBox = findViewById(R.id.BlueCheckBox);
        CheckBox redCheckBox = findViewById(R.id.RedCheckBox);
        CheckBox greenCheckBox = findViewById(R.id.GreenCheckBox);
        CheckBox whiteCheckBox = findViewById(R.id.WhiteCheckBox);
        CheckBox colorlessCheckBox = findViewById(R.id.ColorlessCheckBox);

        return (whiteCheckBox.isChecked() || redCheckBox.isChecked() || blackCheckBox.isChecked() || blueCheckBox.isChecked() || greenCheckBox.isChecked() || colorlessCheckBox.isChecked());
    }

    public boolean allFieldsChecked(){
        //TODO: if all method checks DON'T return "", return true otherwise return false
        String color = checkColor();
        String name = checkNameText();
        return (color != "" && name !="");
    }

    public void sendToDBOnClick(View view) {
        if(allFieldsChecked()){
            defaultToNull();
            insertIntoDB();
            allowNavigation = false;
            WebService webService = new WebService(this);
            webService.execute(WebService.GET_PRODUCT_WITH_NAME, insertedCard.cardName);
            resetAllFields();

        }else {
            displayErrorMessage();
        }
    }

    public void goToCollection(View view) {
        if(allowNavigation){
            Intent intent = new Intent(this, MyCollection.class);
            intent.putExtra("cardName", ((EditText) findViewById(R.id.CardNameEditText)).getText().toString());
            this.startActivity(intent);
        } else {
            displayNavigationErrorMessage();
        }

    }

    public void resetAllFields() {
        ((EditText) findViewById(R.id.CardNameEditText)).setText("");
        ((CheckBox) findViewById(R.id.BlackCheckBox)).setChecked(false);
        ((CheckBox) findViewById(R.id.BlueCheckBox)).setChecked(false);
        ((CheckBox) findViewById(R.id.ColorlessCheckBox)).setChecked(false);
        ((CheckBox) findViewById(R.id.GreenCheckBox)).setChecked(false);
        ((CheckBox) findViewById(R.id.RedCheckBox)).setChecked(false);
        ((CheckBox) findViewById(R.id.WhiteCheckBox)).setChecked(false);
        ((EditText) findViewById(R.id.CardDescEditText)).setText("");
        ((EditText) findViewById(R.id.flavorTextText)).setText("");
        ((EditText) findViewById(R.id.powerEditText)).setText("");
        ((EditText) findViewById(R.id.toughnessEditText)).setText("");
        ((EditText) findViewById(R.id.packEditText)).setText("");
        ((Spinner) findViewById(R.id.raritySpinner)).setSelection(0);
        ((Spinner) findViewById(R.id.cmcSpinner)).setSelection(0);
    }

    public void insertIntoDB(){

        dao.insertCardIntoDB(
                checkNameText(),
                ((Spinner) findViewById(R.id.cmcSpinner)).getSelectedItem().toString(),
                ((EditText) findViewById(R.id.powerEditText)).getText().toString(),
                ((EditText) findViewById(R.id.toughnessEditText)).getText().toString(),
                ((EditText) findViewById(R.id.CardDescEditText)).getText().toString(),
                ((EditText) findViewById(R.id.flavorTextText)).getText().toString(),
                setDefaultPack(),
                (checkColor()),
                ((Spinner) findViewById(R.id.raritySpinner)).getSelectedItem().toString(),
                "0.00"
        );

        insertedCard = new Card(
                checkNameText(),
                ((EditText) findViewById(R.id.CardDescEditText)).getText().toString(),
                ((EditText) findViewById(R.id.flavorTextText)).getText().toString(),
                setDefaultPack(),
                ((Spinner) findViewById(R.id.raritySpinner)).getSelectedItem().toString(),
                checkColor(),
                Integer.parseInt(((Spinner) findViewById(R.id.cmcSpinner)).getSelectedItem().toString()),
                Integer.parseInt(((EditText) findViewById(R.id.powerEditText)).getText().toString()),
                Integer.parseInt(((EditText) findViewById(R.id.toughnessEditText)).getText().toString()),
                "0.00",
                dao.getLastInsertedCardId());

    }

    private void defaultToNull(){
        String powerText = ((EditText) findViewById(R.id.powerEditText)).getText().toString();
        String toughText = ((EditText) findViewById(R.id.toughnessEditText)).getText().toString();
        if(powerText.equalsIgnoreCase("")){
            ((EditText) findViewById(R.id.powerEditText)).setText("0");
        }
        if(toughText.equalsIgnoreCase("")){
            ((EditText) findViewById(R.id.toughnessEditText)).setText("0");
        }
    }

    public void displayErrorMessage() {
        Toast.makeText(this, R.string.error_not_all_fields, Toast.LENGTH_SHORT).show();
    }

    public void displayNavigationErrorMessage() {
        Toast.makeText(this, "The webservice is still fetching data, please wait a few moments before trying again.", Toast.LENGTH_SHORT).show();
    }

    public void webserviceReturned(){
        //The webservice has come back with the token information. Don't allow navigation away from this screen unless this method returns first.
        allowNavigation = true;
    }

    public void receivedAllProducts(JSONArray allProductsWithCardName) throws JSONException {
        String prodIDs = "";

        for(int i = 0; i < allProductsWithCardName.length(); i++) {
            JSONArray idArray = (JSONArray)((JSONObject) allProductsWithCardName.get(i)).get("productConditions");
            for(int j = 0; j < idArray.length(); j++){
                if(((String)((JSONObject) idArray.get(j)).get("name")).contains("Mint")){
                    prodIDs += ((JSONObject) allProductsWithCardName.get(i)).get("productId") + ",";
                }
            }
        }
        prodIDs = prodIDs.substring(0, prodIDs.length() - 1);
        allowNavigation = false;
        WebService ws2 = new WebService(this);
        ws2.execute(WebService.GET_PRODUCT_PRICE_WITH_GIVEN_IDS, prodIDs);

    }

    public void recievedPrice(String price){
        insertedCard.cardValue = price;
        //update the database using the "insertedCard"
        dao.updateCardEntry(insertedCard);
        allowNavigation = true;

    }

    public void noResponse(){
        allowNavigation = true;
    }


}
