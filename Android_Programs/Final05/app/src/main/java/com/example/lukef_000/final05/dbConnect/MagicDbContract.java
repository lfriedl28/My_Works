package com.example.lukef_000.final05.dbConnect;

import android.provider.BaseColumns;

public class MagicDbContract {

    public static final String DB_NAME = "lfriedl.lukefriedl.com.cvtc.edu.magic.cardcollectiondb";
    public static final int DB_VERSION = 10;

    public class DecksTable implements BaseColumns {
        public static final String TABLE = "decks";

        public static final String COL_DECKID = "deckID";
        public static final String COL_NAME = "name";
        public static final String COL_DESC = "desc";
        public static final String COL_TYPEID = "typeID";
    }

    public class DeckCardsTable implements BaseColumns {
        public static final String TABLE = "deckCards";

        public static final String COL_DECKID = "deckID";
        public static final String COL_CARDID = "cardID";
        public static final String COL_QUANTITY = "quantity";
    }

    public class CardsTable implements BaseColumns {
        public static final String TABLE = "cards";

        public static final String COL_CARDID = "cardID";
        public static final String COL_NAME = "name";
        public static final String COL_CMC = "cmc";
        public static final String COL_POWER = "power";
        public static final String COL_TOUGHNESS = "toughness";
        public static final String COL_DESC = "desc";
        public static final String COL_FLAVOR = "flavor";
        public static final String COL_PACK = "pack";
        public static final String COL_COLOR = "color";
        public static final String COL_RARITY = "rarity";
        public static final String COL_VALUE = "value";
    }

    public class CardTypesTable implements BaseColumns {
        public static final String TABLE = "cardTypes";

        public static final String COL_CARDID = "cardID";
        public static final String COL_TYPEID = "typeID";
    }

    public class TypesTable implements BaseColumns {
        public static final String TABLE = "types";

        public static final String COL_TYPEID = "typeID";
        public static final String COL_TYPENAME = "typeName";
        public static final String COL_DESC = "desc";
    }

    public class CollectionsTable implements BaseColumns {
        public static final String TABLE = "collections";

        public static final String COL_COLLECTIONID = "collectionID";
        public static final String COL_USERNAME = "username";
    }

    public class CollectionCardsTable implements BaseColumns {
        public static final String TABLE = "collectionCards";

        public static final String COL_COLLECTIONID = "collectionID";
        public static final String COL_CARDID = "cardID";
        public static final String COL_QUANTITY = "quantity";
    }

    public class DeckTypesTable implements BaseColumns {
        public static final String TABLE = "deckTypes";

        public static final String COL_TYPEID = "typeID";
        public static final String COL_TYPENAME = "typeName";
        public static final String COL_DESC = "desc";
    }

}

