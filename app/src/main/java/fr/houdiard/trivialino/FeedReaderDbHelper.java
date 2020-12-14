package fr.houdiard.trivialino;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_C1;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_C2;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_C3;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_C4;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_C5;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_C6;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_Cc1;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_Cc2;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_Cc3;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_Cc4;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_Cc5;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_Cc6;

import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.COLUMN_NAME_SCORE;
import static fr.houdiard.trivialino.FeedReaderContract.FeedEntry.TABLE_NAME;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Parties.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SCORE + " INTEGER," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_C1 + " INTEGER," +
                    COLUMN_NAME_Cc1 + " INTEGER," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_C2 + " INTEGER," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_Cc2 + " INTEGER," +
                    COLUMN_NAME_C3 + " INTEGER," +
                    COLUMN_NAME_Cc3 + " INTEGER," +
                    COLUMN_NAME_C4 + " INTEGER," +
                    COLUMN_NAME_Cc4 + " INTEGER," +
                    COLUMN_NAME_C5 + " INTEGER," +
                    COLUMN_NAME_Cc5 + " INTEGER," +
                    COLUMN_NAME_C6 + " INTEGER," +
                    COLUMN_NAME_Cc6 + " INTEGER)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;




    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long addParty (int score, int c1, int cc1, int c2, int cc2, int c3, int cc3, int c4, int cc4, int c5, int cc5, int c6, int cc6) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_SCORE, score);
        values.put(COLUMN_NAME_C1, c1);
        values.put(COLUMN_NAME_Cc1, cc1);
        values.put(COLUMN_NAME_C2, c2);
        values.put(COLUMN_NAME_Cc2, cc2);
        values.put(COLUMN_NAME_C3, c3);
        values.put(COLUMN_NAME_Cc3, cc3);
        values.put(COLUMN_NAME_C4, c4);
        values.put(COLUMN_NAME_Cc4, cc4);
        values.put(COLUMN_NAME_C5, c5);
        values.put(COLUMN_NAME_Cc5, cc5);
        values.put(COLUMN_NAME_C6, c6);
        values.put(COLUMN_NAME_Cc6, cc6);

        SQLiteDatabase db = this.getWritableDatabase();
        long e = db.insert(TABLE_NAME, null, values);
        db.close();
        return e;
    }

    public ArrayList<ArrayList<Integer>> loadHandler() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        String query = "Select * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            ArrayList<Integer> ei = new ArrayList<Integer>();
            for (int j = 0; j < 14; j++) {
                ei.add(cursor.getInt(j));
            }

            result.add(ei);
        }
        cursor.close();
        db.close();
        return result;
    }

    public int nbPP () {
        String query = "Select * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int a = cursor.getCount();
        return a;

    }

    public int nbQA() {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int s = 0;
        while (cursor.moveToNext()) {
            for (int i = 1; i <= 6; i++) {
                s += cursor.getInt(2 * i);
            }
        }

        return s;


    }

    public int nbQC( int cat) {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int s = 0;
        while (cursor.moveToNext()) {
            s+= cursor.getInt(2*cat);
        }

        return s;


    }

    public int nbQCC( int cat) {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int s = 0;
        while (cursor.moveToNext()) {
            s+= cursor.getInt(2*cat+1);
        }

        return s;


    }

    public long scoreMoy () {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        double s = 0;
        while (cursor.moveToNext()) {
            s+= cursor.getInt(1);
        }

        double n = nbPP();

        return Math.round(s/n);

    }


    public int gA() {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int s = 0;
        while (cursor.moveToNext()) {
            for (int i = 1; i <= 6; i++) {
                s += cursor.getInt(2 * i +1);
            }
        }

        return s;


    }


}
