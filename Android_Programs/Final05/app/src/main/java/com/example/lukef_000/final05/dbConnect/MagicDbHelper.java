package com.example.lukef_000.final05.dbConnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukef_000.final05.Objects.Card;
import com.example.lukef_000.final05.Objects.Deck;
import com.example.lukef_000.final05.Objects.DeckCard;

import java.util.ArrayList;

public class MagicDbHelper extends SQLiteOpenHelper {

    public MagicDbHelper(Context context) {
        super(context, MagicDbContract.DB_NAME, null, MagicDbContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getTypesTableCreateStatement());
        db.execSQL(getCollectionsTableCreateStatement());
        db.execSQL(getDeckTypesTableCreateStatement());
        db.execSQL(getCardsTableCreateStatement());
        db.execSQL(getDecksTableCreateStatement());
        db.execSQL(getDeckCardsTableCreateStatement());
        db.execSQL(getCardTypesTableCreateStatement());
        db.execSQL(getCollectionCardsTableCreateStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAllTables(db);
        onCreate(db);

    }

    public int getNumEntriesInTypesTable(SQLiteDatabase db){
        int count = 0;
        Cursor c = db.rawQuery("SELECT * FROM " + MagicDbContract.TypesTable.TABLE, new String[]{});
        while (c.moveToNext()) {
            count++;
        }
        c.close();
        db.close();
        return count;
    }

    public void createDefaultDeckTypes(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(MagicDbContract.TypesTable.COL_TYPENAME, "Commander");
        values.put(MagicDbContract.TypesTable.COL_DESC, "100 card with only 1 of each card (except basic lands)");

        db.insertOrThrow(MagicDbContract.TypesTable.TABLE, null, values);

        values = new ContentValues();
        values.put(MagicDbContract.TypesTable.COL_TYPENAME, "Standard");
        values.put(MagicDbContract.TypesTable.COL_DESC, "60 card minimum with only 4 of each card max (except basic lands)");

        db.insertOrThrow(MagicDbContract.TypesTable.TABLE, null, values);
        db.close();
    }

    public void dropAllTables(SQLiteDatabase db) {
        String drop = "DROP TABLE IF EXISTS ";
        db.execSQL(drop + MagicDbContract.CardsTable.TABLE + ";");
        db.execSQL(drop + MagicDbContract.CardTypesTable.TABLE + ";");
        db.execSQL(drop + MagicDbContract.TypesTable.TABLE + ";");
        db.execSQL(drop + MagicDbContract.DeckCardsTable.TABLE + ";");
        db.execSQL(drop + MagicDbContract.CollectionsTable.TABLE + ";");
        db.execSQL(drop + MagicDbContract.CollectionCardsTable.TABLE + ";");
        db.execSQL(drop + MagicDbContract.CardsTable.TABLE + ";");
        db.execSQL(drop + MagicDbContract.DeckTypesTable.TABLE + ";");
        db.execSQL(drop + MagicDbContract.DecksTable.TABLE + ";");
    }

    public static String getTypesTableCreateStatement() {
        return "CREATE TABLE " +
                MagicDbContract.TypesTable.TABLE + "( " +
                MagicDbContract.TypesTable.COL_TYPEID + " INTEGER PRIMARY KEY NOT NULL, " +
                MagicDbContract.TypesTable.COL_TYPENAME + " TEXT NOT NULL, " +
                MagicDbContract.TypesTable.COL_DESC + " TEXT NOT NULL " +
                ");";
    }

    public static String getCollectionsTableCreateStatement() {
        return "CREATE TABLE " +
                MagicDbContract.CollectionsTable.TABLE + " ( " +
                MagicDbContract.CollectionsTable.COL_COLLECTIONID + " INTEGER PRIMARY KEY NOT NULL, " +
                MagicDbContract.CollectionsTable.COL_USERNAME + " TEXT NOT NULL " +
                ");";
    }

    public static String getDeckTypesTableCreateStatement() {
        return "CREATE TABLE " +
                MagicDbContract.DeckTypesTable.TABLE + " ( " +
                MagicDbContract.DeckTypesTable.COL_TYPEID + " INTEGER PRIMARY KEY NOT NULL, " +
                MagicDbContract.DeckTypesTable.COL_TYPENAME + " TEXT NOT NULL, " +
                MagicDbContract.DeckTypesTable.COL_DESC + " TEXT DEFAULT NULL " +
                " );";
    }

    public static String getCardsTableCreateStatement() {
        return "CREATE TABLE " +
                MagicDbContract.CardsTable.TABLE + " ( " +
                MagicDbContract.CardsTable.COL_CARDID + " INTEGER PRIMARY KEY NOT NULL, " +
                MagicDbContract.CardsTable.COL_NAME + " TEXT NOT NULL, " +
                MagicDbContract.CardsTable.COL_CMC + " INTEGER NOT NULL, " +
                MagicDbContract.CardsTable.COL_POWER + " INTEGER DEFAULT NULL, " +
                MagicDbContract.CardsTable.COL_TOUGHNESS + " INTEGER DEFAULT NULL, " +
                MagicDbContract.CardsTable.COL_DESC + " TEXT DEFAULT NULL, " +
                MagicDbContract.CardsTable.COL_FLAVOR + " TEXT DEFAULT NULL, " +
                MagicDbContract.CardsTable.COL_PACK + " TEXT DEFAULT NULL, " +
                MagicDbContract.CardsTable.COL_COLOR + " TEXT NOT NULL, " +
                MagicDbContract.CardsTable.COL_RARITY + " TEXT NOT NULL, " +
                MagicDbContract.CardsTable.COL_VALUE + " DECIMAL DEFAULT NULL " +
                " );";
    }

    public static String getDecksTableCreateStatement() {
        return "CREATE TABLE " +
                MagicDbContract.DecksTable.TABLE + " ( " +
                MagicDbContract.DecksTable.COL_DECKID + " INTEGER PRIMARY KEY NOT NULL, " +
                MagicDbContract.DecksTable.COL_NAME + " TEXT NOT NULL, " +
                MagicDbContract.DecksTable.COL_DESC + " TEXT DEFAULT NULL, " +
                MagicDbContract.DecksTable.COL_TYPEID + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + MagicDbContract.DecksTable.COL_TYPEID + ") REFERENCES " + MagicDbContract.DeckTypesTable.TABLE + " (" + MagicDbContract.DeckTypesTable.COL_TYPEID + ")" +
                " );";
    }

    public static String getDeckCardsTableCreateStatement() {
        return "CREATE TABLE " +
                MagicDbContract.DeckCardsTable.TABLE + " ( " +
                MagicDbContract.DeckCardsTable.COL_DECKID + " INTEGER NOT NULL, " +
                MagicDbContract.DeckCardsTable.COL_CARDID + " INTEGER NOT NULL, " +
                MagicDbContract.DeckCardsTable.COL_QUANTITY + " INTEGER NOT NULL, " +
                " PRIMARY KEY (" + MagicDbContract.DeckCardsTable.COL_DECKID + ", " + MagicDbContract.DeckCardsTable.COL_CARDID + "), " +
                " FOREIGN KEY (" + MagicDbContract.DeckCardsTable.COL_DECKID + ") REFERENCES " + MagicDbContract.DecksTable.TABLE + " (" + MagicDbContract.DecksTable.COL_DECKID + "), " +
                " FOREIGN KEY (" + MagicDbContract.DeckCardsTable.COL_CARDID + ") REFERENCES " + MagicDbContract.CardsTable.TABLE + " (" + MagicDbContract.CardsTable.COL_CARDID + ")" +
                ");";
    }

    public static String getCardTypesTableCreateStatement() {
        return "CREATE TABLE " +
                MagicDbContract.CardTypesTable.TABLE + " ( " +
                MagicDbContract.CardTypesTable.COL_CARDID + " INTEGER NOT NULL, " +
                MagicDbContract.CardTypesTable.COL_TYPEID + " INTEGER NOT NULL, " +
                " PRIMARY KEY (" + MagicDbContract.CardTypesTable.COL_CARDID + ", " + MagicDbContract.CardTypesTable.COL_TYPEID + "), " +
                " FOREIGN KEY (" + MagicDbContract.CardTypesTable.COL_CARDID + ") REFERENCES " + MagicDbContract.CardsTable.TABLE + " (" + MagicDbContract.CardsTable.COL_CARDID + "), " +
                " FOREIGN KEY (" + MagicDbContract.CardTypesTable.COL_TYPEID + ") REFERENCES " + MagicDbContract.TypesTable.TABLE + " (" + MagicDbContract.TypesTable.COL_TYPEID + ")" +
                ");";
    }

    public static String getCollectionCardsTableCreateStatement() {
        return "CREATE TABLE " +
                MagicDbContract.CollectionCardsTable.TABLE + " ( " +
                MagicDbContract.CollectionCardsTable.COL_COLLECTIONID + " INTEGER NOT NULL, " +
                MagicDbContract.CollectionCardsTable.COL_CARDID + " INTEGER NOT NULL, " +
                MagicDbContract.CollectionCardsTable.COL_QUANTITY + " INTEGER NOT NULL, " +
                " PRIMARY KEY (" + MagicDbContract.CollectionCardsTable.COL_COLLECTIONID + ", " + MagicDbContract.CollectionCardsTable.COL_CARDID + "), " +
                " FOREIGN KEY (" + MagicDbContract.CollectionCardsTable.COL_COLLECTIONID + ") REFERENCES " + MagicDbContract.CollectionsTable.TABLE + " (" + MagicDbContract.CollectionsTable.COL_COLLECTIONID + "), " +
                " FOREIGN KEY (" + MagicDbContract.CollectionCardsTable.COL_CARDID + ") REFERENCES " + MagicDbContract.CardsTable.TABLE + " (" + MagicDbContract.CardsTable.COL_CARDID + ")" +
                ");";
    }


    public void insertCardIntoDB(SQLiteDatabase db, String cardName, String cmc, String power, String toughness, String description, String flavor, String pack, String color, String rarity, String value) {
        ContentValues values = new ContentValues();
        values.put(MagicDbContract.CardsTable.COL_NAME, cardName);
        values.put(MagicDbContract.CardsTable.COL_CMC, cmc);
        values.put(MagicDbContract.CardsTable.COL_POWER, power);
        values.put(MagicDbContract.CardsTable.COL_TOUGHNESS, toughness);
        values.put(MagicDbContract.CardsTable.COL_DESC, description);
        values.put(MagicDbContract.CardsTable.COL_FLAVOR, flavor);
        values.put(MagicDbContract.CardsTable.COL_PACK, pack);
        values.put(MagicDbContract.CardsTable.COL_COLOR, color);
        values.put(MagicDbContract.CardsTable.COL_RARITY, rarity);
        values.put(MagicDbContract.CardsTable.COL_VALUE, value);

        db.insertOrThrow(MagicDbContract.CardsTable.TABLE, null, values);

        db.close();
    }

    public void insertCardIntoDeckCards(SQLiteDatabase db, int deckID, int cardID, int quantity){
        String insert = "INSERT INTO " + MagicDbContract.DeckCardsTable.TABLE + " (" +
                MagicDbContract.DeckCardsTable.COL_DECKID + ", " + MagicDbContract.DeckCardsTable.COL_CARDID + ", " + MagicDbContract.DeckCardsTable.COL_QUANTITY + ") VALUES (" +
                deckID + ", " + cardID + ", " + quantity + ");";
        db.execSQL(insert);
        /*
        ContentValues values = new ContentValues();
        values.put(MagicDbContract.DeckCardsTable.COL_DECKID, deckID);
        values.put(MagicDbContract.DeckCardsTable.COL_CARDID, cardID);
        values.put(MagicDbContract.DeckCardsTable.COL_QUANTITY, quantity);
        Log.wtf("jkljkl", "AAAAAAAA" + deckID + ", " + cardID + ", " + quantity);
        db.insert(MagicDbContract.DeckCardsTable.TABLE, null, values);
        */
        db.close();

    }

    public void insertDeckIntoDB(SQLiteDatabase database, String deckName, String deckDescription, int typeID) {
        ContentValues values = new ContentValues();
        values.put(MagicDbContract.DecksTable.COL_NAME, deckName);
        values.put(MagicDbContract.DecksTable.COL_DESC, deckDescription);
        values.put(MagicDbContract.DecksTable.COL_TYPEID, typeID);

        database.insertOrThrow(MagicDbContract.DecksTable.TABLE, null, values);

        database.close();
    }

    public void removeCardFromDB(SQLiteDatabase cardDB, Card card) {
        String sql = "DELETE FROM " + MagicDbContract.CardsTable.TABLE + " " +
                "WHERE " + MagicDbContract.CardsTable.COL_CARDID + " = " + card.cardId + ";";
        Cursor c = cardDB.rawQuery(sql, null);
        c.moveToNext();
        c.close();
        cardDB.close();
    }

    public void updateCardInDB(SQLiteDatabase database, Card card) {
        String sql = "UPDATE " + MagicDbContract.CardsTable.TABLE + "  " +
                "SET " + MagicDbContract.CardsTable.COL_VALUE + " = " + card.cardValue + " WHERE " + MagicDbContract.CardsTable.COL_CARDID + " = " + card.cardId + ";";
        Cursor c = database.rawQuery(sql, null);
        c.moveToNext();
        c.close();
        database.close();
    }

    public Card pullLastCardFromDB(SQLiteDatabase db) {
        Card card = null;
        Cursor c = db.rawQuery("SELECT * FROM " + MagicDbContract.CardsTable.TABLE, new String[]{});
        while (c.moveToNext()) {

            card = new Card(

                    //String cardName, String cardDesc, String cardFlavor, String cardPack, String cardRarity, int cardCmc, int cardPower, int cardToughness, int cardValue
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_NAME)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_DESC)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_FLAVOR)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_PACK)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_RARITY)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_COLOR)),
                    c.getInt(c.getColumnIndex(MagicDbContract.CardsTable.COL_CMC)),
                    c.getInt(c.getColumnIndex(MagicDbContract.CardsTable.COL_POWER)),
                    c.getInt(c.getColumnIndex(MagicDbContract.CardsTable.COL_TOUGHNESS)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_VALUE)),
                    c.getInt(c.getColumnIndex(MagicDbContract.CardsTable.COL_CARDID))
            );
        }
        c.close();
        db.close();
        return card;
    }

    public ArrayList<Card> pullAllCardsFromDB(SQLiteDatabase db) {
        ArrayList<Card> cards = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM " + MagicDbContract.CardsTable.TABLE, new String[]{});
        while (c.moveToNext()) {

            cards.add(new Card(

                    //String cardName, String cardDesc, String cardFlavor, String cardPack, String cardRarity, int cardCmc, int cardPower, int cardToughness, int cardValue
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_NAME)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_DESC)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_FLAVOR)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_PACK)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_RARITY)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_COLOR)),
                    c.getInt(c.getColumnIndex(MagicDbContract.CardsTable.COL_CMC)),
                    c.getInt(c.getColumnIndex(MagicDbContract.CardsTable.COL_POWER)),
                    c.getInt(c.getColumnIndex(MagicDbContract.CardsTable.COL_TOUGHNESS)),
                    c.getString(c.getColumnIndex(MagicDbContract.CardsTable.COL_VALUE)),
                    c.getInt(c.getColumnIndex(MagicDbContract.CardsTable.COL_CARDID))
            ));
        }
        c.close();
        db.close();
        return cards;
    }

    public ArrayList<Deck> pullAllDecksFromDB(SQLiteDatabase db){
        ArrayList<Deck> decks = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM " + MagicDbContract.DecksTable.TABLE, new String[]{});
        while (c.moveToNext()) {
            decks.add(new Deck(
            c.getString(c.getColumnIndex(MagicDbContract.DecksTable.COL_NAME)),
            c.getString(c.getColumnIndex(MagicDbContract.DecksTable.COL_DESC)),
            c.getInt(c.getColumnIndex(MagicDbContract.DecksTable.COL_DECKID)),
            c.getInt(c.getColumnIndex(MagicDbContract.DecksTable.COL_TYPEID)
            )));
        }
        c.close();
        db.close();
        return decks;
    }

    public ArrayList<DeckCard> pullAllCardsFromDeck(SQLiteDatabase db, int deckID){
        ArrayList<DeckCard> deckCards = new ArrayList<>();

        //Cursor c = db.rawQuery("SELECT " + deckID + " FROM " + MagicDbContract.DeckCardsTable.TABLE +
        //        " WHERE " + MagicDbContract.DeckCardsTable.COL_DECKID + " = ?", new String[]{deckID + ""});
        Log.wtf("TAGG", "FFFFFF" + deckID);
        Cursor c = db.rawQuery("SELECT * FROM " + MagicDbContract.DeckCardsTable.TABLE + " WHERE " + MagicDbContract.DeckCardsTable.COL_DECKID + " = " + deckID, new String[]{});

        while (c.moveToNext()) {
            c.getInt(c.getColumnIndexOrThrow(MagicDbContract.DeckCardsTable.COL_DECKID));
            c.getInt(c.getColumnIndexOrThrow(MagicDbContract.DeckCardsTable.COL_CARDID));
            c.getInt(c.getColumnIndexOrThrow(MagicDbContract.DeckCardsTable.COL_QUANTITY));
            deckCards.add(new DeckCard(
                    c.getInt(c.getColumnIndex(MagicDbContract.DeckCardsTable.COL_DECKID)),
                    c.getInt(c.getColumnIndex(MagicDbContract.DeckCardsTable.COL_CARDID)),
                    c.getInt(c.getColumnIndex(MagicDbContract.DeckCardsTable.COL_QUANTITY))
            ));
        }
        c.close();
        db.close();
        return deckCards;
    }


    public void removeAllDeckCards(SQLiteDatabase deckCardsDB, int deckID){
        String removal = "DELETE FROM " + MagicDbContract.DecksTable.TABLE + " WHERE " + MagicDbContract.DeckCardsTable.COL_DECKID + " = " + deckID + ";";
        Cursor c = deckCardsDB.rawQuery(removal, null);
        c.moveToNext();
        c.close();
        deckCardsDB.close();
    }

    public void removeDeckFromDB(SQLiteDatabase deckDB, Deck deck) {
        String sql = "DELETE FROM " + MagicDbContract.DeckCardsTable.TABLE + " WHERE " + MagicDbContract.DecksTable.COL_DECKID + " = " + deck.deckID + ";";
        Cursor c = deckDB.rawQuery(sql, null);
        c.moveToNext();
        c.close();
        deckDB.close();
    }

    public boolean cardExistsInDeckCardsTable(SQLiteDatabase db, int cardID, int deckID){
        long cnt = DatabaseUtils.queryNumEntries(db, MagicDbContract.DeckCardsTable.TABLE,
                "cardID = ? AND deckID = ?", new String[] { "" + cardID, "" + deckID});

        return cnt > 0;
    }

    public void updateCardQuantityInDeckCardsTable(SQLiteDatabase db, int cardID, int quantity){
        String update = "UPDATE " + MagicDbContract.DeckCardsTable.TABLE + " SET " + MagicDbContract.DeckCardsTable.COL_QUANTITY + " = "
                + quantity + " + 1" + " WHERE " + MagicDbContract.DeckCardsTable.COL_CARDID + " = " + cardID + ");";

        Cursor c = db.rawQuery(update, null);
        c.moveToNext();
        c.close();
        db.close();
    }

}