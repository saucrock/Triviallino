package fr.houdiard.trivialino;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "party";
        public static final String COLUMN_NAME_SCORE = "Score";
        public static final String COLUMN_NAME_C1 = "C1";
        public static final String COLUMN_NAME_Cc1 = "Cc1";
        public static final String COLUMN_NAME_C2 = "C2";
        public static final String COLUMN_NAME_Cc2 = "Cc2";
        public static final String COLUMN_NAME_C3 = "C3";
        public static final String COLUMN_NAME_Cc3 = "Cc3";
        public static final String COLUMN_NAME_C4 = "C4";
        public static final String COLUMN_NAME_Cc4 = "Cc4";
        public static final String COLUMN_NAME_C5 = "C5";
        public static final String COLUMN_NAME_Cc5 = "Cc5";
        public static final String COLUMN_NAME_C6 = "C6";
        public static final String COLUMN_NAME_Cc6 = "Cc6";


    }
}